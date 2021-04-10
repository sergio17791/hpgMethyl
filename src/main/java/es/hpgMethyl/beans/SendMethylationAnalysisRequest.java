package es.hpgMethyl.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.servlet.http.Part;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisRequest;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

@ManagedBean(name="sendAnalysis")
@RequestScoped
public class SendMethylationAnalysisRequest implements Serializable {
	
	private static final long serialVersionUID = -589016682649578821L;

	private Part inputReadFile;
	private Part pairedEndModeFile;
	private UIComponent sendAnalysisComponent;
	
	public SendMethylationAnalysisRequest() {
		this.inputReadFile = null;
		this.pairedEndModeFile = null;
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
	
	public String sendAnalysis() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null) {
			return "pretty:home";	
		}
		
		String inputReadFileName = this.getInputReadFile().getSubmittedFileName();
		
		String pairedEndModeFile = null;
		
		if(this.getPairedEndModeFile() != null) {
			pairedEndModeFile = this.getPairedEndModeFile().getSubmittedFileName();
		}
		
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
		
		try {
			FileUtils.saveFileUploadedByUser(user, this.getInputReadFile(), inputReadFileName);
			
			new CreateMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
				new CreateMethylationAnalysisRequest(
					user,
					analysisRequestBean.getIdentifier(),
					inputReadFileName,
					analysisRequestBean.getWriteMethylationContext(), 
					analysisRequestBean.getReadBatchSize(), 
					analysisRequestBean.getWriteBatchSize(), 
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
			
			String successMessage = FacesContextUtils.geti18nMessage("analysis.send.requestSentSuccessfully");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_INFO, successMessage, successMessage);

		} catch (CreateMethylationAnalysisException e) {
			String defaultErrorMessage = e.getMessage();//FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} catch (DuplicatedIdentifier e) {
			String duplicatedIdentifierErrorMessage = e.getMessage();//FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, duplicatedIdentifierErrorMessage, duplicatedIdentifierErrorMessage);
		} catch (IOException e) {
			String defaultErrorMessage = e.getMessage();//FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		}
		
		return null;
	}
}
