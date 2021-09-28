package es.hpgMethyl.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class HPGMethylResultReader {
		
	private static final String RESULT_SEPARATOR = "Final Cytosine Methylation Report";
	
	private String resultFilePath;
	
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

	public HPGMethylResultReader(String resultFilePath) {
		this.resultFilePath = resultFilePath;
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
	 * @return the resultFilePath
	 */
	public String getResultFilePath() {
		return resultFilePath;
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

	public void read() {
		
		try {
			Scanner scanner = new Scanner(new File(resultFilePath));
			
			Integer line = null;
			
			while(scanner.hasNextLine()) {				
				String fileLine = scanner.nextLine();

				if(fileLine.equals(RESULT_SEPARATOR)) {
					line = 0;
				}
				
				if(line != null) {
					switch (line) {
					case 2:
						this.totalNumberCAnalysed = getIntegerResultFromSimpleLine(fileLine);
						break;
					case 4:
						this.totalMethylatedCCPGContext = getIntegerResultFromSimpleLine(fileLine);
						break;
					case 5:
						this.totalMethylatedCCHGContext = getIntegerResultFromSimpleLine(fileLine);
						break;
					case 6:
						this.totalMethylatedCCHHContext = getIntegerResultFromSimpleLine(fileLine);
						break;
					case 8:
						this.totalCToTConversionsCPGContext = getIntegerResultFromSimpleLine(fileLine);
						break;
					case 9:
						this.totalCToTConversionsCHGContext = getIntegerResultFromSimpleLine(fileLine);
						break;	
					case 10:
						this.totalCToTConversionsCHHContex = getIntegerResultFromSimpleLine(fileLine);
						break;	
					case 12:
						this.cMethylatedCPGContext = getPercentageResultFromSimpleLine(fileLine);
						break;
					case 13:
						this.cMethylatedCHGContext = getPercentageResultFromSimpleLine(fileLine);
						break;	
					case 14:
						this.cMethylatedCHHContext = getPercentageResultFromSimpleLine(fileLine);
						break;	
					case 18:
						this.loadingTime = getFloatResultFromSimpleLine(fileLine);
						break;	
					case 19:
						this.aligmentTime = getFloatResultFromSimpleLine(fileLine);
						break;	
					case 20:
						this.totalTime = getFloatResultFromSimpleLine(fileLine);
						break;	
					case 22:
						this.totalReadsProcessed = getIntegerResultFromSimpleLine(fileLine);
						break;	
					case 24:
						String[] lineResultsParts= fileLine.replaceAll("^ +| +$|( )+", "$1").split("\\|");
						
						String readsMappedCompleteResult = lineResultsParts[1].split(":")[1];
						String[] readsMappedResults = readsMappedCompleteResult.split(" ");
						this.totalReadsMapped = Integer.valueOf(readsMappedResults[1].trim());
						this.readsMapped = new BigDecimal(readsMappedResults[2].trim().replace("%", ""));

						String readsUnmappedCompleteResult = lineResultsParts[2].split(":")[1];
						String[] readsUnmappedResults = readsUnmappedCompleteResult.split(" ");
						this.totalReadsUnmapped = Integer.valueOf(readsUnmappedResults[1].trim());
						this.readsUnmapped = new BigDecimal(readsUnmappedResults[2].trim().replace("%", ""));
						break;	
					default:
						break;
					}
					
					line++;
				}
			}
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
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
