package es.hpgMethyl.usecases.analysis.UpdateMethylationAnalysisParameters;

import java.math.BigDecimal;
import java.util.UUID;

import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.types.PairedMode;

public class UpdateMethylationAnalysisParametersRequest {

	private UUID id;
	
	private String identifier; 
	
	private HPGMethylFile inputReadFile; 
	
	private Boolean writeMethylationContext;
	
	private PairedMode pairedMode; 
	
	private HPGMethylFile pairedEndModeFile;
	
	private Integer pairedMaxDistance; 
	
	private Integer pairedMinDistance; 
	
	private BigDecimal swaMinimunScore;
	
	private BigDecimal swaMatchScore; 
	
	private BigDecimal swaMismatchScore; 
	
	private BigDecimal swaGapOpen; 
	
	private BigDecimal swaGapExtend;
	
	private Integer calFlankSize; 
	
	private Integer minimumCalSize; 
	
	private BigDecimal calUmbralLengthFactor;
	
	private Integer maximumBetweenSeeds; 
	
	private Integer maximumSeedSize; 
	
	private Integer minimumSeedSize;
	
	private Integer numberSeedsPerRead; 
	
	private Integer readMinimumDiscardLength; 
	
	private Integer readMaximumInnerGap;
	
	private Integer minimumNumberSeeds; 
	
	private Integer filterReadMappings;
	
	private Integer filterSeedMappings; 
	
	private Boolean reportAll; 
	
	private Boolean reportBest; 
	
	private Integer reportNBest;
	
	private Integer reportNHits;

	public UpdateMethylationAnalysisParametersRequest(
			UUID id, 
			String identifier, 
			HPGMethylFile inputReadFile,
			Boolean writeMethylationContext,
			PairedMode pairedMode, 
			HPGMethylFile pairedEndModeFile,
			Integer pairedMaxDistance,
			Integer pairedMinDistance, 
			BigDecimal swaMinimunScore, 
			BigDecimal swaMatchScore,
			BigDecimal swaMismatchScore, 
			BigDecimal swaGapOpen, 
			BigDecimal swaGapExtend, 
			Integer calFlankSize,
			Integer minimumCalSize, 
			BigDecimal calUmbralLengthFactor, 
			Integer maximumBetweenSeeds,
			Integer maximumSeedSize, 
			Integer minimumSeedSize, 
			Integer numberSeedsPerRead,
			Integer readMinimumDiscardLength, 
			Integer readMaximumInnerGap, 
			Integer minimumNumberSeeds,
			Integer filterReadMappings, 
			Integer filterSeedMappings, 
			Boolean reportAll, 
			Boolean reportBest,
			Integer reportNBest, 
			Integer reportNHits
	) {
		this.id = id;
		this.identifier = identifier;
		this.inputReadFile = inputReadFile;
		this.writeMethylationContext = writeMethylationContext;
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
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
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
	public HPGMethylFile getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the inputReadFile to set
	 */
	public void setInputReadFile(HPGMethylFile inputReadFile) {
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
	 * @return the pairedMode
	 */
	public PairedMode getPairedMode() {
		return pairedMode;
	}

	/**
	 * @param pairedMode the pairedMode to set
	 */
	public void setPairedMode(PairedMode pairedMode) {
		this.pairedMode = pairedMode;
	}

	/**
	 * @return the pairedEndModeFile
	 */
	public HPGMethylFile getPairedEndModeFile() {
		return pairedEndModeFile;
	}

	/**
	 * @param pairedEndModeFile the pairedEndModeFile to set
	 */
	public void setPairedEndModeFile(HPGMethylFile pairedEndModeFile) {
		this.pairedEndModeFile = pairedEndModeFile;
	}

	/**
	 * @return the pairedMaxDistance
	 */
	public Integer getPairedMaxDistance() {
		return pairedMaxDistance;
	}

	/**
	 * @param pairedMaxDistance the pairedMaxDistance to set
	 */
	public void setPairedMaxDistance(Integer pairedMaxDistance) {
		this.pairedMaxDistance = pairedMaxDistance;
	}

	/**
	 * @return the pairedMinDistance
	 */
	public Integer getPairedMinDistance() {
		return pairedMinDistance;
	}

	/**
	 * @param pairedMinDistance the pairedMinDistance to set
	 */
	public void setPairedMinDistance(Integer pairedMinDistance) {
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
	public Integer getCalFlankSize() {
		return calFlankSize;
	}

	/**
	 * @param calFlankSize the calFlankSize to set
	 */
	public void setCalFlankSize(Integer calFlankSize) {
		this.calFlankSize = calFlankSize;
	}

	/**
	 * @return the minimumCalSize
	 */
	public Integer getMinimumCalSize() {
		return minimumCalSize;
	}

	/**
	 * @param minimumCalSize the minimumCalSize to set
	 */
	public void setMinimumCalSize(Integer minimumCalSize) {
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
	public Integer getMaximumBetweenSeeds() {
		return maximumBetweenSeeds;
	}

	/**
	 * @param maximumBetweenSeeds the maximumBetweenSeeds to set
	 */
	public void setMaximumBetweenSeeds(Integer maximumBetweenSeeds) {
		this.maximumBetweenSeeds = maximumBetweenSeeds;
	}

	/**
	 * @return the maximumSeedSize
	 */
	public Integer getMaximumSeedSize() {
		return maximumSeedSize;
	}

	/**
	 * @param maximumSeedSize the maximumSeedSize to set
	 */
	public void setMaximumSeedSize(Integer maximumSeedSize) {
		this.maximumSeedSize = maximumSeedSize;
	}

	/**
	 * @return the minimumSeedSize
	 */
	public Integer getMinimumSeedSize() {
		return minimumSeedSize;
	}

	/**
	 * @param minimumSeedSize the minimumSeedSize to set
	 */
	public void setMinimumSeedSize(Integer minimumSeedSize) {
		this.minimumSeedSize = minimumSeedSize;
	}

	/**
	 * @return the numberSeedsPerRead
	 */
	public Integer getNumberSeedsPerRead() {
		return numberSeedsPerRead;
	}

	/**
	 * @param numberSeedsPerRead the numberSeedsPerRead to set
	 */
	public void setNumberSeedsPerRead(Integer numberSeedsPerRead) {
		this.numberSeedsPerRead = numberSeedsPerRead;
	}

	/**
	 * @return the readMinimumDiscardLength
	 */
	public Integer getReadMinimumDiscardLength() {
		return readMinimumDiscardLength;
	}

	/**
	 * @param readMinimumDiscardLength the readMinimumDiscardLength to set
	 */
	public void setReadMinimumDiscardLength(Integer readMinimumDiscardLength) {
		this.readMinimumDiscardLength = readMinimumDiscardLength;
	}

	/**
	 * @return the readMaximumInnerGap
	 */
	public Integer getReadMaximumInnerGap() {
		return readMaximumInnerGap;
	}

	/**
	 * @param readMaximumInnerGap the readMaximumInnerGap to set
	 */
	public void setReadMaximumInnerGap(Integer readMaximumInnerGap) {
		this.readMaximumInnerGap = readMaximumInnerGap;
	}

	/**
	 * @return the minimumNumberSeeds
	 */
	public Integer getMinimumNumberSeeds() {
		return minimumNumberSeeds;
	}

	/**
	 * @param minimumNumberSeeds the minimumNumberSeeds to set
	 */
	public void setMinimumNumberSeeds(Integer minimumNumberSeeds) {
		this.minimumNumberSeeds = minimumNumberSeeds;
	}

	/**
	 * @return the filterReadMappings
	 */
	public Integer getFilterReadMappings() {
		return filterReadMappings;
	}

	/**
	 * @param filterReadMappings the filterReadMappings to set
	 */
	public void setFilterReadMappings(Integer filterReadMappings) {
		this.filterReadMappings = filterReadMappings;
	}

	/**
	 * @return the filterSeedMappings
	 */
	public Integer getFilterSeedMappings() {
		return filterSeedMappings;
	}

	/**
	 * @param filterSeedMappings the filterSeedMappings to set
	 */
	public void setFilterSeedMappings(Integer filterSeedMappings) {
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
	public Integer getReportNBest() {
		return reportNBest;
	}

	/**
	 * @param reportNBest the reportNBest to set
	 */
	public void setReportNBest(Integer reportNBest) {
		this.reportNBest = reportNBest;
	}

	/**
	 * @return the reportNHits
	 */
	public Integer getReportNHits() {
		return reportNHits;
	}

	/**
	 * @param reportNHits the reportNHits to set
	 */
	public void setReportNHits(Integer reportNHits) {
		this.reportNHits = reportNHits;
	}
}
