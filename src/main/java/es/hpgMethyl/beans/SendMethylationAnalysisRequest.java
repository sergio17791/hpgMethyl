package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.ConfigurationDAOHibernate;
import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.services.HPGMethylProcessor;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis.ListPendingMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListPendingMethylationAnalysis.ListPendingMethylationAnalysisRequest;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="sendAnalysis")
@RequestScoped
public class SendMethylationAnalysisRequest implements Serializable {
	
	private static final long serialVersionUID = -589016682649578821L;

	private UIComponent sendAnalysisComponent;
		
	public String loadFiles() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null) {
			return "pretty:home";	
		}		
			
		AnalysisRequestBean analysisRequestBean = (AnalysisRequestBean) FacesContextUtils.getBean("analysisBean");
		analysisRequestBean.setUser(user);
		analysisRequestBean.loadUserFiles();
		
		return null;			
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
		
		ApplicationConfiguration applicationConfiguration = (ApplicationConfiguration) FacesContextUtils.getBean("applicationConfiguration");
		
		Integer maximumUserAnalysis = applicationConfiguration.getMaximumUserAnalysis();
		
		if(maximumUserAnalysis != null) {
			List<AnalysisRequest> pendingUserAnalysis = new ListPendingMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
					new ListPendingMethylationAnalysisRequest(user, null)
			).getAnalysisRequestList();
			
			if(maximumUserAnalysis.intValue() <= pendingUserAnalysis.size()) {
				String maximumUserAnalysisErrorMessage = FacesContextUtils.geti18nMessage("analysis.send.maximumUserAnalysisError");
				FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, maximumUserAnalysisErrorMessage, maximumUserAnalysisErrorMessage);
				return null;
			}
		}
		
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
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, fileNotFoundErrorMessage, fileNotFoundErrorMessage);
			return null;
		}
		
		HPGMethylFile pairedEndModeFile = analysisRequestBean.getPairedEndModeFile();		
		if(pairedEndModeFile != null) {
			Boolean validPairedEndModeFile = analysisRequestBean.checkFile(pairedEndModeFile);
			if(!validPairedEndModeFile) {
				String fileNotFoundErrorMessage = FacesContextUtils.geti18nMessage("error.fileNotFound") + " " + pairedEndModeFile.getFileName();
				FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, fileNotFoundErrorMessage, fileNotFoundErrorMessage);
				return null;
			}
		}
	
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
			
			new Thread(() -> {
			    new HPGMethylProcessor(new AnalysisRequestDAOHibernate(), new HPGMethylFileDAOHibernate(), new ConfigurationDAOHibernate()).start();
			}).start();

			return "pretty:analysis";
			
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
