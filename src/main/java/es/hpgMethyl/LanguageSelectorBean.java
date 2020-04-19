package es.hpgMethyl;

import java.util.Locale;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @author Sergio Muñoz
 * @version 1.0.0
 * @since 2020-04-19
 */
public class LanguageSelectorBean {
	
	private String codeLanguage;
    private Locale language;
    
    /**
     * Create a new instance of LanguageSelectorBean
     */
	public LanguageSelectorBean() {
		FacesContext context = FacesContext.getCurrentInstance();
        this.language = context.getViewRoot().getLocale();
        if (this.language != null) {
            this.codeLanguage = this.language.getLanguage();
        } else {
            this.codeLanguage = null;
        }
	}

	/**
	 * @return the codeLanguage
	 */
	public String getCodeLanguage() {
		return codeLanguage;
	}

	/**
	 * @param codeLanguage the codeLanguage to set
	 */
	public void setCodeLanguage(String codeLanguage) {
		this.codeLanguage = codeLanguage;
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
    public void changeLang(String newCodeLanguage) {

        this.setCodeLanguage(newCodeLanguage); 
        Locale newLanguage = new Locale(newCodeLanguage);
        this.setLanguage(newLanguage);

        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(this.getLanguage());
    }
}
