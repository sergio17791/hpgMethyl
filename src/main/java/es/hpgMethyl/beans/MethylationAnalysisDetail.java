  package es.hpgMethyl.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.AnalysisRequestProcessed;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisResponse;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisInputFile.UpdateMethylationAnalysisInputFile;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisInputFile.UpdateMethylationAnalysisInputFileRequest;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParameters;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParametersRequest;
import es.hpgMethyl.usecases.file.CreateFile.CreateFile;
import es.hpgMethyl.usecases.file.CreateFile.CreateFileRequest;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFile;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFileRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

@ManagedBean(name="analysisDetail")
@RequestScoped
public class MethylationAnalysisDetail implements Serializable {

	private static final long serialVersionUID = 929869883425786237L;

	private String id;	
	private AnalysisRequest analysisRequest;
	private Part inputReadFile;
	private Part pairedEndModeFile;
	private HPGMethylFile selectedInputReadFile;
	private HPGMethylFile selectedPairedEndModeFile;
	private Boolean newInputReadFile;
	private Boolean newPairedReadFile;
	private UIComponent updateAnalysisParametersComponent;
	private UIComponent updateInputFileComponent;
	private List<HPGMethylFile> userFiles;
		
	public MethylationAnalysisDetail() {
		this.id = null;
		this.analysisRequest = null;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}	

	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}

	/**
	 * @param analysisRequest the analysisRequest to set
	 */
	public void setAnalysisRequest(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
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
	 * @return the updateAnalysisParametersComponent
	 */
	public UIComponent getUpdateAnalysisParametersComponent() {
		return updateAnalysisParametersComponent;
	}

	/**
	 * @param updateAnalysisParametersComponent the updateAnalysisParametersComponent to set
	 */
	public void setUpdateAnalysisParametersComponent(UIComponent updateAnalysisParametersComponent) {
		this.updateAnalysisParametersComponent = updateAnalysisParametersComponent;
	}

	/**
	 * @return the updateInputFileComponent
	 */
	public UIComponent getUpdateInputFileComponent() {
		return updateInputFileComponent;
	}

	/**
	 * @param updateInputFileComponent the updateInputFileComponent to set
	 */
	public void setUpdateInputFileComponent(UIComponent updateInputFileComponent) {
		this.updateInputFileComponent = updateInputFileComponent;
	}

	/**
	 * @return the pairedModes
	 */
	public PairedMode[] getPairedModes() {
		return PairedMode.values();
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

	public String loadAnalysisDetail() {
		
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			
			if(this.id == null) {
				return "pretty:home";
			}
			
			try {
				GetMethylationAnalysisResponse response = new GetMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
						new GetMethylationAnalysisRequest(UUID.fromString(this.id))
				);
					
				this.analysisRequest = response.getAnalysisRequest();
				
				User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
				
				if(user == null) {
					return "pretty:home";	
				}
				
				if(user.getRole() == UserRole.USER && !this.analysisRequest.getUser().getId().equals(user.getId())) {
					return "pretty:home";
				}
					
				AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
				analysisRequestBean.setId(analysisRequest.getId());
				analysisRequestBean.setUser(analysisRequest.getUser());
				analysisRequestBean.setIdentifier(analysisRequest.getIdentifier());
				analysisRequestBean.setStatus(analysisRequest.getStatus());
				analysisRequestBean.setPairedMode(analysisRequest.getPairedMode());
				analysisRequestBean.setInputReadFile(analysisRequest.getInputReadFile());
				analysisRequestBean.setWriteMethylationContext(analysisRequest.getWriteMethylationContext());
				analysisRequestBean.setReadBatchSize(analysisRequest.getReadBatchSize());
				analysisRequestBean.setWriteBatchSize(analysisRequest.getWriteBatchSize());
				analysisRequestBean.setPairedEndModeFile(analysisRequest.getPairedEndModeFile());
				analysisRequestBean.setPairedMaxDistance(analysisRequest.getPairedMaxDistance());
				analysisRequestBean.setPairedMinDistance(analysisRequest.getPairedMinDistance());
				analysisRequestBean.setSwaMinimunScore(analysisRequest.getSwaMinimunScore());
				analysisRequestBean.setSwaMatchScore(analysisRequest.getSwaMatchScore());
				analysisRequestBean.setSwaMismatchScore(analysisRequest.getSwaMismatchScore());
				analysisRequestBean.setSwaGapOpen(analysisRequest.getSwaGapOpen());
				analysisRequestBean.setSwaGapExtend(analysisRequest.getSwaGapExtend());
				analysisRequestBean.setCalFlankSize(analysisRequest.getCalFlankSize());
				analysisRequestBean.setMinimumCalSize(analysisRequest.getMinimumCalSize());
				analysisRequestBean.setCalUmbralLengthFactor(analysisRequest.getCalUmbralLengthFactor());
				analysisRequestBean.setMaximumBetweenSeeds(analysisRequest.getMaximumBetweenSeeds());
				analysisRequestBean.setMaximumSeedSize(analysisRequest.getMaximumSeedSize());
				analysisRequestBean.setMinimumCalSize(analysisRequest.getMinimumCalSize());
				analysisRequestBean.setNumberSeedsPerRead(analysisRequest.getNumberSeedsPerRead());
				analysisRequestBean.setReadMinimumDiscardLength(analysisRequest.getReadMinimumDiscardLength());
				analysisRequestBean.setReadMaximumInnerGap(analysisRequest.getReadMaximumInnerGap());
				analysisRequestBean.setMinimumNumberSeeds(analysisRequest.getMinimumNumberSeeds());
				analysisRequestBean.setFilterReadMappings(analysisRequest.getFilterReadMappings());
				analysisRequestBean.setFilterSeedMappings(analysisRequest.getFilterSeedMappings());
				analysisRequestBean.setReportAll(analysisRequest.getReportAll());
				analysisRequestBean.setReportBest(analysisRequest.getReportBest());
				analysisRequestBean.setReportNBest(analysisRequest.getReportNBest());
				analysisRequestBean.setReportNHits(analysisRequest.getReportNHits());
				analysisRequestBean.setNumber(analysisRequest.getNumber());		
				analysisRequestBean.setCreatedAt(analysisRequest.getCreatedAt());
				analysisRequestBean.setUpdatedAt(analysisRequest.getUpdatedAt());
				
			} catch (AnalysisRequestNotFound | GetObjectException e) {
				return "pretty:home";
			}						
		}
		
		return null;	
	}
	
	public String updateAnalysisParameters() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null) {
			return "pretty:home";	
		}
				
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
		
		try {
			new UpdateMethylationAnalysisParameters(new AnalysisRequestDAOHibernate()).execute(
					new UpdateMethylationAnalysisParametersRequest(
						UUID.fromString(this.id),
						analysisRequestBean.getIdentifier(),
						analysisRequestBean.getWriteMethylationContext(), 
						analysisRequestBean.getReadBatchSize(), 
						analysisRequestBean.getWriteBatchSize(), 
						analysisRequestBean.getPairedMode(),
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
			
			analysisRequestBean.setUpdatedAt(new Date());
			
			String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
			
		} catch (DuplicatedIdentifier e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.duplicatedIdentifier");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} catch (AnalysisRequestNotFound | AnalysisRequestProcessed | UpdateMethylationAnalysisException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		}
				
		return null;
	}
	
	public String updateInputFile() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null) {
			return "pretty:home";	
		}
				
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");				
		
		try {
			HPGMethylFile inputHPGMethylFile = null;
			if(newInputReadFile) {				
				inputHPGMethylFile = saveFile(user, inputReadFile, inputReadFile.getSubmittedFileName());
			} else {
				inputHPGMethylFile = selectedInputReadFile;
			}
			
			new UpdateMethylationAnalysisInputFile(new AnalysisRequestDAOHibernate()).execute(
					new UpdateMethylationAnalysisInputFileRequest(
						UUID.fromString(this.id),
						inputHPGMethylFile
					)				
			);
			
			analysisRequestBean.setUpdatedAt(new Date());
			analysisRequestBean.setInputReadFile(inputHPGMethylFile);
			
			String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
			FacesContextUtils.setMessageInComponent(this.updateInputFileComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
			
		} catch (DuplicatedFile e) {
			String duplicatedFileMessage = FacesContextUtils.geti18nMessage("error.duplicatedFile");
			FacesContextUtils.setMessageInComponent(this.updateInputFileComponent, FacesMessage.SEVERITY_ERROR, duplicatedFileMessage, duplicatedFileMessage);
		} catch (AnalysisRequestNotFound | AnalysisRequestProcessed | CreateFileException| IOException| UpdateMethylationAnalysisException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.updateInputFileComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
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
