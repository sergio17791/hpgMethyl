package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UpdateUserException;
import es.hpgMethyl.exceptions.UserNotFound;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.user.ActivateUser.ActivateUser;
import es.hpgMethyl.usecases.user.ActivateUser.ActivateUserRequest;
import es.hpgMethyl.usecases.user.ActivateUser.ActivateUserResponse;
import es.hpgMethyl.usecases.user.DeactivateUser.DeactivateUser;
import es.hpgMethyl.usecases.user.DeactivateUser.DeactivateUserRequest;
import es.hpgMethyl.usecases.user.DeactivateUser.DeactivateUserResponse;
import es.hpgMethyl.usecases.user.GetUser.GetUser;
import es.hpgMethyl.usecases.user.GetUser.GetUserRequest;
import es.hpgMethyl.usecases.user.GetUser.GetUserResponse;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformation;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformationRequest;
import es.hpgMethyl.usecases.user.UpdateUserPersonalInformation.UpdateUserPersonalInformationResponse;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="userDetail")
@ViewScoped
public class UserDetail implements Serializable {

	private static final long serialVersionUID = 7201339289224834858L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean active;
	private String defaultLanguage;
	private UserRole role;
	private Date createdAt;
	private Date updatedAt;
	private Boolean enabledEdition;
	private UIComponent updateUserComponent;
	private UIComponent deactivateUserComponent;
	private UIComponent activateUserComponent;
	
	public UserDetail() {
		this.id = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.active = null;
		this.defaultLanguage = null;
		this.role = null;
		this.createdAt = null;
		this.updatedAt = null;
		this.enabledEdition = false;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the defaultLanguage
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * @param defaultLanguage the defaultLanguage to set
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the enabledEdition
	 */
	public Boolean getEnabledEdition() {
		return enabledEdition;
	}

	/**
	 * @param enabledEdition the enabledEdition to set
	 */
	public void setEnabledEdition(Boolean enabledEdition) {
		this.enabledEdition = enabledEdition;
	}

	/**
	 * @return the updateUserComponent
	 */
	public UIComponent getUpdateUserComponent() {
		return updateUserComponent;
	}

	/**
	 * @param updateUserComponent the updateUserComponent to set
	 */
	public void setUpdateUserComponent(UIComponent updateUserComponent) {
		this.updateUserComponent = updateUserComponent;
	}

	/**
	 * @return the deactivateUserComponent
	 */
	public UIComponent getDeactivateUserComponent() {
		return deactivateUserComponent;
	}

	/**
	 * @param deactivateUserComponent the deactivateUserComponent to set
	 */
	public void setDeactivateUserComponent(UIComponent deactivateUserComponent) {
		this.deactivateUserComponent = deactivateUserComponent;
	}

	/**
	 * @return the activateUserComponent
	 */
	public UIComponent getActivateUserComponent() {
		return activateUserComponent;
	}

	/**
	 * @param activateUserComponent the activateUserComponent to set
	 */
	public void setActivateUserComponent(UIComponent activateUserComponent) {
		this.activateUserComponent = activateUserComponent;
	}

	public String loadUserDetail() {
		
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			
			if(this.id == null) {
				return "/admin/index";
			}
			
			try {
				GetUserResponse response = new GetUser(new UserDAOHibernate()).execute(
						new GetUserRequest(UUID.fromString(id))
				);
				
				User user = response.getUser();
				
				enableEdition(user);
				
				this.firstName = user.getFirstName();
				this.lastName = user.getLastName();
				this.email = user.getEmail();
				this.active = user.getActive();
				this.defaultLanguage = user.getDefaultLanguage();
				this.role = user.getRole();
				this.createdAt = user.getCreatedAt();
				this.updatedAt = user.getUpdatedAt();
				
			} catch (GetObjectException | UserNotFound e) {
				return "/admin/index";
			}
		}
		
		return null;
	}
	
	private void enableEdition(User requestedUser) {
	
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
				
		if(user != null && user.getRole() != UserRole.USER) {
			if(user.getId().equals(requestedUser.getId())) {
				this.enabledEdition = true;
			} else if(user.getRole() == UserRole.ADMIN && requestedUser.getRole() != UserRole.ADMIN) {
				this.enabledEdition = true;
			} else if(user.getRole() == UserRole.MODERATOR && requestedUser.getRole() == UserRole.USER) {
				this.enabledEdition = true;
			}
		}
	}
	
	public String updateUser() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
        
        if(user == null || !enabledEdition) {
            return "admin";   
        }
        
        try {
        	UpdateUserPersonalInformationResponse response = new UpdateUserPersonalInformation(new UserDAOHibernate()).execute(
                    new UpdateUserPersonalInformationRequest(
                    	UUID.fromString(id),
                        firstName,
                        lastName,
                        defaultLanguage,
                        role
                    )
            );
        	
        	User updatedUser = response.getUser();
        	
        	if(user.getId().equals(updatedUser.getId())) {
        		FacesContextUtils.setParameterFacesContextSession(FacesContextUtils.SESSION_USER, updatedUser);
        	}
        	
        	this.updatedAt = updatedUser.getUpdatedAt();
                        
            String successMessage = FacesContextUtils.geti18nMessage("general.updateSuccessfully");
            FacesContextUtils.setMessageInComponent(this.updateUserComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
            
        } catch (UserNotFound | UpdateUserException e) {
            String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
            FacesContextUtils.setMessageInComponent(this.updateUserComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
        }   
        
        return null;
	}
	
	public String deactivateUser() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
        
        if(user == null || !enabledEdition) {
            return "/admin/index";   
        }
        
        try {
        	DeactivateUserResponse response = new DeactivateUser(new UserDAOHibernate()).execute(
                    new DeactivateUserRequest(UUID.fromString(id))
            );
        	
        	User deactivatedUser = response.getUser();
        	
        	if(user.getId().equals(deactivatedUser.getId())) {
        		FacesContextUtils.invalidateSession();	
        		return "login"; 
        	}
        	
        	this.active = deactivatedUser.getActive();
                        
            String successMessage = FacesContextUtils.geti18nMessage("admin.users.detail.deactivate.successfully");
            FacesContextUtils.setMessageInComponent(this.deactivateUserComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
            
        } catch (UserNotFound | UpdateUserException e) {
            String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
            FacesContextUtils.setMessageInComponent(this.deactivateUserComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
        } 
		
		return null;
	}
	
	public String activateUser() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
        
        if(user == null || !enabledEdition) {
            return "/admin/index";   
        }
        
        try {
        	ActivateUserResponse response = new ActivateUser(new UserDAOHibernate()).execute(
                    new ActivateUserRequest(UUID.fromString(id))
            );
        	
        	User activatedUser = response.getUser();
        	        	
        	this.active = activatedUser.getActive();
                        
            String successMessage = FacesContextUtils.geti18nMessage("admin.users.detail.activate.successfully");
            FacesContextUtils.setMessageInComponent(this.deactivateUserComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
            
        } catch (UserNotFound | UpdateUserException e) {
            String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
            FacesContextUtils.setMessageInComponent(this.deactivateUserComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
        } 
		
		return null;
	}
}