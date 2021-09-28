  package es.hpgMethyl.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.AnalysisResultDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.AnalysisRequestProcessed;
import es.hpgMethyl.exceptions.AnalysisResultNotFound;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UpdateFileException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisResponse;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis.ListPendingMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis.ListPendingMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParameters;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParametersRequest;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFile;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFileRequest;
import es.hpgMethyl.usecases.result.GetMethylationAnalysisResult.GetMethylationAnalysisResult;
import es.hpgMethyl.usecases.result.GetMethylationAnalysisResult.GetMethylationAnalysisResultRequest;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

@ManagedBean(name="analysisDetail")
@ViewScoped
public class MethylationAnalysisDetail implements Serializable {

	private static final long serialVersionUID = 929869883425786237L;

	private String id;	
	private AnalysisRequest analysisRequest;
	private AnalysisResult analysisResult;
	private Boolean downloaded;
	private StreamedContent file;
	private UIComponent updateAnalysisParametersComponent;
	private UIComponent downloadButtonComponent;
		
	public MethylationAnalysisDetail() {
		this.id = null;
		this.analysisRequest = null;
		this.analysisResult = null;
		this.downloaded = Boolean.TRUE;
		this.file = new DefaultStreamedContent();;
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
	 * @return the analysisResult
	 */
	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}

	/**
	 * @param analysisResult the analysisResult to set
	 */
	public void setAnalysisResult(AnalysisResult analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the downloaded
	 */
	public Boolean getDownloaded() {
		return downloaded;
	}

	/**
	 * @param downloaded the downloaded to set
	 */
	public void setDownloaded(Boolean downloaded) {
		this.downloaded = downloaded;
	}

	/**
	 * @return the file
	 */
	public StreamedContent getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(StreamedContent file) {
		this.file = file;
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
	 * @return the downloadButtonComponent
	 */
	public UIComponent getDownloadButtonComponent() {
		return downloadButtonComponent;
	}

	/**
	 * @param downloadButtonComponent the downloadButtonComponent to set
	 */
	public void setDownloadButtonComponent(UIComponent downloadButtonComponent) {
		this.downloadButtonComponent = downloadButtonComponent;
	}

	/**
	 * @return the pairedModes
	 */
	public PairedMode[] getPairedModes() {
		return PairedMode.values();
	}

	public String loadAnalysisDetail() {
		
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			
			if(this.id == null) {
				return "pretty:home";
			}
			
			try {
				GetMethylationAnalysisResponse getAnalysisResponse = new GetMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
						new GetMethylationAnalysisRequest(UUID.fromString(this.id))
				);
					
				this.analysisRequest = getAnalysisResponse.getAnalysisRequest();
				
				User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
				
				if(user == null) {
					return "pretty:home";	
				}
				
				if(user.getRole() == UserRole.USER && !this.analysisRequest.getUser().getId().equals(user.getId())) {
					return "pretty:home";
				}	
				
				AnalysisStatus status = analysisRequest.getStatus();
					
				AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
				analysisRequestBean.loadAnalysisRequest(analysisRequest);
				
				if(status.equals(AnalysisStatus.CREATED)) {
					analysisRequestBean.loadUserFiles();
				} else if(status.equals(AnalysisStatus.COMPLETED) || status.equals(AnalysisStatus.FAILED)) {
					try {
						this.analysisResult = new GetMethylationAnalysisResult(new AnalysisResultDAOHibernate()).execute(
								new GetMethylationAnalysisResultRequest(analysisRequest)
						).getAnalysisResult();
						
						if(status.equals(AnalysisStatus.COMPLETED)) {
							HPGMethylFile resultFile = analysisResult.getResultFile();
							
							if(resultFile != null) {
								this.downloaded = !resultFile.getStored();
								
								this.file = buildDownloadFile(resultFile);
							}
						}
																			
						AnalysisResultBean analysisResultBean = (AnalysisResultBean) FacesContextUtils.getBean("analysisResultBean");
						analysisResultBean.loadAnalysisResult(analysisResult, status);
						
					} catch (AnalysisResultNotFound | FileNotFoundException e) {
						
					}
				}
								
			} catch (AnalysisRequestNotFound | GetObjectException e) {
				return "pretty:home";
			}						
		}
		
		return null;	
	}
	
	public void updateAnalysisParameters() {
		
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
		
		PairedMode pairedMode = analysisRequestBean.getPairedMode();
		
		if(pairedMode.equals(PairedMode.SINGLE_END_MODE)) {
			analysisRequestBean.setPairedEndModeFile(null);
			analysisRequestBean.setPairedMaxDistance(null);
			analysisRequestBean.setPairedMinDistance(null);
		}
		
		HPGMethylFile inputReadFile = analysisRequestBean.getInputReadFile();		
		Boolean validInputReadFile = analysisRequestBean.checkFile(inputReadFile);
		if(!validInputReadFile) {
			String fileNotFoundErrorMessage = FacesContextUtils.geti18nMessage("error.fileNotFound") + " " + inputReadFile.getFileName();
			FacesContextUtils.setMessageInComponent(this.getUpdateAnalysisParametersComponent(), FacesMessage.SEVERITY_ERROR, fileNotFoundErrorMessage, fileNotFoundErrorMessage);
		}
		
		HPGMethylFile pairedEndModeFile = analysisRequestBean.getPairedEndModeFile();		
		if(pairedEndModeFile != null) {
			Boolean validPairedEndModeFile = analysisRequestBean.checkFile(pairedEndModeFile);
			if(!validPairedEndModeFile) {
				String fileNotFoundErrorMessage = FacesContextUtils.geti18nMessage("error.fileNotFound") + " " + pairedEndModeFile.getFileName();
				FacesContextUtils.setMessageInComponent(this.getUpdateAnalysisParametersComponent(), FacesMessage.SEVERITY_ERROR, fileNotFoundErrorMessage, fileNotFoundErrorMessage);
			}
		}
		
		String normalizedIdentifier = analysisRequestBean.getIdentifier().trim().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
		
		try {
			new UpdateMethylationAnalysisParameters(new AnalysisRequestDAOHibernate()).execute(
					new UpdateMethylationAnalysisParametersRequest(
						UUID.fromString(this.id),
						normalizedIdentifier,
						inputReadFile,
						analysisRequestBean.getWriteMethylationContext(), 
						analysisRequestBean.getPairedMode(),
						pairedEndModeFile,
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
			
			removeFile(analysisRequest.getInputReadFile());
			removeFile(analysisRequest.getPairedEndModeFile());
			
			analysisRequestBean.loadUserFiles();
			
			String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
			
		} catch (DuplicatedIdentifier e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.duplicatedIdentifier");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} catch (AnalysisRequestNotFound | AnalysisRequestProcessed | UpdateMethylationAnalysisException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} catch (FileNotFound | UpdateFileException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.updateAnalysisParametersComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} 
	}
	
	public void downloadFile() {
		
		HPGMethylFile file = analysisResult.getResultFile();
		
		try {
			new UnstoreFile(new HPGMethylFileDAOHibernate()).execute(
					new UnstoreFileRequest(file.getId())
			);
			
			this.downloaded = Boolean.TRUE;
			
			FileUtils.delete(file.getPath());
			
		} catch (FileNotFound | UpdateFileException e) {
			String errorDefault = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getDownloadButtonComponent(), FacesMessage.SEVERITY_ERROR, errorDefault, errorDefault);
		} 
	}
	
	private void removeFile(HPGMethylFile file) throws FileNotFound, UpdateFileException {
		
		if(file != null) {
			List<AnalysisRequest> analysisWithFile = new ListPendingMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
					new ListPendingMethylationAnalysisRequest(file.getUser(), file)
			).getAnalysisRequestList();
			
			if(analysisWithFile.isEmpty()) {
				new UnstoreFile(new HPGMethylFileDAOHibernate()).execute(
						new UnstoreFileRequest(file.getId())
				);
						
				FileUtils.delete(file.getPath());					
			}
		}		
	}
	
	private StreamedContent buildDownloadFile(HPGMethylFile resultFile) throws FileNotFoundException {
		
		StreamedContent download = new DefaultStreamedContent();
		
		if(resultFile.getStored()) {
			
			download = DefaultStreamedContent.builder()
					.name(resultFile.getFileName())
					.contentType(resultFile.getContentType()).stream(
							() -> { 
								try {
									return new FileInputStream(new File(resultFile.getPath())) {
									    @Override
									    public void close() throws IOException {
									        super.close();
									        downloadFile();   
									    }
									};
	                            } catch (FileNotFoundException e) {
	                                e.printStackTrace();
	                            }
								
								return null;
							}
					).build();
		}	
		
		return download;
	}
}
