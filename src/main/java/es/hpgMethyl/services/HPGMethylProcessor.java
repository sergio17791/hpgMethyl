package es.hpgMethyl.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.hpgMethyl.builders.AnalysisCommandBuilder;
import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.ConfigurationNotFound;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.usecases.analysis.GetNextPendingMethylationAnalysis.GetNextPendingMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatus;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisStatus.UpdateMethylationAnalysisStatusRequest;
import es.hpgMethyl.usecases.configuration.GetApplicationConfiguration.GetApplicationConfiguration;

public class HPGMethylProcessor extends Thread {
	
	private static Semaphore semaphore = new Semaphore(1);

	private AnalysisRequestDAO analysisRequestDAO;
	
	private ConfigurationDAO configurationDAO;
	
	public HPGMethylProcessor(AnalysisRequestDAO analysisRequestDAO, ConfigurationDAO configurationDAO) {
		super("HPGMethylProcessor");
		this.analysisRequestDAO = analysisRequestDAO;
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
				
				String command = new AnalysisCommandBuilder().build(configuration, analysisRequest);
				Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, command);
				
				try {
					executeCommand(command);
				} catch (IOException | InterruptedException e) {
					Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e.getMessage());
					
					try {
						new UpdateMethylationAnalysisStatus(analysisRequestDAO).execute(
								new UpdateMethylationAnalysisStatusRequest(analysisRequest.getId(), AnalysisStatus.FAILED)
						);
					} catch (AnalysisRequestNotFound | UpdateMethylationAnalysisException e2) {
						Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.SEVERE, e2.getMessage());
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
			}
									
			semaphore.release();
			
			Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "Releasing blockage from HPG-Mehtyl process");
			
		} 
		
		Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, "HPG-Methyl Processing Finished");
	}
	
	private void executeCommand(String command) throws IOException, InterruptedException {

		Runtime runtime = Runtime.getRuntime();
		
		Process process = runtime.exec(command);
		process.waitFor();
			
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String output = "";
		while ((output = bufferedReader.readLine()) != null) {
			Logger.getLogger (HPGMethylProcessor.class.getName()).log(Level.INFO, output);
		}
	}
}
