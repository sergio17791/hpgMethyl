package es.hpgMethyl.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import es.hpgMethyl.utils.FacesContextUtils;

@FacesValidator("equalsEmailsValidator")
public class EqualsEmailsValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String email =  (String) component.getAttributes().get("email");
			
		String emailVerification = (String) value;
		
		if(!emailVerification.equals(email)) {
			
			String errorMessage = FacesContextUtils.geti18nMessage("signup.emailsNotEquals");
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			
			throw new ValidatorException(message);
		}		
	}
}
