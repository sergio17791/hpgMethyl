package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="languageSelector")
@SessionScoped
public class LanguageSelector implements Serializable {
	
	private static final long serialVersionUID = 8026391569352704332L;
	
	private Locale language;
    
	@PostConstruct
	protected void init() {
        this.language = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}

	/**
	 * @return the language
	 */
	public Locale getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(Locale language) {
		this.language = language;
	}
	
	/**
	 * Modify the current language of the application
	 * @param code The new language code to be set
	 */
    public void changeLanguage(String code) {
        this.language = new Locale(code);       
        FacesContext.getCurrentInstance().getViewRoot().setLocale(language);
    }
}
