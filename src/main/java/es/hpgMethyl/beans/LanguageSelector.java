package es.hpgMethyl.beans;

import java.util.Locale;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

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
	 * @param code The new language code to be set
	 */
    public void changeLanguage(String code) {

        Locale newLanguage = new Locale(code);
        this.setLanguage(newLanguage);
        
        FacesContext context = FacesContext.getCurrentInstance();
        String refreshPage = context.getViewRoot().getViewId();

        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot viewRoot = handler.createView(context, refreshPage);
        viewRoot.setLocale(this.getLanguage());
        
        viewRoot.setViewId(refreshPage);
        context.setViewRoot(viewRoot);
    }
}
