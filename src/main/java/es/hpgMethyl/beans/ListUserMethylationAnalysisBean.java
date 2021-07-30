package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis.ListUserMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis.ListUserMethylationAnalysisRequest;
import es.hpgMethyl.usecases.analysis.ListUserMethylationAnalysis.ListUserMethylationAnalysisResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="listUserAnalysis")
@RequestScoped
public class ListUserMethylationAnalysisBean implements Serializable {

	private static final long serialVersionUID = 6123081117058258476L;
	
	private List<AnalysisRequest> analysisRequestLists;
	
	public ListUserMethylationAnalysisBean() {
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
			ListUserMethylationAnalysisResponse response = new ListUserMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
					new ListUserMethylationAnalysisRequest(user)
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
