package es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration;

public class SaveApplicationConfigurationRequest {

	private String usersDirectoryAbsolutePath;
	
	private String hpgMethylAbsolutePath;
	
	private String bwtIndexAbsolutePath;
	
	private Integer cpuThreads;
	
	private Integer maximumUserAnalysisPending;
	
	private Integer maximumUserFilesStored;
	
	private Long fileSizeLimit;
	
	private Integer readBatchSize;
	
	private Integer writeBatchSize;

	public SaveApplicationConfigurationRequest(
			String usersDirectoryAbsolutePath, 
			String hpgMethylAbsolutePath,
			String bwtIndexAbsolutePath, 
			Integer cpuThreads, 
			Integer maximumUserAnalysisPending,
			Integer maximumUserFilesStored, 
			Long fileSizeLimit,
			Integer readBatchSize, 
			Integer writeBatchSize
	) {
		this.usersDirectoryAbsolutePath = usersDirectoryAbsolutePath;
		this.hpgMethylAbsolutePath = hpgMethylAbsolutePath;
		this.bwtIndexAbsolutePath = bwtIndexAbsolutePath;
		this.cpuThreads = cpuThreads;
		this.maximumUserAnalysisPending = maximumUserAnalysisPending;
		this.maximumUserFilesStored = maximumUserFilesStored;
		this.fileSizeLimit = fileSizeLimit;
		this.readBatchSize = readBatchSize;
		this.writeBatchSize = writeBatchSize;
	}

	/**
	 * @return the usersDirectoryAbsolutePath
	 */
	public String getUsersDirectoryAbsolutePath() {
		return usersDirectoryAbsolutePath;
	}

	/**
	 * @param usersDirectoryAbsolutePath the usersDirectoryAbsolutePath to set
	 */
	public void setUsersDirectoryAbsolutePath(String usersDirectoryAbsolutePath) {
		this.usersDirectoryAbsolutePath = usersDirectoryAbsolutePath;
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
	 * @return the bwtIndexAbsolutePath
	 */
	public String getBwtIndexAbsolutePath() {
		return bwtIndexAbsolutePath;
	}

	/**
	 * @param bwtIndexAbsolutePath the bwtIndexAbsolutePath to set
	 */
	public void setBwtIndexAbsolutePath(String bwtIndexAbsolutePath) {
		this.bwtIndexAbsolutePath = bwtIndexAbsolutePath;
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
	 * @return the maximumUserAnalysisPending
	 */
	public Integer getMaximumUserAnalysisPending() {
		return maximumUserAnalysisPending;
	}

	/**
	 * @param maximumUserAnalysisPending the maximumUserAnalysisPending to set
	 */
	public void setMaximumUserAnalysisPending(Integer maximumUserAnalysisPending) {
		this.maximumUserAnalysisPending = maximumUserAnalysisPending;
	}

	/**
	 * @return the maximumUserFilesStored
	 */
	public Integer getMaximumUserFilesStored() {
		return maximumUserFilesStored;
	}

	/**
	 * @param maximumUserFilesStored the maximumUserFilesStored to set
	 */
	public void setMaximumUserFilesStored(Integer maximumUserFilesStored) {
		this.maximumUserFilesStored = maximumUserFilesStored;
	}

	/**
	 * @return the fileSizeLimit
	 */
	public Long getFileSizeLimit() {
		return fileSizeLimit;
	}

	/**
	 * @param fileSizeLimit the fileSizeLimit to set
	 */
	public void setFileSizeLimit(Long fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
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
}
