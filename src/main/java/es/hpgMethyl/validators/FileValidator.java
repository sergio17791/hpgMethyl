package es.hpgMethyl.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFile;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFileRequest;
import es.hpgMethyl.utils.FacesContextUtils;

@FacesValidator("fileValidator")
public class FileValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user != null) {
			
			Part file = (Part) value;
			
			Boolean duplicatedFile = new ExistsFile(new HPGMethylFileDAOHibernate()).execute(
					new ExistsFileRequest(user, file.getSubmittedFileName())
			).getExists();
			
			if(duplicatedFile) {
				String invalidPasswordFormatMessage = FacesContextUtils.geti18nMessage("error.duplicatedFile");				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, invalidPasswordFormatMessage, invalidPasswordFormatMessage);
				throw new ValidatorException(message);
			}
		}		
	}

}
