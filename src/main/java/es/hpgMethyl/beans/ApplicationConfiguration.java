package es.hpgMethyl.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;

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
		
		return null;
	}
}
