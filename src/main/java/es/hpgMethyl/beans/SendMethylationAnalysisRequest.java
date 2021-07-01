package es.hpgMethyl.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.servlet.http.Part;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.ConfigurationDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.services.HPGMethylProcessor;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisRequest;
import es.hpgMethyl.usecases.file.CreateFile.CreateFile;
import es.hpgMethyl.usecases.file.CreateFile.CreateFileRequest;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFile;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFileRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

@ManagedBean(name="sendAnalysis")
@ViewScoped
public class SendMethylationAnalysisRequest implements Serializable {
	
	private static final long serialVersionUID = -589016682649578821L;

	private Part inputReadFile;
	private Part pairedEndModeFile;
	private HPGMethylFile selectedInputReadFile;
	private HPGMethylFile selectedPairedEndModeFile;
	private Boolean newInputReadFile;
	private Boolean newPairedReadFile;
	private UIComponent sendAnalysisComponent;
	private List<HPGMethylFile> userFiles;
	
	public SendMethylationAnalysisRequest() {
		this.inputReadFile = null;
		this.pairedEndModeFile = null;
		this.selectedInputReadFile = null;
		this.selectedPairedEndModeFile = null;
		this.newInputReadFile = true;
		this.newPairedReadFile = true;
		this.userFiles = new ArrayList<HPGMethylFile>();
	}
	
	@PostConstruct
	public void init() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user != null) {
			ListUserFilesResponse response = new ListUserFiles(new HPGMethylFileDAOHibernate()).execute(
					new ListUserFilesRequest(user, true)
			);
			
			this.userFiles = response.getFiles();
		}				
	}

	/**
	 * @return the inputReadFile
	 */
	public Part getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the inputReadFile to set
	 */
	public void setInputReadFile(Part inputReadFile) {
		this.inputReadFile = inputReadFile;
	}

	/**
	 * @return the pairedEndModeFile
	 */
	public Part getPairedEndModeFile() {
		return pairedEndModeFile;
	}

	/**
	 * @param pairedEndModeFile the pairedEndModeFile to set
	 */
	public void setPairedEndModeFile(Part pairedEndModeFile) {
		this.pairedEndModeFile = pairedEndModeFile;
	}

	/**
	 * @return the selectedInputReadFile
	 */
	public HPGMethylFile getSelectedInputReadFile() {
		return selectedInputReadFile;
	}

	/**
	 * @param selectedInputReadFile the selectedInputReadFile to set
	 */
	public void setSelectedInputReadFile(HPGMethylFile selectedInputReadFile) {
		this.selectedInputReadFile = selectedInputReadFile;
	}

	/**
	 * @return the selectedPairedEndModeFile
	 */
	public HPGMethylFile getSelectedPairedEndModeFile() {
		return selectedPairedEndModeFile;
	}

	/**
	 * @param selectedPairedEndModeFile the selectedPairedEndModeFile to set
	 */
	public void setSelectedPairedEndModeFile(HPGMethylFile selectedPairedEndModeFile) {
		this.selectedPairedEndModeFile = selectedPairedEndModeFile;
	}

	/**
	 * @return the newInputReadFile
	 */
	public Boolean getNewInputReadFile() {
		return newInputReadFile;
	}

	/**
	 * @param newInputReadFile the newInputReadFile to set
	 */
	public void setNewInputReadFile(Boolean newInputReadFile) {
		this.newInputReadFile = newInputReadFile;
	}

	/**
	 * @return the newPairedReadFile
	 */
	public Boolean getNewPairedReadFile() {
		return newPairedReadFile;
	}

	/**
	 * @param newPairedReadFile the newPairedReadFile to set
	 */
	public void setNewPairedReadFile(Boolean newPairedReadFile) {
		this.newPairedReadFile = newPairedReadFile;
	}

	/**
	 * @return the sendAnalysisComponent
	 */
	public UIComponent getSendAnalysisComponent() {
		return sendAnalysisComponent;
	}

	/**
	 * @param sendAnalysisComponent the sendAnalysisComponent to set
	 */
	public void setSendAnalysisComponent(UIComponent sendAnalysisComponent) {
		this.sendAnalysisComponent = sendAnalysisComponent;
	}

	/**
	 * @return the userFiles
	 */
	public List<HPGMethylFile> getUserFiles() {
		return userFiles;
	}

	/**
	 * @param userFiles the userFiles to set
	 */
	public void setUserFiles(List<HPGMethylFile> userFiles) {
		this.userFiles = userFiles;
	}

	public String sendAnalysis() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null) {
			return "/index";	
		}								
		
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
		
		try {
			
			HPGMethylFile inputHPGMethylFile = null;
			if(newInputReadFile) {				
				inputHPGMethylFile = saveFile(user, inputReadFile, inputReadFile.getSubmittedFileName());
			} else {
				inputHPGMethylFile = selectedInputReadFile;
			}
			
			HPGMethylFile pairedEndHPGMethylFile = null;
			if(analysisRequestBean.getPairedMode() == PairedMode.PAIRED_END_MODE) {
				if(newPairedReadFile) {
					if(pairedEndModeFile != null) {
						String pairedEndModeFileName = pairedEndModeFile.getSubmittedFileName();
						if(pairedEndModeFileName.equals(inputHPGMethylFile.getFileName())) {
							pairedEndHPGMethylFile = inputHPGMethylFile;
						} else {
							pairedEndHPGMethylFile = saveFile(user, pairedEndModeFile, pairedEndModeFileName);
						}
					}
				} else {
					pairedEndHPGMethylFile = selectedPairedEndModeFile;
				}
			}
			
			new CreateMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
				new CreateMethylationAnalysisRequest(
					user,
					analysisRequestBean.getIdentifier(),
					inputHPGMethylFile,
					analysisRequestBean.getWriteMethylationContext(), 
					analysisRequestBean.getPairedMode(),
					pairedEndHPGMethylFile, 
					analysisRequestBean.getPairedMaxDistance(), 
					analysisRequestBean.getPairedMinDistance(),
					analysisRequestBean.getSwaMinimunScore(), 
					analysisRequestBean.getSwaMatchScore(), 
					analysisRequestBean.getSwaMismatchScore(), 
					analysisRequestBean.getSwaGapOpen(),
					analysisRequestBean.getSwaGapExtend(), 
					analysisRequestBean.getCalFlankSize(), 
					analysisRequestBean.getMinimumCalSize(),
					analysisRequestBean.getCalUmbralLengthFactor(), 
					analysisRequestBean.getMaximumBetweenSeeds(), 
					analysisRequestBean.getMaximumSeedSize(),
					analysisRequestBean.getMinimumSeedSize(), 
					analysisRequestBean.getNumberSeedsPerRead(), 
					analysisRequestBean.getReadMinimumDiscardLength(),
					analysisRequestBean.getReadMaximumInnerGap(), 
					analysisRequestBean.getMinimumNumberSeeds(), 
					analysisRequestBean.getFilterReadMappings(),
					analysisRequestBean.getFilterSeedMappings(), 
					analysisRequestBean.getReportAll(), 
					analysisRequestBean.getReportBest(), 
					analysisRequestBean.getReportNBest(),
					analysisRequestBean.getReportNHits()
				)				
			);
			
			String successMessage = FacesContextUtils.geti18nMessage("analysis.send.requestSentSuccessfully");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_INFO, successMessage, successMessage);
			
			new Thread(() -> {
			    new HPGMethylProcessor(new AnalysisRequestDAOHibernate(), new ConfigurationDAOHibernate()).start();
			}).start();

		} catch (DuplicatedFile e) {
			String duplicatedFileMessage = FacesContextUtils.geti18nMessage("error.duplicatedFile");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, duplicatedFileMessage, duplicatedFileMessage);
		} catch (DuplicatedIdentifier e) {
			String duplicatedIdentifierErrorMessage = FacesContextUtils.geti18nMessage("error.duplicatedIdentifier");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, duplicatedIdentifierErrorMessage, duplicatedIdentifierErrorMessage);
		} catch (CreateFileException | CreateMethylationAnalysisException | IOException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} 
		
		return null;
	}
	
	private HPGMethylFile saveFile(User user, Part inputFile, String inputFileName) throws CreateFileException, DuplicatedFile, IOException {
		
		Boolean duplicatedFile = new ExistsFile(new HPGMethylFileDAOHibernate()).execute(
				new ExistsFileRequest(user, inputFileName)
		).getExists();
		
		if(duplicatedFile) {
			throw new DuplicatedFile();
		}
		
		File uploadedFile = FileUtils.saveFileUploadedByUser(user, inputFile, inputFileName);

		HPGMethylFile hpgMethylFile = new CreateFile(new HPGMethylFileDAOHibernate()).execute(
			new CreateFileRequest(
				user,
				uploadedFile.getName(),
				uploadedFile.getAbsolutePath(),
				inputFile.getSize(),
				inputFile.getContentType()
			)	
		).getFile();	
		
		return hpgMethylFile;
	}
}
