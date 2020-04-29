package es.hpgMethyl.bean;

import java.util.Locale;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @author Sergio Muñoz
 * @version 1.0.0
 * @since 2020-04-19
 */
public class LanguageSelector {
	
    private Locale language;
    
    /**
     * Create a new instance of LanguageSelectorBean
     */
	public LanguageSelector() {
		FacesContext context = FacesContext.getCurrentInstance();
        this.language = context.getViewRoot().getLocale();
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
	 * @param newCodeLanguage The new language code to be set
	 */
    public void changeLanguage(String code) {

        Locale newLanguage = new Locale(code);
        this.setLanguage(newLanguage);

        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(this.getLanguage());
    }
}
