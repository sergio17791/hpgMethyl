package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.ConfigurationDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.services.HPGMethylProcessor;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="sendAnalysis")
@RequestScoped
public class SendMethylationAnalysisRequest implements Serializable {
	
	private static final long serialVersionUID = -589016682649578821L;

	private HPGMethylFile inputReadFile;
	private HPGMethylFile pairedEndModeFile;
	private UIComponent sendAnalysisComponent;
	private List<HPGMethylFile> userFiles;
	
	public SendMethylationAnalysisRequest() {
		this.inputReadFile = null;
		this.pairedEndModeFile = null;
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
	public HPGMethylFile getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the inputReadFile to set
	 */
	public void setInputReadFile(HPGMethylFile inputReadFile) {
		this.inputReadFile = inputReadFile;
	}

	/**
	 * @return the pairedEndModeFile
	 */
	public HPGMethylFile getPairedEndModeFile() {
		return pairedEndModeFile;
	}

	/**
	 * @param pairedEndModeFile the pairedEndModeFile to set
	 */
	public void setPairedEndModeFile(HPGMethylFile pairedEndModeFile) {
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
			
			new CreateMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
				new CreateMethylationAnalysisRequest(
					user,
					analysisRequestBean.getIdentifier(),
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
			
			String successMessage = FacesContextUtils.geti18nMessage("analysis.send.requestSentSuccessfully");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_INFO, successMessage, successMessage);
			
			new Thread(() -> {
			    new HPGMethylProcessor(new AnalysisRequestDAOHibernate(), new ConfigurationDAOHibernate()).start();
			}).start();

		} catch (DuplicatedIdentifier e) {
			String duplicatedIdentifierErrorMessage = FacesContextUtils.geti18nMessage("error.duplicatedIdentifier");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, duplicatedIdentifierErrorMessage, duplicatedIdentifierErrorMessage);
		} catch (CreateMethylationAnalysisException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} 
		
		return null;
	}
}
