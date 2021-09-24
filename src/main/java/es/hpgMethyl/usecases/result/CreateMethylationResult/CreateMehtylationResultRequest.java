package es.hpgMethyl.usecases.result.CreateMethylationResult;

import java.math.BigDecimal;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;

public class CreateMehtylationResultRequest {

	private AnalysisRequest analysisRequest;
	
	private HPGMethylFile resultFile;
	
	private Integer totalNumberCAnalysed;
	
	private Integer totalMethylatedCCPGContext;
	
	private Integer totalMethylatedCCHGContext;
	
	private Integer totalMethylatedCCHHContext;
	
	private Integer totalCToTConversionsCPGContext;
	
	private Integer totalCToTConversionsCHGContext;
	
	private Integer totalCToTConversionsCHHContex;
	
	private BigDecimal cMethylatedCPGContext;
	
	private BigDecimal cMethylatedCHGContext;
	
	private BigDecimal cMethylatedCHHContext;
	
	private BigDecimal loadingTime;
	
	private BigDecimal aligmentTime;
	
	private BigDecimal totalTime;
	
	private Integer totalReadsProcessed;
	
	private BigDecimal readsMapped;
	
	private Integer totalReadsMapped;
	
	private BigDecimal readsUnmapped;
	
	private Integer totalReadsUnmapped;

	public CreateMehtylationResultRequest(
			AnalysisRequest analysisRequest, 
			HPGMethylFile resultFile,
			Integer totalNumberCAnalysed, 
			Integer totalMethylatedCCPGContext, 
			Integer totalMethylatedCCHGContext,
			Integer totalMethylatedCCHHContext, 
			Integer totalCToTConversionsCPGContext,
			Integer totalCToTConversionsCHGContext, 
			Integer totalCToTConversionsCHHContex,
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
			Integer totalReadsUnmapped
		) {
		this.analysisRequest = analysisRequest;
		this.resultFile = resultFile;
		this.totalNumberCAnalysed = totalNumberCAnalysed;
		this.totalMethylatedCCPGContext = totalMethylatedCCPGContext;
		this.totalMethylatedCCHGContext = totalMethylatedCCHGContext;
		this.totalMethylatedCCHHContext = totalMethylatedCCHHContext;
		this.totalCToTConversionsCPGContext = totalCToTConversionsCPGContext;
		this.totalCToTConversionsCHGContext = totalCToTConversionsCHGContext;
		this.totalCToTConversionsCHHContex = totalCToTConversionsCHHContex;
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
	public Integer getTotalCToTConversionsCHHContex() {
		return totalCToTConversionsCHHContex;
	}

	/**
	 * @param totalCToTConversionsCHHContex the totalCToTConversionsCHHContex to set
	 */
	public void setTotalCToTConversionsCHHContex(Integer totalCToTConversionsCHHContex) {
		this.totalCToTConversionsCHHContex = totalCToTConversionsCHHContex;
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
}
