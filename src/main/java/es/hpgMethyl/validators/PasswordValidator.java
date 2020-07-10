package es.hpgMethyl.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import es.hpgMethyl.utils.FacesContextUtils;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {
	
	private Pattern pattern;
	
	private final static String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

	public PasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String password = (String) value;
		
		Matcher matcher = pattern.matcher(password);
		
		if(!matcher.matches()) {
			
			String invalidPasswordFormatMessage = FacesContextUtils.geti18nMessage("signup.invalidPasswordFormat");
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, invalidPasswordFormatMessage, invalidPasswordFormatMessage);
			
			throw new ValidatorException(message);
		}		
	}

}
