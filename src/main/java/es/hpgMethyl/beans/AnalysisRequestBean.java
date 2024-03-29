package es.hpgMethyl.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.file.GetFile.GetFile;
import es.hpgMethyl.usecases.file.GetFile.GetFileRequest;
import es.hpgMethyl.usecases.file.GetFile.GetFileResponse;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;

@ManagedBean(name="analysisBean")
@ViewScoped
public class AnalysisRequestBean implements Serializable {
	
	private static final long serialVersionUID = -6282901190770748985L;
	
	private UUID id;
	private User user;
	private String identifier;
	private AnalysisStatus status;
	private PairedMode pairedMode;
	private HPGMethylFile inputReadFile;
	private Boolean writeMethylationContext;
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
	private Integer number;
	private Date createdAt;
	private Date updatedAt;
	private List<HPGMethylFile> userFiles;

	public AnalysisRequestBean() {
		this.id = null;
		this.user = null;
		this.identifier = "";
		this.pairedMode = PairedMode.SINGLE_END_MODE;
		this.status = null;
		this.inputReadFile = null;
		this.writeMethylationContext = true;
		this.pairedEndModeFile = null;
		this.pairedMaxDistance = null;
		this.pairedMinDistance = null;
		this.swaMinimunScore = new BigDecimal("0.8");
		this.swaMatchScore = new BigDecimal("5.0");
		this.swaMismatchScore = new BigDecimal("-4.0");
		this.swaGapOpen = new BigDecimal("10.0");
		this.swaGapExtend = new BigDecimal("0.5");
		this.calFlankSize = 5;
		this.minimumCalSize = 30;
		this.calUmbralLengthFactor = new BigDecimal("4");
		this.maximumBetweenSeeds = 100;
		this.maximumSeedSize = 23;
		this.minimumSeedSize = 16;
		this.numberSeedsPerRead = 10;
		this.readMinimumDiscardLength = 100;
		this.readMaximumInnerGap = -1;
		this.minimumNumberSeeds = null;
		this.filterReadMappings = null;
		this.filterSeedMappings = null;
		this.reportAll = false;
		this.reportBest = true;
		this.reportNBest = null;
		this.reportNHits = null;
		this.number = null;
		this.createdAt = null;
		this.updatedAt = null;
		this.userFiles = new ArrayList<HPGMethylFile>();
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
	 * @return the inputReadFile
	 */
	public HPGMethylFile getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the readInputFile to set
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

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the userFiles
	 */
	public List<HPGMethylFile> getUserFiles() {
		return userFiles;
	}

	/**
	 * @param userFiles the userFiles to set
	 */
	public void setUserFiles(List<HPGMethylFile> userFiles) {
		this.userFiles = userFiles;
	}
	
	public void loadAnalysisRequest(AnalysisRequest analysisRequest) {
		this.id = analysisRequest.getId();
		this.user = analysisRequest.getUser();
		this.identifier = analysisRequest.getIdentifier();
		this.status = analysisRequest.getStatus();
		this.pairedMode = analysisRequest.getPairedMode();
		this.inputReadFile = analysisRequest.getInputReadFile();
		this.writeMethylationContext = analysisRequest.getWriteMethylationContext();
		this.pairedEndModeFile = analysisRequest.getPairedEndModeFile();
		this.pairedMaxDistance = analysisRequest.getPairedMaxDistance();
		this.pairedMinDistance = analysisRequest.getPairedMinDistance();
		this.swaMinimunScore = analysisRequest.getSwaMinimunScore();
		this.swaMatchScore = analysisRequest.getSwaMatchScore();
		this.swaMismatchScore = analysisRequest.getSwaMismatchScore();
		this.swaGapOpen = analysisRequest.getSwaGapOpen();
		this.swaGapExtend = analysisRequest.getSwaGapExtend();
		this.calFlankSize = analysisRequest.getCalFlankSize();
		this.minimumCalSize = analysisRequest.getMinimumCalSize();
		this.calUmbralLengthFactor = analysisRequest.getCalUmbralLengthFactor();
		this.maximumBetweenSeeds = analysisRequest.getMaximumBetweenSeeds();
		this.maximumSeedSize = analysisRequest.getMaximumSeedSize();
		this.minimumSeedSize = analysisRequest.getMinimumCalSize();
		this.numberSeedsPerRead = analysisRequest.getNumberSeedsPerRead();
		this.readMinimumDiscardLength = analysisRequest.getReadMinimumDiscardLength();
		this.readMaximumInnerGap = -analysisRequest.getReadMaximumInnerGap();
		this.minimumNumberSeeds = analysisRequest.getMinimumNumberSeeds();
		this.filterReadMappings = analysisRequest.getFilterReadMappings();
		this.filterSeedMappings = analysisRequest.getFilterSeedMappings();
		this.reportAll = analysisRequest.getReportAll();
		this.reportBest = analysisRequest.getReportBest();
		this.reportNBest = analysisRequest.getReportNBest();
		this.reportNHits = analysisRequest.getReportNHits();
		this.number = analysisRequest.getNumber();	
		this.createdAt = analysisRequest.getCreatedAt();
		this.updatedAt = analysisRequest.getUpdatedAt();		
	}
	
	public void loadUserFiles() {
		
		if(user != null) {
			ListUserFilesResponse listUserFilesResponse = new ListUserFiles(new HPGMethylFileDAOHibernate()).execute(
					new ListUserFilesRequest(user, true)
			);
			
			this.userFiles = listUserFilesResponse.getFiles();
		}		
	}
	
	public Boolean checkFile(HPGMethylFile file) {
		
		Boolean validated;
		
		try {
			GetFileResponse response = new GetFile(new HPGMethylFileDAOHibernate()).execute(
					new GetFileRequest(file.getId())
			);
				
			validated = response.getFile().getStored();
			
		} catch (GetObjectException | FileNotFound e) {
			validated = Boolean.FALSE;
		}
		
		return validated;
	}
}
