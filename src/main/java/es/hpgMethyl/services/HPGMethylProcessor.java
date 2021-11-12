
package es.hpgMethyl.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtils;

import es.hpgMethyl.builders.AnalysisCommandBuilder;
import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.dao.HPGMethylFileDAO;
import es.hpgMethyl.dao.hibernate.AnalysisResultDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.ConfigurationNotFound;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.CreateMehtylationResultException;
import es.hpgMethyl.exceptions.DuplicatedAnalysisResult;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.UpdateFileException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.GetNextPendingMethylationAnalysis.GetNextPendingMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis.ListPendingMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis.ListPendingMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatus;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatusRequest;
import es.hpgMethyl.usecases.configuration.GetApplicationConfiguration.GetApplicationConfiguration;
import es.hpgMethyl.usecases.file.CreateFile.CreateFile;
import es.hpgMethyl.usecases.file.CreateFile.CreateFileRequest;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFile;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFileRequest;
import es.hpgMethyl.usecases.result.CreateErrorMethylationResult.CreateErrorMethylationResult;
import es.hpgMethyl.usecases.result.CreateErrorMethylationResult.CreateErrorMethylationResultRequest;
import es.hpgMethyl.usecases.result.CreateMethylationResult.CreateMehtylationResult;
import es.hpgMethyl.usecases.result.CreateMethylationResult.CreateMehtylationResultRequest;
import es.hpgMethyl.utils.FileUtils;

public class HPGMethylProcessor extends Thread {
	
	private static Semaphore semaphore = new Semaphore(1);

	private AnalysisRequestDAO analysisRequestDAO;
	
	private HPGMethylFileDAO hpgMethylFileDAO;
	
	private ConfigurationDAO configurationDAO;
	
	public HPGMethylProcessor(AnalysisRequestDAO analysisRequestDAO, HPGMethylFileDAO hpgMethylFileDAO, ConfigurationDAO configurationDAO) {
		super("HPGMethylProcessor");
		this.analysisRequestDAO = analysisRequestDAO;
		this.hpgMethylFileDAO = hpgMethylFileDAO;
		this.configurationDAO = configurationDAO;
	}
	
	@Override
    public void run() {
		
		Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "New HPG-Methyl Processing Requested");
		
		boolean freeProcess = semaphore.tryAcquire();
		
		if(freeProcess) {
			
			Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "Starting new HPG-methyl processing");
			
			boolean continueProcessing = true;
			
			while(continueProcessing) {
				
				Configuration configuration;
				try {
					configuration = new GetApplicationConfiguration(configurationDAO).execute().getConfiguration();
				} catch (ConfigurationNotFound e1) {
					continueProcessing = false;
					break;
				}
				
				AnalysisRequest analysisRequest;
				try {
					analysisRequest = new GetNextPendingMethylationAnalysis(analysisRequestDAO).execute().getAnalysisRequest();
				} catch (AnalysisRequestNotFound e) {
					continueProcessing = false;
					break;
				}
				
				Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "Processing analysis " + analysisRequest.getId());
				
				try {
					new UpdateMethylationAnalysisStatus(analysisRequestDAO).execute(
							new UpdateMethylationAnalysisStatusRequest(analysisRequest.getId(), AnalysisStatus.PROCESSING)
					);
				} catch (AnalysisRequestNotFound | UpdateMethylationAnalysisException e) {
					Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e.getMessage());
					continueProcessing = false;
					break;
				}
				
				String userFilesPath = FileUtils.concatenatePath(configuration.getUsersDirectoryAbsolutePath(), analysisRequest.getUser().getId().toString());
				String outputDirectory = FileUtils.concatenatePath(userFilesPath, analysisRequest.getId().toString());
				
				
				
				List<String> command = new AnalysisCommandBuilder().build(configuration, analysisRequest, outputDirectory);
				Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, String.join(" ", command));
				
				try {
					Boolean directoryCreated = FileUtils.createDirectory(outputDirectory);
					
					if(!directoryCreated) {
						throw new IOException();
					}
					
					HPGMethylResultReader resultReader = executeCommand(command);
					
					generateGraphs(outputDirectory, resultReader);
					
					File zipFile = FileUtils.compressDirectoryInZip(outputDirectory, userFilesPath, analysisRequest.getIdentifier());
					
					HPGMethylFile resultFile = new CreateFile(new HPGMethylFileDAOHibernate()).execute(
							new CreateFileRequest(
								analysisRequest.getUser(),
								zipFile.getName(),
								zipFile.getAbsolutePath(), 
								zipFile.length(),
								"application/octet-stream",
								Boolean.FALSE
							)	
					).getFile();				
					
					new CreateMehtylationResult(new AnalysisResultDAOHibernate()).execute(
							new CreateMehtylationResultRequest(
									analysisRequest, 
									resultFile,
									resultReader.getTotalNumberCAnalysed(),
									resultReader.getTotalMethylatedCCPGContext(),
									resultReader.getTotalMethylatedCCHGContext(),
									resultReader.getTotalMethylatedCCHHContext(),
									resultReader.getTotalCToTConversionsCPGContext(),
									resultReader.getTotalCToTConversionsCHGContext(),
									resultReader.getTotalCToTConversionsCHHContext(),
									resultReader.getcMethylatedCPGContext(),
									resultReader.getcMethylatedCHGContext(),
									resultReader.getcMethylatedCHHContext(),
									resultReader.getLoadingTime(),
									resultReader.getAligmentTime(),
									resultReader.getTotalTime(),
									resultReader.getTotalReadsProcessed(),
									resultReader.getReadsMapped(),
									resultReader.getTotalReadsMapped(),
									resultReader.getReadsUnmapped(),
									resultReader.getTotalReadsUnmapped()
							)
					);
					
					FileUtils.delete(outputDirectory);
					
				} catch (IOException | InterruptedException | CreateFileException | DuplicatedFile | CreateMehtylationResultException | DuplicatedAnalysisResult e) {
					Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e.getMessage());
					
					try {
						new UpdateMethylationAnalysisStatus(analysisRequestDAO).execute(
								new UpdateMethylationAnalysisStatusRequest(analysisRequest.getId(), AnalysisStatus.FAILED)
						);
						
						new CreateErrorMethylationResult(new AnalysisResultDAOHibernate()).execute(
								new CreateErrorMethylationResultRequest(
										analysisRequest, 
										e.getMessage()
								)
						);
						
					} catch (AnalysisRequestNotFound | UpdateMethylationAnalysisException | CreateMehtylationResultException | DuplicatedAnalysisResult e2) {
						Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e2.getMessage());
					}
					
					deleteFileFromSystem(analysisRequest.getInputReadFile());
					
					if(analysisRequest.getPairedMode().equals(PairedMode.PAIRED_END_MODE)) {
						deleteFileFromSystem(analysisRequest.getPairedEndModeFile());
					}
					
					break;
				}
				
				try {
					new UpdateMethylationAnalysisStatus(analysisRequestDAO).execute(
							new UpdateMethylationAnalysisStatusRequest(analysisRequest.getId(), AnalysisStatus.COMPLETED)
					);
				} catch (AnalysisRequestNotFound | UpdateMethylationAnalysisException e) {
					Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e.getMessage());
				}
				
				deleteFileFromSystem(analysisRequest.getInputReadFile());
				
				if(analysisRequest.getPairedMode().equals(PairedMode.PAIRED_END_MODE)) {
					deleteFileFromSystem(analysisRequest.getPairedEndModeFile());
				}
			}
									
			semaphore.release();
			
			Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "Releasing blockage from HPG-Mehtyl process");
			
		} 
		
		Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "HPG-Methyl Processing Finished");
	}
	
	private HPGMethylResultReader executeCommand(List<String> command) throws IOException, InterruptedException {

		HPGMethylResultReader resultReader = new HPGMethylResultReader();
		
		Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String output;
		while((output = bufferedReader.readLine()) != null){
			resultReader.readLine(output);
		}
		
		process.waitFor();
		
		return resultReader;
	}
	
	private void deleteFileFromSystem(HPGMethylFile file) {
		
		List<AnalysisRequest> analysisRequestWithFileList = new ListPendingMethylationAnalysis(analysisRequestDAO).execute(
				new ListPendingMethylationAnalysisRequest(file.getUser(), file)
		).getAnalysisRequestList();
		
		if(analysisRequestWithFileList.isEmpty() && file.getStored()) {
			try {
				new UnstoreFile(hpgMethylFileDAO).execute(
						new UnstoreFileRequest(file.getId())
				);
				
				FileUtils.delete(file.getPath());
			} catch (FileNotFound | UpdateFileException e) {
				Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e.getMessage());
			} 
		}
	}
	
	private void generateGraphs(String outputDirectory, HPGMethylResultReader resultReader) {
		
		String graphsDirectory = FileUtils.concatenatePath(outputDirectory, "graphs");
		
		Boolean directoryCreated = FileUtils.createDirectory(graphsDirectory);
		
		if(directoryCreated) {
			Integer totalNumberCAnalysed = resultReader.getTotalNumberCAnalysed();
			Integer totalMethylatedCCPGContext = resultReader.getTotalMethylatedCCPGContext();
			Integer totalMethylatedCCHGContext = resultReader.getTotalMethylatedCCHGContext();
			Integer totalMethylatedCCHHContext = resultReader.getTotalMethylatedCCHHContext();
			Integer totalMethylated = totalMethylatedCCPGContext + totalMethylatedCCHGContext + totalMethylatedCCHHContext;
			Integer totalCToTConversionsCPGContext = resultReader.getTotalCToTConversionsCPGContext();
			Integer totalCToTConversionsCHGContext = resultReader.getTotalCToTConversionsCHGContext();
			Integer totalCToTConversionsCHHContext = resultReader.getTotalCToTConversionsCHHContext();
			Integer totalCToTConversions = totalCToTConversionsCPGContext + totalCToTConversionsCHGContext + totalCToTConversionsCHHContext;
			
			DefaultCategoryDataset cytosineMethylationReportDataset = new DefaultCategoryDataset();
			cytosineMethylationReportDataset.addValue(totalNumberCAnalysed.doubleValue(), "Total", "C's analysed");
			cytosineMethylationReportDataset.addValue(totalMethylatedCCPGContext.doubleValue(), "CpG context", "Methylated C's");
			cytosineMethylationReportDataset.addValue(totalMethylatedCCHGContext.doubleValue(), "CHG context", "Methylated C's");
			cytosineMethylationReportDataset.addValue(totalMethylatedCCHHContext.doubleValue(), "CHH context", "Methylated C's");
			cytosineMethylationReportDataset.addValue(totalMethylated.doubleValue(), "Total", "Methylated C's");
			cytosineMethylationReportDataset.addValue(totalCToTConversionsCPGContext.doubleValue(), "CpG context", "C to T conversions");
			cytosineMethylationReportDataset.addValue(totalCToTConversionsCHGContext.doubleValue(), "CHG context", "C to T conversions");
			cytosineMethylationReportDataset.addValue(totalCToTConversionsCHHContext.doubleValue(), "CHH context", "C to T conversions");
			cytosineMethylationReportDataset.addValue(totalCToTConversions.doubleValue(), "Total", "C to T conversions");
			
			JFreeChart cytosineMethylationReportChart = ChartFactory.createStackedBarChart(
					"Cytosine Methylation Report", 
					null, 
			        null, 
			        cytosineMethylationReportDataset,
			        PlotOrientation.VERTICAL, 
			        true, 
			        true, 
			        false
			);
			
			File cytosineMethylationReportFile = new File( graphsDirectory + "/cytosine_methylation_report.jpeg" ); 
			try {
				ChartUtils.saveChartAsJPEG(cytosineMethylationReportFile, cytosineMethylationReportChart, 960, 720);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}
