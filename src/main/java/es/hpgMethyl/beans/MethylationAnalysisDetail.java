  package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.GetMethylationAnalysis.GetMethylationAnalysisResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="analysisDetail")
@RequestScoped
public class MethylationAnalysisDetail implements Serializable {

	private static final long serialVersionUID = 929869883425786237L;

	@ManagedProperty(value="#{param.id}")
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

	public void loadAnalysisDetail() {
		
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			if(id != null) {
				try {
					GetMethylationAnalysisResponse response = new GetMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
							new GetMethylationAnalysisRequest(UUID.fromString(this.id))
					);
					
					analysisRequest = response.getAnalysisRequest();
					
					AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
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
					
				}		
			}
		}		
	}
	
	public String updateAnalysisParameters() {
		
		String successMessage = FacesContextUtils.geti18nMessage("analysis.send.requestSentSuccessfully");
		FacesContextUtils.setMessageInComponent(this.getUpdateAnalysisParametersComponent(), FacesMessage.SEVERITY_INFO, successMessage, successMessage);
		
		return "detail?faces-redirect=true&includeViewParams=true";
	}
}
