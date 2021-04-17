  package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.AnalysisRequestProcessed;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UpdateMethylationAnalysisException;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisResponse;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParameters;
import es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters.UpdateMethylationAnalysisParametersRequest;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="analysisDetail")
@RequestScoped
public class MethylationAnalysisDetail implements Serializable {

	private static final long serialVersionUID = 929869883425786237L;

	private String id;	
	private AnalysisRequest analysisRequest;
	private Part pairedEndModeFile;
	private UIComponent updateAnalysisParametersComponent;
		
	public MethylationAnalysisDetail() {
		this.id = null;
		this.analysisRequest = null;
		this.pairedEndModeFile = null;
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
				GetMethylationAnalysisResponse response = new GetMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
						new GetMethylationAnalysisRequest(UUID.fromString(this.id))
				);
					
				this.analysisRequest = response.getAnalysisRequest();
				
				User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
				
				if(user == null) {
					return "pretty:home";	
				}
				
				if(!this.analysisRequest.getUser().getId().equals(user.getId())) {
					return "pretty:home";
				}
					
				AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
				analysisRequestBean.setId(analysisRequest.getId());
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
}
