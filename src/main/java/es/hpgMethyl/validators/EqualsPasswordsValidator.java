package es.hpgMethyl.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import es.hpgMethyl.utils.FacesContextUtils;

@FacesValidator("equalsPasswordsValidator")
public class EqualsPasswordsValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String password =  (String) component.getAttributes().get("password");
					
		String passwordVerification = (String) value;
		
		if(!password.isEmpty() && !passwordVerification.equals(password)) {
			
			String errorMessage = FacesContextUtils.geti18nMessage("signup.passwordsNotEquals");
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			
			((UIInput) component).setSubmittedValue("");
			
			throw new ValidatorException(message);
		}		
	}
}
