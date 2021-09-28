package es.hpgMethyl.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import es.hpgMethyl.entities.AnalysisResult;

@ManagedBean(name="analysisResultBean")
@ViewScoped
public class AnalysisResultBean implements Serializable {

	private static final long serialVersionUID = -66931227949705531L;

	private Integer totalNumberCAnalysed;
	private Integer totalMethylatedCCPGContext;
	private Integer totalMethylatedCCHGContext;
	private Integer totalMethylatedCCHHContext;
	private Integer totalCToTConversionsCPGContext;
	private Integer totalCToTConversionsCHGContext;
	private Integer totalCToTConversionsCHHContext;
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
	
	public AnalysisResultBean() {
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
	 * @return the totalCToTConversionsCHHContext
	 */
	public Integer getTotalCToTConversionsCHHContext() {
		return totalCToTConversionsCHHContext;
	}

	/**
	 * @param totalCToTConversionsCHHContext the totalCToTConversionsCHHContext to set
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
	
	public void loadAnalysisResult(AnalysisResult analysisResult) {
		this.totalNumberCAnalysed = analysisResult.getTotalNumberCAnalysed();
		this.totalMethylatedCCPGContext = analysisResult.getTotalMethylatedCCPGContext();
		this.totalMethylatedCCHGContext = analysisResult.getTotalMethylatedCCHGContext();
		this.totalMethylatedCCHHContext = analysisResult.getTotalMethylatedCCHHContext();
		this.totalCToTConversionsCPGContext = analysisResult.getTotalCToTConversionsCPGContext();
		this.totalCToTConversionsCHGContext = analysisResult.getTotalCToTConversionsCHGContext();
		this.totalCToTConversionsCHHContext = analysisResult.getTotalCToTConversionsCHHContext();
		this.cMethylatedCPGContext = analysisResult.getcMethylatedCPGContext();
		this.cMethylatedCHGContext = analysisResult.getcMethylatedCHGContext();;
		this.cMethylatedCHHContext = analysisResult.getcMethylatedCHHContext();
		this.loadingTime = analysisResult.getLoadingTime();
		this.aligmentTime = analysisResult.getAligmentTime();
		this.totalTime = analysisResult.getTotalTime();
		this.totalReadsProcessed = analysisResult.getTotalReadsProcessed();
		this.readsMapped = analysisResult.getReadsMapped();
		this.totalReadsMapped = analysisResult.getTotalReadsMapped();
		this.readsUnmapped = analysisResult.getReadsUnmapped();
		this.totalReadsUnmapped = analysisResult.getTotalReadsUnmapped();
	}
}
