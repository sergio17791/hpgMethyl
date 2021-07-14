package es.hpgMethyl.beans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;

import es.hpgMethyl.dao.hibernate.ConfigurationDAOHibernate;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ConfigurationNotFound;
import es.hpgMethyl.exceptions.SaveApplicationConfigurationException;
import es.hpgMethyl.types.UserRole;
import es.hpgMethyl.usecases.configuration.GetApplicationConfiguration.GetApplicationConfiguration;
import es.hpgMethyl.usecases.configuration.GetApplicationConfiguration.GetApplicationConfigurationResponse;
import es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration.SaveApplicationConfiguration;
import es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration.SaveApplicationConfigurationRequest;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="applicationConfiguration")
@RequestScoped
public class ApplicationConfiguration implements Serializable {

	private static final long serialVersionUID = 8144704243737428086L;
	
	private String bwtIndexAbsolutePath;
	private String hpgMethylAbsolutePath;
	private String userAbsolutePath;
	private Integer cpuThreads;
	private Integer maximumUserAnalysis;
	private Integer maximumUserFiles;
	private Integer readBatchSize;
	private Integer writeBatchSize;
	private Date createdAt;
	private Date updatedAt;
	private UIComponent sendApplicationConfigurationComponent;

	public ApplicationConfiguration() {
		this.bwtIndexAbsolutePath = null;
		this.hpgMethylAbsolutePath = null;
		this.userAbsolutePath = null;
		this.cpuThreads = null;
		this.maximumUserAnalysis = null;
		this.maximumUserFiles = null;
		this.readBatchSize = null;
		this.writeBatchSize = null;
		this.createdAt = null;
		this.updatedAt = null;
	}
	
	@PostConstruct
	public void init() {
		
		try {
			GetApplicationConfigurationResponse response = new GetApplicationConfiguration(new ConfigurationDAOHibernate()).execute();
			
			Configuration configuration = response.getConfiguration();
			this.bwtIndexAbsolutePath = configuration.getBwtIndexAbsolutePath();
			this.hpgMethylAbsolutePath = configuration.getHpgMethylAbsolutePath();
			this.userAbsolutePath = configuration.getUsersDirectoryAbsolutePath();
			this.cpuThreads = configuration.getCpuThreads();
			this.maximumUserAnalysis = configuration.getMaximumUserAnalysisPending();
			this.maximumUserFiles = configuration.getMaximumUserFilesStored();
			this.readBatchSize = configuration.getReadBatchSize();
			this.writeBatchSize = configuration.getWriteBatchSize();
			this.createdAt = configuration.getCreatedAt();
			this.updatedAt = configuration.getUpdatedAt();
			
		} catch (ConfigurationNotFound e) {

		}
	}

	/**
	 * @return the bsIndexAbsolutePath
	 */
	public String getBwtIndexAbsolutePath() {
		return bwtIndexAbsolutePath;
	}

	/**
	 * @param bsIndexAbsolutePath the bsIndexAbsolutePath to set
	 */
	public void setBwtIndexAbsolutePath(String bwtIndexAbsolutePath) {
		this.bwtIndexAbsolutePath = bwtIndexAbsolutePath;
	}

	/**
	 * @return the hpgMethylAbsolutePath
	 */
	public String getHpgMethylAbsolutePath() {
		return hpgMethylAbsolutePath;
	}

	/**
	 * @param hpgMethylAbsolutePath the hpgMethylAbsolutePath to set
	 */
	public void setHpgMethylAbsolutePath(String hpgMethylAbsolutePath) {
		this.hpgMethylAbsolutePath = hpgMethylAbsolutePath;
	}

	/**
	 * @return the userAbsolutePath
	 */
	public String getUserAbsolutePath() {
		return userAbsolutePath;
	}

	/**
	 * @param userAbsolutePath the userAbsolutePath to set
	 */
	public void setUserAbsolutePath(String userAbsolutePath) {
		this.userAbsolutePath = userAbsolutePath;
	}

	/**
	 * @return the cpuThreads
	 */
	public Integer getCpuThreads() {
		return cpuThreads;
	}

	/**
	 * @param cpuThreads the cpuThreads to set
	 */
	public void setCpuThreads(Integer cpuThreads) {
		this.cpuThreads = cpuThreads;
	}

	/**
	 * @return the maximumUserAnalysis
	 */
	public Integer getMaximumUserAnalysis() {
		return maximumUserAnalysis;
	}

	/**
	 * @param maximumUserAnalysis the maximumUserAnalysis to set
	 */
	public void setMaximumUserAnalysis(Integer maximumUserAnalysis) {
		this.maximumUserAnalysis = maximumUserAnalysis;
	}

	/**
	 * @return the maximumUserFiles
	 */
	public Integer getMaximumUserFiles() {
		return maximumUserFiles;
	}

	/**
	 * @param maximumUserFiles the maximumUserFiles to set
	 */
	public void setMaximumUserFiles(Integer maximumUserFiles) {
		this.maximumUserFiles = maximumUserFiles;
	}

	/**
	 * @return the readBatchSize
	 */
	public Integer getReadBatchSize() {
		return readBatchSize;
	}

	/**
	 * @param readBatchSize the readBatchSize to set
	 */
	public void setReadBatchSize(Integer readBatchSize) {
		this.readBatchSize = readBatchSize;
	}

	/**
	 * @return the writeBatchSize
	 */
	public Integer getWriteBatchSize() {
		return writeBatchSize;
	}

	/**
	 * @param writeBatchSize the writeBatchSize to set
	 */
	public void setWriteBatchSize(Integer writeBatchSize) {
		this.writeBatchSize = writeBatchSize;
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
	 * @return the sendApplicationConfigurationComponent
	 */
	public UIComponent getSendApplicationConfigurationComponent() {
		return sendApplicationConfigurationComponent;
	}

	/**
	 * @param sendApplicationConfigurationComponent the sendApplicationConfigurationComponent to set
	 */
	public void setSendApplicationConfigurationComponent(UIComponent sendApplicationConfigurationComponent) {
		this.sendApplicationConfigurationComponent = sendApplicationConfigurationComponent;
	}

	public String sendNewConfiguration() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null || user.getRole().equals(UserRole.USER)) {
			return "pretty:home";	
		}
		
		try {
			new SaveApplicationConfiguration(new ConfigurationDAOHibernate()).execute(
					new SaveApplicationConfigurationRequest(
							userAbsolutePath, 
							hpgMethylAbsolutePath,
							bwtIndexAbsolutePath, 
							cpuThreads, 
							maximumUserAnalysis, 
							maximumUserFiles, 
							readBatchSize, 
							writeBatchSize						
					)
			);
			
			this.updatedAt = new Date();
			
			String successMessage = FacesContextUtils.geti18nMessage("admin.configuration.updated");
			FacesContextUtils.setMessageInComponent(this.sendApplicationConfigurationComponent, FacesMessage.SEVERITY_INFO, successMessage, successMessage);
			
		} catch (SaveApplicationConfigurationException e) {
			String defaultErrorMessage = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.sendApplicationConfigurationComponent, FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		}
		
		return null;
	}
}
