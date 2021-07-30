package es.hpgMethyl.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;

@Entity
@Table(name = "analysis_request")
public class AnalysisRequest extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name="\"user\"", nullable=false)
	private User user;

	@Column(name = "identifier", nullable = false)
	private String identifier;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private AnalysisStatus status;
	
	@ManyToOne
	@JoinColumn(name = "input_read_file", nullable = false)
	private HPGMethylFile inputReadFile;
	
	@Column(name = "write_methylation_context", nullable = false)
	private Boolean writeMethylationContext;
	
	@Column(name = "paired_mode", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private PairedMode pairedMode;
	
	@ManyToOne
	@JoinColumn(name = "paired_end_mode_file", nullable = true)
	private HPGMethylFile pairedEndModeFile;
	
	@Column(name = "paired_max_distance", nullable = true)
	private Integer pairedMaxDistance;
	
	@Column(name = "paired_min_distance", nullable = true)
	private Integer pairedMinDistance;
	
	@Column(name = "swa_minimun_score", nullable = true)
	private BigDecimal swaMinimunScore;
	
	@Column(name = "swa_match_score", nullable = true)
	private BigDecimal swaMatchScore;
	
	@Column(name = "swa_mismatch_score", nullable = true)
	private BigDecimal swaMismatchScore;
	
	@Column(name = "swa_gap_open", nullable = true)
	private BigDecimal swaGapOpen;
	
	@Column(name = "swa_gap_extend", nullable = true)
	private BigDecimal swaGapExtend;
	
	@Column(name = "cal_flank_size", nullable = true)
	private Integer calFlankSize;
	
	@Column(name = "minimum_cal_size", nullable = true)
	private Integer minimumCalSize;
	
	@Column(name = "cal_umbral_length_factor", nullable = true)
	private BigDecimal calUmbralLengthFactor;
	
	@Column(name = "maximum_between_seeds", nullable = true)
	private Integer maximumBetweenSeeds;
	
	@Column(name = "maximum_seed_size", nullable = true)
	private Integer maximumSeedSize;
	
	@Column(name = "minimum_seed_size", nullable = true)
	private Integer minimumSeedSize;
	
	@Column(name = "number_seeds_per_read", nullable = true)
	private Integer numberSeedsPerRead;
	
	@Column(name = "read_minimum_discard_length", nullable = true)
	private Integer readMinimumDiscardLength;
	
	@Column(name = "read_maximum_inner_gap", nullable = true)
	private Integer readMaximumInnerGap;
	
	@Column(name = "minimum_number_seeds", nullable = true)
	private Integer minimumNumberSeeds;
	
	@Column(name = "filter_read_mappings", nullable = true)
	private Integer filterReadMappings;
	
	@Column(name = "filter_seed_mappings", nullable = true)
	private Integer filterSeedMappings;
	
	@Column(name = "report_all", nullable = false)
	private Boolean reportAll;
	
	@Column(name = "report_best", nullable = false)
	private Boolean reportBest;
	
	@Column(name = "report_n_best", nullable = true)
	private Integer reportNBest;
	
	@Column(name = "report_n_hits", nullable = true)
	private Integer reportNHits;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analysis_number_sequence")
	@Column(name = "number", unique = true, nullable = false, insertable = false, updatable = false)
	private Integer number;
	
	public AnalysisRequest() {
		super();
		this.status = AnalysisStatus.CREATED;
		this.writeMethylationContext = false;
		this.reportAll = false;
		this.reportBest = true;
	}

	public AnalysisRequest(			
			UUID id, 
			Date createdAt, 
			Date updatedAt,
			User user,
			String identifier, 
			AnalysisStatus status,
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
			Integer reportNHits,
			Integer number
	) {
		super(id, createdAt, updatedAt);
		this.user = user;
		this.identifier = identifier;
		this.status = status;
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
		this.number = number;
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
	 * @return the status
	 */
	public AnalysisStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AnalysisStatus status) {
		this.status = status;
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
	
	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
}
