package es.hpgMethyl.services;

import java.math.BigDecimal;

public class HPGMethylResultReader {
		
	private static final String TOTAL_NUMBER_C_ANALYSED = "Total number of C's analysed";
	
	private static final String TOTAL_METHYLATED_C_CPG_CONTEXT = "Total methylated C's in CpG context";
	
	private static final String TOTAL_METHYLATED_C_CHG_CONTEXT = "Total methylated C's in CHG context";
	
	private static final String TOTAL_METHYLATED_C_CHH_CONTEXT = "Total methylated C's in CHH context";
	
	private static final String TOTAL_C_TO_T_CONVERSIONS_CPG_CONTEXT = "Total C to T conversions in CpG context";
	
	private static final String TOTAL_C_TO_T_CONVERSIONS_CHG_CONTEXT = "Total C to T conversions in CHG context";
	
	private static final String TOTAL_C_TO_T_CONVERSIONS_CHH_CONTEXT = "Total C to T conversions in CHH context";
	
	private static final String C_METHYLATED_CPG_CONTEXT = "C methylated in CpG context";
	
	private static final String C_METHYLATED_CHG_CONTEXT = "C methylated in CHG context";
	
	private static final String C_METHYLATED_CHH_CONTEXT = "C methylated in CHH context";
	
	private static final String LOADING_TIME = "Loading Time (s)";
	
	private static final String ALIGNMENT_TIME = "Alignment Time (s)";
	
	private static final String TOTAL_TIME = "Total Time (s)";
	
	private static final String TOTAL_READS_PROCESSED = "Total Reads Processed";
	
	private static final String READS_MAPPED = "Reads Mapped";
	
	private static final String READS_UNMAPPED = "Reads Unmapped";
	
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

	public HPGMethylResultReader() {
		this.totalNumberCAnalysed = null;
		this.totalMethylatedCCPGContext = null;
		this.totalMethylatedCCHGContext = null;
		this.totalMethylatedCCHHContext = null;
		this.totalCToTConversionsCPGContext = null;
		this.totalCToTConversionsCHGContext = null;
		this.totalCToTConversionsCHHContex = null;
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
	 * @return the totalMethylatedCCPGContext
	 */
	public Integer getTotalMethylatedCCPGContext() {
		return totalMethylatedCCPGContext;
	}

	/**
	 * @return the totalMethylatedCCHGContext
	 */
	public Integer getTotalMethylatedCCHGContext() {
		return totalMethylatedCCHGContext;
	}

	/**
	 * @return the totalMethylatedCCHHContext
	 */
	public Integer getTotalMethylatedCCHHContext() {
		return totalMethylatedCCHHContext;
	}

	/**
	 * @return the totalCToTConversionsCPGContext
	 */
	public Integer getTotalCToTConversionsCPGContext() {
		return totalCToTConversionsCPGContext;
	}

	/**
	 * @return the totalCToTConversionsCHGContext
	 */
	public Integer getTotalCToTConversionsCHGContext() {
		return totalCToTConversionsCHGContext;
	}

	/**
	 * @return the totalCToTConversionsCHHContex
	 */
	public Integer getTotalCToTConversionsCHHContex() {
		return totalCToTConversionsCHHContex;
	}

	/**
	 * @return the cMethylatedCPGContext
	 */
	public BigDecimal getcMethylatedCPGContext() {
		return cMethylatedCPGContext;
	}

	/**
	 * @return the cMethylatedCHGContext
	 */
	public BigDecimal getcMethylatedCHGContext() {
		return cMethylatedCHGContext;
	}

	/**
	 * @return the cMethylatedCHHContext
	 */
	public BigDecimal getcMethylatedCHHContext() {
		return cMethylatedCHHContext;
	}

	/**
	 * @return the loadingTime
	 */
	public BigDecimal getLoadingTime() {
		return loadingTime;
	}

	/**
	 * @return the aligmentTime
	 */
	public BigDecimal getAligmentTime() {
		return aligmentTime;
	}

	/**
	 * @return the totalTime
	 */
	public BigDecimal getTotalTime() {
		return totalTime;
	}

	/**
	 * @return the totalReadsProcessed
	 */
	public Integer getTotalReadsProcessed() {
		return totalReadsProcessed;
	}

	/**
	 * @return the readsMapped
	 */
	public BigDecimal getReadsMapped() {
		return readsMapped;
	}

	/**
	 * @return the totalReadsMapped
	 */
	public Integer getTotalReadsMapped() {
		return totalReadsMapped;
	}

	/**
	 * @return the readsUnmapped
	 */
	public BigDecimal getReadsUnmapped() {
		return readsUnmapped;
	}

	/**
	 * @return the totalReadsUnmapped
	 */
	public Integer getTotalReadsUnmapped() {
		return totalReadsUnmapped;
	}
	
	public void readLine(String line) {
		if(line != null) {
			if(line.contains(TOTAL_NUMBER_C_ANALYSED)) {
				this.totalNumberCAnalysed = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_METHYLATED_C_CPG_CONTEXT)) {
				this.totalMethylatedCCPGContext = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_METHYLATED_C_CHG_CONTEXT)) {
				this.totalMethylatedCCHGContext = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_METHYLATED_C_CHH_CONTEXT)) {
				this.totalMethylatedCCHHContext = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_C_TO_T_CONVERSIONS_CPG_CONTEXT)) {
				this.totalCToTConversionsCPGContext = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_C_TO_T_CONVERSIONS_CHG_CONTEXT)) {
				this.totalCToTConversionsCHGContext = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_C_TO_T_CONVERSIONS_CHH_CONTEXT)) {
				this.totalCToTConversionsCHHContex = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(C_METHYLATED_CPG_CONTEXT)) {
				this.cMethylatedCPGContext = getPercentageResultFromSimpleLine(line);
			} else if(line.contains(C_METHYLATED_CHG_CONTEXT)) {
				this.cMethylatedCHGContext = getPercentageResultFromSimpleLine(line);
			} else if(line.contains(C_METHYLATED_CHH_CONTEXT)) {
				this.cMethylatedCHHContext = getPercentageResultFromSimpleLine(line);
			} else if(line.contains(LOADING_TIME)) {
				this.loadingTime = getFloatResultFromSimpleLine(line);
			} else if(line.contains(ALIGNMENT_TIME)) {
				this.aligmentTime = getFloatResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_TIME)) {
				this.totalTime = getFloatResultFromSimpleLine(line);
			} else if(line.contains(TOTAL_READS_PROCESSED)) {
				this.totalReadsProcessed = getIntegerResultFromSimpleLine(line);
			} else if(line.contains(READS_MAPPED) && line.contains(READS_UNMAPPED)) {
				String[] lineResultsParts= line.replaceAll("^ +| +$|( )+", "$1").split("\\|");
				
				String readsMappedCompleteResult = lineResultsParts[1].split(":")[1];
				String[] readsMappedResults = readsMappedCompleteResult.split(" ");
				this.totalReadsMapped = Integer.valueOf(readsMappedResults[1].trim());
				this.readsMapped = new BigDecimal(readsMappedResults[2].trim().replace("%", ""));

				String readsUnmappedCompleteResult = lineResultsParts[2].split(":")[1];
				String[] readsUnmappedResults = readsUnmappedCompleteResult.split(" ");
				this.totalReadsUnmapped = Integer.valueOf(readsUnmappedResults[1].trim());
				this.readsUnmapped = new BigDecimal(readsUnmappedResults[2].trim().replace("%", ""));
			}
		}
	}
	
	private Integer getIntegerResultFromSimpleLine(String fileLine) {
		String[] lineParts = fileLine.split(":");
		return Integer.valueOf(lineParts[1].replace("|", "").trim());
	}
	
	private BigDecimal getFloatResultFromSimpleLine(String fileLine) {
		String[] lineParts = fileLine.split(":");
		return new BigDecimal(lineParts[1].replace("|", "").trim());
	}
	
	private BigDecimal getPercentageResultFromSimpleLine(String fileLine) {
		String[] lineParts = fileLine.split(":");
		return new BigDecimal(lineParts[1].replace("%", "").replace("|", "").trim());
	}
}
