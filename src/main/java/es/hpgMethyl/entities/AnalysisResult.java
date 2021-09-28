package es.hpgMethyl.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "analysis_result")
public class AnalysisResult extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "analysis_request", nullable = false, unique = true)
	private AnalysisRequest analysisRequest;

	@ManyToOne
	@JoinColumn(name = "result_file", nullable = true)
	private HPGMethylFile resultFile;
	
	@Column(name = "total_number_c_analysed", nullable = true)
	private Integer totalNumberCAnalysed;
	
	@Column(name = "total_methylated_c_cpg_context", nullable = true)
	private Integer totalMethylatedCCPGContext;
	
	@Column(name = "total_methylated_c_chg_context", nullable = true)
	private Integer totalMethylatedCCHGContext;
	
	@Column(name = "total_methylated_c_chh_context", nullable = true)
	private Integer totalMethylatedCCHHContext;
	
	@Column(name = "total_c_to_t_conversions_cpg_context", nullable = true)
	private Integer totalCToTConversionsCPGContext;
	
	@Column(name = "total_c_to_t_conversions_chg_context", nullable = true)
	private Integer totalCToTConversionsCHGContext;
	
	@Column(name = "total_c_to_t_conversions_chh_context", nullable = true)
	private Integer totalCToTConversionsCHHContext;
	
	@Column(name = "c_methylated_cpg_context", nullable = true)
	private BigDecimal cMethylatedCPGContext;
	
	@Column(name = "c_methylated_chg_context", nullable = true)
	private BigDecimal cMethylatedCHGContext;
	
	@Column(name = "c_methylated_chh_context", nullable = true)
	private BigDecimal cMethylatedCHHContext;
	
	@Column(name = "loading_time", nullable = true)
	private BigDecimal loadingTime;
	
	@Column(name = "aligment_time", nullable = true)
	private BigDecimal aligmentTime;
	
	@Column(name = "total_time", nullable = true)
	private BigDecimal totalTime;
	
	@Column(name = "total_reads_processed", nullable = true)
	private Integer totalReadsProcessed;
	
	@Column(name = "reads_mapped", nullable = true)
	private BigDecimal readsMapped;
	
	@Column(name = "total_reads_mapped", nullable = true)
	private Integer totalReadsMapped;
	
	@Column(name = "reads_unmapped", nullable = true)
	private BigDecimal readsUnmapped;
	
	@Column(name = "total_reads_unmapped", nullable = true)
	private Integer totalReadsUnmapped;
	
	@Column(name = "error", nullable = true)
	private String error;
	
	public AnalysisResult() {
		super();
		this.analysisRequest = null;
		this.resultFile = null;
		this.totalNumberCAnalysed = null;
		this.totalMethylatedCCPGContext = null;
		this.totalMethylatedCCHGContext = null;
		this.totalMethylatedCCHHContext = null;
		this.totalCToTConversionsCPGContext = null;
		this.totalCToTConversionsCHGContext = null;
		this.totalCToTConversionsCHHContext = null;
		this.cMethylatedCPGContext = null;
		this.cMethylatedCHGContext = null;
		this.cMethylatedCHHContext = null;
		this.loadingTime = null;
		this.aligmentTime = null;
		this.totalTime = null;
		this.totalReadsProcessed = null;
		this.readsMapped = null;
		this.totalReadsMapped = null;
		this.readsUnmapped = null;
		this.totalReadsUnmapped = null;
		this.error = null;
	}
	
	public AnalysisResult(			
			UUID id, 
			Date createdAt, 
			Date updatedAt,
			AnalysisRequest analysisRequest,
			HPGMethylFile resultFile,
			Integer totalNumberCAnalysed,
			Integer totalMethylatedCCPGContext,
			Integer totalMethylatedCCHGContext,
			Integer totalMethylatedCCHHContext,
			Integer totalCToTConversionsCPGContext,
			Integer totalCToTConversionsCHGContext,
			Integer totalCToTConversionsCHHContext,
			BigDecimal cMethylatedCPGContext,
			BigDecimal cMethylatedCHGContext,
			BigDecimal cMethylatedCHHContext,
			BigDecimal loadingTime,
			BigDecimal aligmentTime,
			BigDecimal totalTime,
			Integer totalReadsProcessed,
			BigDecimal readsMapped,
			Integer totalReadsMapped,
			BigDecimal readsUnmapped,
			Integer totalReadsUnmapped,
			String error
	) {
		super(id, createdAt, updatedAt);
		this.analysisRequest = analysisRequest;
		this.resultFile = resultFile;
		this.totalNumberCAnalysed = totalNumberCAnalysed;
		this.totalMethylatedCCPGContext = totalMethylatedCCPGContext;
		this.totalMethylatedCCHGContext = totalMethylatedCCHGContext;
		this.totalMethylatedCCHHContext = totalMethylatedCCHHContext;
		this.totalCToTConversionsCPGContext = totalCToTConversionsCPGContext;
		this.totalCToTConversionsCHGContext = totalCToTConversionsCHGContext;
		this.totalCToTConversionsCHHContext = totalCToTConversionsCHHContext;
		this.cMethylatedCPGContext = cMethylatedCPGContext;
		this.cMethylatedCHGContext = cMethylatedCHGContext;
		this.cMethylatedCHHContext = cMethylatedCHHContext;
		this.loadingTime = loadingTime;
		this.aligmentTime = aligmentTime;
		this.totalTime = totalTime;
		this.totalReadsProcessed = totalReadsProcessed;
		this.readsMapped = readsMapped;
		this.totalReadsMapped = totalReadsMapped;
		this.readsUnmapped = readsUnmapped;
		this.totalReadsUnmapped = totalReadsUnmapped;
		this.error = error;
	}
	
	/**
	 * @return the analysisRequest
	 */
	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}

	/**
	 * @param analysisRequest the analysisRequest to set
	 */
	public void setAnalysisRequest(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}

	/**
	 * @return the resultFile
	 */
	public HPGMethylFile getResultFile() {
		return resultFile;
	}

	/**
	 * @param resultFile the resultFile to set
	 */
	public void setResultFile(HPGMethylFile resultFile) {
		this.resultFile = resultFile;
	}

	/**
	 * @return the totalNumberCAnalysed
	 */
	public Integer getTotalNumberCAnalysed() {
		return totalNumberCAnalysed;
	}

	/**
	 * @param totalNumberCAnalysed the totalNumberCAnalysed to set
	 */
	public void setTotalNumberCAnalysed(Integer totalNumberCAnalysed) {
		this.totalNumberCAnalysed = totalNumberCAnalysed;
	}

	/**
	 * @return the totalMethylatedCCPGContext
	 */
	public Integer getTotalMethylatedCCPGContext() {
		return totalMethylatedCCPGContext;
	}

	/**
	 * @param totalMethylatedCCPGContext the totalMethylatedCCPGContext to set
	 */
	public void setTotalMethylatedCCPGContext(Integer totalMethylatedCCPGContext) {
		this.totalMethylatedCCPGContext = totalMethylatedCCPGContext;
	}

	/**
	 * @return the totalMethylatedCCHGContext
	 */
	public Integer getTotalMethylatedCCHGContext() {
		return totalMethylatedCCHGContext;
	}

	/**
	 * @param totalMethylatedCCHGContext the totalMethylatedCCHGContext to set
	 */
	public void setTotalMethylatedCCHGContext(Integer totalMethylatedCCHGContext) {
		this.totalMethylatedCCHGContext = totalMethylatedCCHGContext;
	}

	/**
	 * @return the totalMethylatedCCHHContext
	 */
	public Integer getTotalMethylatedCCHHContext() {
		return totalMethylatedCCHHContext;
	}

	/**
	 * @param totalMethylatedCCHHContext the totalMethylatedCCHHContext to set
	 */
	public void setTotalMethylatedCCHHContext(Integer totalMethylatedCCHHContext) {
		this.totalMethylatedCCHHContext = totalMethylatedCCHHContext;
	}

	/**
	 * @return the totalCToTConversionsCPGContext
	 */
	public Integer getTotalCToTConversionsCPGContext() {
		return totalCToTConversionsCPGContext;
	}

	/**
	 * @param totalCToTConversionsCPGContext the totalCToTConversionsCPGContext to set
	 */
	public void setTotalCToTConversionsCPGContext(Integer totalCToTConversionsCPGContext) {
		this.totalCToTConversionsCPGContext = totalCToTConversionsCPGContext;
	}

	/**
	 * @return the totalCToTConversionsCHGContext
	 */
	public Integer getTotalCToTConversionsCHGContext() {
		return totalCToTConversionsCHGContext;
	}

	/**
	 * @param totalCToTConversionsCHGContext the totalCToTConversionsCHGContext to set
	 */
	public void setTotalCToTConversionsCHGContext(Integer totalCToTConversionsCHGContext) {
		this.totalCToTConversionsCHGContext = totalCToTConversionsCHGContext;
	}

	/**
	 * @return the totalCToTConversionsCHHContex
	 */
	public Integer getTotalCToTConversionsCHHContext() {
		return totalCToTConversionsCHHContext;
	}

	/**
	 * @param totalCToTConversionsCHHContex the totalCToTConversionsCHHContex to set
	 */
	public void setTotalCToTConversionsCHHContext(Integer totalCToTConversionsCHHContext) {
		this.totalCToTConversionsCHHContext = totalCToTConversionsCHHContext;
	}

	/**
	 * @return the cMethylatedCPGContext
	 */
	public BigDecimal getcMethylatedCPGContext() {
		return cMethylatedCPGContext;
	}

	/**
	 * @param cMethylatedCPGContext the cMethylatedCPGContext to set
	 */
	public void setcMethylatedCPGContext(BigDecimal cMethylatedCPGContext) {
		this.cMethylatedCPGContext = cMethylatedCPGContext;
	}

	/**
	 * @return the cMethylatedCHGContext
	 */
	public BigDecimal getcMethylatedCHGContext() {
		return cMethylatedCHGContext;
	}

	/**
	 * @param cMethylatedCHGContext the cMethylatedCHGContext to set
	 */
	public void setcMethylatedCHGContext(BigDecimal cMethylatedCHGContext) {
		this.cMethylatedCHGContext = cMethylatedCHGContext;
	}

	/**
	 * @return the cMethylatedCHHContext
	 */
	public BigDecimal getcMethylatedCHHContext() {
		return cMethylatedCHHContext;
	}

	/**
	 * @param cMethylatedCHHContext the cMethylatedCHHContext to set
	 */
	public void setcMethylatedCHHContext(BigDecimal cMethylatedCHHContext) {
		this.cMethylatedCHHContext = cMethylatedCHHContext;
	}

	/**
	 * @return the loadingTime
	 */
	public BigDecimal getLoadingTime() {
		return loadingTime;
	}

	/**
	 * @param loadingTime the loadingTime to set
	 */
	public void setLoadingTime(BigDecimal loadingTime) {
		this.loadingTime = loadingTime;
	}

	/**
	 * @return the aligmentTime
	 */
	public BigDecimal getAligmentTime() {
		return aligmentTime;
	}

	/**
	 * @param aligmentTime the aligmentTime to set
	 */
	public void setAligmentTime(BigDecimal aligmentTime) {
		this.aligmentTime = aligmentTime;
	}

	/**
	 * @return the totalTime
	 */
	public BigDecimal getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(BigDecimal totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return the totalReadsProcessed
	 */
	public Integer getTotalReadsProcessed() {
		return totalReadsProcessed;
	}

	/**
	 * @param totalReadsProcessed the totalReadsProcessed to set
	 */
	public void setTotalReadsProcessed(Integer totalReadsProcessed) {
		this.totalReadsProcessed = totalReadsProcessed;
	}

	/**
	 * @return the readsMapped
	 */
	public BigDecimal getReadsMapped() {
		return readsMapped;
	}

	/**
	 * @param readsMapped the readsMapped to set
	 */
	public void setReadsMapped(BigDecimal readsMapped) {
		this.readsMapped = readsMapped;
	}

	/**
	 * @return the totalReadsMapped
	 */
	public Integer getTotalReadsMapped() {
		return totalReadsMapped;
	}

	/**
	 * @param totalReadsMapped the totalReadsMapped to set
	 */
	public void setTotalReadsMapped(Integer totalReadsMapped) {
		this.totalReadsMapped = totalReadsMapped;
	}

	/**
	 * @return the readsUnmapped
	 */
	public BigDecimal getReadsUnmapped() {
		return readsUnmapped;
	}

	/**
	 * @param readsUnmapped the readsUnmapped to set
	 */
	public void setReadsUnmapped(BigDecimal readsUnmapped) {
		this.readsUnmapped = readsUnmapped;
	}

	/**
	 * @return the totalReadsUnmapped
	 */
	public Integer getTotalReadsUnmapped() {
		return totalReadsUnmapped;
	}

	/**
	 * @param totalReadsUnmapped the totalReadsUnmapped to set
	 */
	public void setTotalReadsUnmapped(Integer totalReadsUnmapped) {
		this.totalReadsUnmapped = totalReadsUnmapped;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
}
