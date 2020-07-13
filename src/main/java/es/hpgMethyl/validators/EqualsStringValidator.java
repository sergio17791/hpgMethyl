package es.hpgMethyl.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import es.hpgMethyl.utils.FacesContextUtils;

@FacesValidator("equalsStringValidator")
public class EqualsStringValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String string =  (String) component.getAttributes().get("stringToVerify");
		
		String errorMessageName =  (String) component.getAttributes().get("errorMessage");
		
		String stringVerification = (String) value;
		
		if(!stringVerification.equals(string)) {
			
			String errorMessage = FacesContextUtils.geti18nMessage(errorMessageName);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
			
			throw new ValidatorException(message);
		}		
	}

}
