package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysisResponse;

@ManagedBean(name="listAnalysis")
@RequestScoped
public class ListMethylationAnalysisBean implements Serializable {
	
	private static final long serialVersionUID = 3046846707604647694L;
	
	private List<AnalysisRequest> analysisRequestLists;
	
	public ListMethylationAnalysisBean() {
		this.analysisRequestLists = new ArrayList<AnalysisRequest>();
	}
	
	@PostConstruct
	public void init() {
			
		try {
			ListMethylationAnalysisResponse response = new ListMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute();
			this.analysisRequestLists = response.getAnalysisRequestList();
		} catch (ListObjectsException e) {
			this.analysisRequestLists = new ArrayList<AnalysisRequest>();
		}			
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
	
	public String customLanguageTranslation() {
		
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		
		if ("ca".equals(locale.getLanguage())) {
			return "//cdn.datatables.net/plug-ins/1.10.12/i18n/Catalan.json";
		}
			
	    return null;
	 }
}
