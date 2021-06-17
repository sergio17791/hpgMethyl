package es.hpgMethyl.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
public class Configuration extends BaseEntity { 

	@Column(name = "users_directory_absolute_path", nullable = false)
	private String usersDirectoryAbsolutePath;
	
	@Column(name = "hpgMethyl_absolute_path", nullable = false)
	private String hpgMethylAbsolutePath;
	
	@Column(name = "bwt_index_absolute_path", nullable = false)
	private String bwtIndexAbsolutePath;
	
	@Column(name = "cpu_threads", nullable = false)
	private Integer cpuThreads;
	
	@Column(name = "maximum_user_analysis_pending", nullable = true)
	private Integer maximumUserAnalysisPending;
	
	@Column(name = "maximum_user_files_stored", nullable = true)
	private Integer maximumUserFilesStored;
	
	@Column(name = "read_batch_size", nullable = true)
	private Integer readBatchSize;
	
	@Column(name = "write_batch_size", nullable = true)
	private Integer writeBatchSize;
	
	public Configuration() {
		super();
	}

	public Configuration(
			UUID id, 
			Date createdAt, 
			Date updatedAt, 
			String usersDirectoryAbsolutePath, 
			String hpgMethylAbsolutePath, 
			String bwtIndexAbsolutePath,
			Integer cpuThreads, 
			Integer maximumUserAnalysisPending, 
			Integer maximumUserFilesStored,
			Integer readBatchSize, 
			Integer writeBatchSize
	) {
		super(id, createdAt, updatedAt);
		this.usersDirectoryAbsolutePath = usersDirectoryAbsolutePath;
		this.hpgMethylAbsolutePath = hpgMethylAbsolutePath;
		this.bwtIndexAbsolutePath = bwtIndexAbsolutePath;
		this.cpuThreads = cpuThreads;
		this.maximumUserAnalysisPending = maximumUserAnalysisPending;
		this.maximumUserFilesStored = maximumUserFilesStored;
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
