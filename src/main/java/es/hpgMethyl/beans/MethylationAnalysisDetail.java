  package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.AnalysisRequestProcessed;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisResponse;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParameters;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParametersRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="analysisDetail")
@ViewScoped
public class MethylationAnalysisDetail implements Serializable {

	private static final long serialVersionUID = 929869883425786237L;

	private String id;	
	private AnalysisRequest analysisRequest;
	private UIComponent updateAnalysisParametersComponent;
		
	public MethylationAnalysisDetail() {
		this.id = null;
		this.analysisRequest = null;
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
				
				ListUserFilesResponse listUserFilesResponse = new ListUserFiles(new HPGMethylFileDAOHibernate()).execute(
						new ListUserFilesRequest(analysisRequest.getUser(), true)
				);
					
				AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
				analysisRequestBean.setUserFiles(listUserFilesResponse.getFiles());
				analysisRequestBean.setId(analysisRequest.getId());
				analysisRequestBean.setUser(analysisRequest.getUser());
				analysisRequestBean.setIdentifier(analysisRequest.getIdentifier());
				analysisRequestBean.setInputReadFile(analysisRequest.getInputReadFile());
				analysisRequestBean.setStatus(analysisRequest.getStatus());
				analysisRequestBean.setPairedMode(analysisRequest.getPairedMode());
				analysisRequestBean.setWriteMethylationContext(analysisRequest.getWriteMethylationContext());
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
	
	public void updateAnalysisParameters() {
		
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
		
		try {
			new UpdateMethylationAnalysisParameters(new AnalysisRequestDAOHibernate()).execute(
					new UpdateMethylationAnalysisParametersRequest(
						UUID.fromString(this.id),
						analysisRequestBean.getIdentifier(),
						analysisRequestBean.getInputReadFile(),
						analysisRequestBean.getWriteMethylationContext(), 
						analysisRequestBean.getPairedMode(),
						analysisRequestBean.getPairedEndModeFile(),
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
	}
}
