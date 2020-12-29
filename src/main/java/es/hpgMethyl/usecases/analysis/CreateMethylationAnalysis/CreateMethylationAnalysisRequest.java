package es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis;

import java.math.BigDecimal;

import es.hpgMethyl.entities.User;

public class CreateMethylationAnalysisRequest {

	private User user;
	
	private String identifier; 
	
	private String inputReadFile; 
	
	private Boolean writeMethylationContext;
	
	private Boolean readBatchSize; 
	
	private Boolean writeBatchSize; 
	
	private Integer pairedMode; 
	
	private String pairedEndModeFile;
	
	private BigDecimal pairedMaxDistance; 
	
	private BigDecimal pairedMinDistance; 
	
	private BigDecimal swaMinimunScore;
	
	private BigDecimal swaMatchScore; 
	
	private BigDecimal swaMismatchScore; 
	
	private BigDecimal swaGapOpen; 
	
	private BigDecimal swaGapExtend;
	
	private BigDecimal calFlankSize; 
	
	private BigDecimal minimumCalSize; 
	
	private BigDecimal calUmbralLengthFactor;
	
	private BigDecimal maximumBetweenSeeds; 
	
	private BigDecimal maximumSeedSize; 
	
	private BigDecimal minimumSeedSize;
	
	private BigDecimal numberSeedsPerRead; 
	
	private BigDecimal readMinimumDiscardLength; 
	
	private BigDecimal readMaximumInnerGap;
	
	private BigDecimal minimumNumberSeeds; 
	
	private BigDecimal filterReadMappings;
	
	private BigDecimal filterSeedMappings; 
	
	private Boolean reportAll; 
	
	private Boolean reportBest; 
	
	private BigDecimal reportNBest;
	
	private BigDecimal reportNHits;

	public CreateMethylationAnalysisRequest(
			User user, 
			String identifier, 
			String inputReadFile,
			Boolean writeMethylationContext, 
			Boolean readBatchSize, 
			Boolean writeBatchSize, 
			Integer pairedMode,
			String pairedEndModeFile, 
			BigDecimal pairedMaxDistance, 
			BigDecimal pairedMinDistance,
			BigDecimal swaMinimunScore, 
			BigDecimal swaMatchScore, 
			BigDecimal swaMismatchScore, 
			BigDecimal swaGapOpen,
			BigDecimal swaGapExtend, 
			BigDecimal calFlankSize, 
			BigDecimal minimumCalSize,
			BigDecimal calUmbralLengthFactor, 
			BigDecimal maximumBetweenSeeds, 
			BigDecimal maximumSeedSize,
			BigDecimal minimumSeedSize, 
			BigDecimal numberSeedsPerRead, 
			BigDecimal readMinimumDiscardLength,
			BigDecimal readMaximumInnerGap, 
			BigDecimal minimumNumberSeeds, 
			BigDecimal filterReadMappings,
			BigDecimal filterSeedMappings, 
			Boolean reportAll, 
			Boolean reportBest, 
			BigDecimal reportNBest,
			BigDecimal reportNHits
	) {
		this.user = user;
		this.identifier = identifier;
		this.inputReadFile = inputReadFile;
		this.writeMethylationContext = writeMethylationContext;
		this.readBatchSize = readBatchSize;
		this.writeBatchSize = writeBatchSize;
		this.pairedMode = pairedMode;
		this.pairedEndModeFile = pairedEndModeFile;
		this.pairedMaxDistance = pairedMaxDistance;
		this.pairedMinDistance = pairedMinDistance;
		this.swaMinimunScore = swaMinimunScore;
		this.swaMatchScore = swaMatchScore;
		this.swaMismatchScore = swaMismatchScore;
		this.swaGapOpen = swaGapOpen;
		this.swaGapExtend = swaGapExtend;
		this.calFlankSize = calFlankSize;
		this.minimumCalSize = minimumCalSize;
		this.calUmbralLengthFactor = calUmbralLengthFactor;
		this.maximumBetweenSeeds = maximumBetweenSeeds;
		this.maximumSeedSize = maximumSeedSize;
		this.minimumSeedSize = minimumSeedSize;
		this.numberSeedsPerRead = numberSeedsPerRead;
		this.readMinimumDiscardLength = readMinimumDiscardLength;
		this.readMaximumInnerGap = readMaximumInnerGap;
		this.minimumNumberSeeds = minimumNumberSeeds;
		this.filterReadMappings = filterReadMappings;
		this.filterSeedMappings = filterSeedMappings;
		this.reportAll = reportAll;
		this.reportBest = reportBest;
		this.reportNBest = reportNBest;
		this.reportNHits = reportNHits;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the inputReadFile
	 */
	public String getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the inputReadFile to set
	 */
	public void setInputReadFile(String inputReadFile) {
		this.inputReadFile = inputReadFile;
	}

	/**
	 * @return the writeMethylationContext
	 */
	public Boolean getWriteMethylationContext() {
		return writeMethylationContext;
	}

	/**
	 * @param writeMethylationContext the writeMethylationContext to set
	 */
	public void setWriteMethylationContext(Boolean writeMethylationContext) {
		this.writeMethylationContext = writeMethylationContext;
	}

	/**
	 * @return the readBatchSize
	 */
	public Boolean getReadBatchSize() {
		return readBatchSize;
	}

	/**
	 * @param readBatchSize the readBatchSize to set
	 */
	public void setReadBatchSize(Boolean readBatchSize) {
		this.readBatchSize = readBatchSize;
	}

	/**
	 * @return the writeBatchSize
	 */
	public Boolean getWriteBatchSize() {
		return writeBatchSize;
	}

	/**
	 * @param writeBatchSize the writeBatchSize to set
	 */
	public void setWriteBatchSize(Boolean writeBatchSize) {
		this.writeBatchSize = writeBatchSize;
	}

	/**
	 * @return the pairedMode
	 */
	public Integer getPairedMode() {
		return pairedMode;
	}

	/**
	 * @param pairedMode the pairedMode to set
	 */
	public void setPairedMode(Integer pairedMode) {
		this.pairedMode = pairedMode;
	}

	/**
	 * @return the pairedEndModeFile
	 */
	public String getPairedEndModeFile() {
		return pairedEndModeFile;
	}

	/**
	 * @param pairedEndModeFile the pairedEndModeFile to set
	 */
	public void setPairedEndModeFile(String pairedEndModeFile) {
		this.pairedEndModeFile = pairedEndModeFile;
	}

	/**
	 * @return the pairedMaxDistance
	 */
	public BigDecimal getPairedMaxDistance() {
		return pairedMaxDistance;
	}

	/**
	 * @param pairedMaxDistance the pairedMaxDistance to set
	 */
	public void setPairedMaxDistance(BigDecimal pairedMaxDistance) {
		this.pairedMaxDistance = pairedMaxDistance;
	}

	/**
	 * @return the pairedMinDistance
	 */
	public BigDecimal getPairedMinDistance() {
		return pairedMinDistance;
	}

	/**
	 * @param pairedMinDistance the pairedMinDistance to set
	 */
	public void setPairedMinDistance(BigDecimal pairedMinDistance) {
		this.pairedMinDistance = pairedMinDistance;
	}

	/**
	 * @return the swaMinimunScore
	 */
	public BigDecimal getSwaMinimunScore() {
		return swaMinimunScore;
	}

	/**
	 * @param swaMinimunScore the swaMinimunScore to set
	 */
	public void setSwaMinimunScore(BigDecimal swaMinimunScore) {
		this.swaMinimunScore = swaMinimunScore;
	}

	/**
	 * @return the swaMatchScore
	 */
	public BigDecimal getSwaMatchScore() {
		return swaMatchScore;
	}

	/**
	 * @param swaMatchScore the swaMatchScore to set
	 */
	public void setSwaMatchScore(BigDecimal swaMatchScore) {
		this.swaMatchScore = swaMatchScore;
	}

	/**
	 * @return the swaMismatchScore
	 */
	public BigDecimal getSwaMismatchScore() {
		return swaMismatchScore;
	}

	/**
	 * @param swaMismatchScore the swaMismatchScore to set
	 */
	public void setSwaMismatchScore(BigDecimal swaMismatchScore) {
		this.swaMismatchScore = swaMismatchScore;
	}

	/**
	 * @return the swaGapOpen
	 */
	public BigDecimal getSwaGapOpen() {
		return swaGapOpen;
	}

	/**
	 * @param swaGapOpen the swaGapOpen to set
	 */
	public void setSwaGapOpen(BigDecimal swaGapOpen) {
		this.swaGapOpen = swaGapOpen;
	}

	/**
	 * @return the swaGapExtend
	 */
	public BigDecimal getSwaGapExtend() {
		return swaGapExtend;
	}

	/**
	 * @param swaGapExtend the swaGapExtend to set
	 */
	public void setSwaGapExtend(BigDecimal swaGapExtend) {
		this.swaGapExtend = swaGapExtend;
	}

	/**
	 * @return the calFlankSize
	 */
	public BigDecimal getCalFlankSize() {
		return calFlankSize;
	}

	/**
	 * @param calFlankSize the calFlankSize to set
	 */
	public void setCalFlankSize(BigDecimal calFlankSize) {
		this.calFlankSize = calFlankSize;
	}

	/**
	 * @return the minimumCalSize
	 */
	public BigDecimal getMinimumCalSize() {
		return minimumCalSize;
	}

	/**
	 * @param minimumCalSize the minimumCalSize to set
	 */
	public void setMinimumCalSize(BigDecimal minimumCalSize) {
		this.minimumCalSize = minimumCalSize;
	}

	/**
	 * @return the calUmbralLengthFactor
	 */
	public BigDecimal getCalUmbralLengthFactor() {
		return calUmbralLengthFactor;
	}

	/**
	 * @param calUmbralLengthFactor the calUmbralLengthFactor to set
	 */
	public void setCalUmbralLengthFactor(BigDecimal calUmbralLengthFactor) {
		this.calUmbralLengthFactor = calUmbralLengthFactor;
	}

	/**
	 * @return the maximumBetweenSeeds
	 */
	public BigDecimal getMaximumBetweenSeeds() {
		return maximumBetweenSeeds;
	}

	/**
	 * @param maximumBetweenSeeds the maximumBetweenSeeds to set
	 */
	public void setMaximumBetweenSeeds(BigDecimal maximumBetweenSeeds) {
		this.maximumBetweenSeeds = maximumBetweenSeeds;
	}

	/**
	 * @return the maximumSeedSize
	 */
	public BigDecimal getMaximumSeedSize() {
		return maximumSeedSize;
	}

	/**
	 * @param maximumSeedSize the maximumSeedSize to set
	 */
	public void setMaximumSeedSize(BigDecimal maximumSeedSize) {
		this.maximumSeedSize = maximumSeedSize;
	}

	/**
	 * @return the minimumSeedSize
	 */
	public BigDecimal getMinimumSeedSize() {
		return minimumSeedSize;
	}

	/**
	 * @param minimumSeedSize the minimumSeedSize to set
	 */
	public void setMinimumSeedSize(BigDecimal minimumSeedSize) {
		this.minimumSeedSize = minimumSeedSize;
	}

	/**
	 * @return the numberSeedsPerRead
	 */
	public BigDecimal getNumberSeedsPerRead() {
		return numberSeedsPerRead;
	}

	/**
	 * @param numberSeedsPerRead the numberSeedsPerRead to set
	 */
	public void setNumberSeedsPerRead(BigDecimal numberSeedsPerRead) {
		this.numberSeedsPerRead = numberSeedsPerRead;
	}

	/**
	 * @return the readMinimumDiscardLength
	 */
	public BigDecimal getReadMinimumDiscardLength() {
		return readMinimumDiscardLength;
	}

	/**
	 * @param readMinimumDiscardLength the readMinimumDiscardLength to set
	 */
	public void setReadMinimumDiscardLength(BigDecimal readMinimumDiscardLength) {
		this.readMinimumDiscardLength = readMinimumDiscardLength;
	}

	/**
	 * @return the readMaximumInnerGap
	 */
	public BigDecimal getReadMaximumInnerGap() {
		return readMaximumInnerGap;
	}

	/**
	 * @param readMaximumInnerGap the readMaximumInnerGap to set
	 */
	public void setReadMaximumInnerGap(BigDecimal readMaximumInnerGap) {
		this.readMaximumInnerGap = readMaximumInnerGap;
	}

	/**
	 * @return the minimumNumberSeeds
	 */
	public BigDecimal getMinimumNumberSeeds() {
		return minimumNumberSeeds;
	}

	/**
	 * @param minimumNumberSeeds the minimumNumberSeeds to set
	 */
	public void setMinimumNumberSeeds(BigDecimal minimumNumberSeeds) {
		this.minimumNumberSeeds = minimumNumberSeeds;
	}

	/**
	 * @return the filterReadMappings
	 */
	public BigDecimal getFilterReadMappings() {
		return filterReadMappings;
	}

	/**
	 * @param filterReadMappings the filterReadMappings to set
	 */
	public void setFilterReadMappings(BigDecimal filterReadMappings) {
		this.filterReadMappings = filterReadMappings;
	}

	/**
	 * @return the filterSeedMappings
	 */
	public BigDecimal getFilterSeedMappings() {
		return filterSeedMappings;
	}

	/**
	 * @param filterSeedMappings the filterSeedMappings to set
	 */
	public void setFilterSeedMappings(BigDecimal filterSeedMappings) {
		this.filterSeedMappings = filterSeedMappings;
	}

	/**
	 * @return the reportAll
	 */
	public Boolean getReportAll() {
		return reportAll;
	}

	/**
	 * @param reportAll the reportAll to set
	 */
	public void setReportAll(Boolean reportAll) {
		this.reportAll = reportAll;
	}

	/**
	 * @return the reportBest
	 */
	public Boolean getReportBest() {
		return reportBest;
	}

	/**
	 * @param reportBest the reportBest to set
	 */
	public void setReportBest(Boolean reportBest) {
		this.reportBest = reportBest;
	}

	/**
	 * @return the reportNBest
	 */
	public BigDecimal getReportNBest() {
		return reportNBest;
	}

	/**
	 * @param reportNBest the reportNBest to set
	 */
	public void setReportNBest(BigDecimal reportNBest) {
		this.reportNBest = reportNBest;
	}

	/**
	 * @return the reportNHits
	 */
	public BigDecimal getReportNHits() {
		return reportNHits;
	}

	/**
	 * @param reportNHits the reportNHits to set
	 */
	public void setReportNHits(BigDecimal reportNHits) {
		this.reportNHits = reportNHits;
	}
}

