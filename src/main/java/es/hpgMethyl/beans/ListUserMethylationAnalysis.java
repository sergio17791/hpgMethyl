package es.hpgMethyl.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysisResponse;
import es.hpgMethyl.utils.FacesContextUtils;

public class ListUserMethylationAnalysis {

	private List<AnalysisRequest> analysisRequestLists;
	
	public ListUserMethylationAnalysis() {
		this.analysisRequestLists = new ArrayList<AnalysisRequest>();
	}

	/**
	 * @return the analysisRequestLists
	 */
	public List<AnalysisRequest> getAnalysisRequestLists() {
		return analysisRequestLists;
	}

	/**
	 * @param analysisRequestLists the analysisRequestLists to set
	 */
	public void setAnalysisRequestLists(List<AnalysisRequest> analysisRequestLists) {
		this.analysisRequestLists = analysisRequestLists;
	}
	
	public void loadAnalysisList(ComponentSystemEvent event) {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user != null) {
			ListMethylationAnalysisResponse response = new ListMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
					new ListMethylationAnalysisRequest(user)
			);
			
			this.analysisRequestLists = response.getAnalysisRequestList();
		}
	}
	
	public String customLanguageTranslation() {
		
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		
		if ("ca".equals(locale.getLanguage())) {
			return "//cdn.datatables.net/plug-ins/1.10.12/i18n/Catalan.json";
		}
			
	    return null;
	 }
}
