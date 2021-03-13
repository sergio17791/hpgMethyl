package es.hpgMethyl.beans;

import java.io.IOException;
import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.servlet.http.Part;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateMethylationAnalysisException;
import es.hpgMethyl.exceptions.DuplicatedIdentifier;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.CreateMethylationAnalysis.CreateMethylationAnalysisRequest;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

public class Analysis {
	
	private String identifier;
	private Integer pairedMode;
	private Part inputReadFile;
	private Boolean writeMethylationContext;
	private Boolean readBatchSize;
	private Boolean writeBatchSize;
	private Part pairedEndModeFile;
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
	private UIComponent sendAnalysisComponent;

	public Analysis() {
		this.identifier = "";
		this.pairedMode = 0;
		this.inputReadFile = null;
		this.writeMethylationContext = false;
		this.readBatchSize = false;
		this.writeBatchSize = false;
		this.pairedEndModeFile = null;
		this.pairedMaxDistance = null;
		this.pairedMinDistance = null;
		this.swaMinimunScore = new BigDecimal("0.8");
		this.swaMatchScore = new BigDecimal("5.0");
		this.swaMismatchScore = new BigDecimal("-4.0");
		this.swaGapOpen = new BigDecimal("10.0");
		this.swaGapExtend = new BigDecimal("0.5");
		this.calFlankSize = new BigDecimal("5");
		this.minimumCalSize = new BigDecimal("30");
		this.calUmbralLengthFactor = new BigDecimal("4");
		this.maximumBetweenSeeds = new BigDecimal("100");
		this.maximumSeedSize = new BigDecimal("23");
		this.minimumSeedSize = new BigDecimal("16");
		this.numberSeedsPerRead = new BigDecimal("10");
		this.readMinimumDiscardLength = new BigDecimal("100");
		this.readMaximumInnerGap = new BigDecimal("-1");
		this.minimumNumberSeeds = null;
		this.filterReadMappings = null;
		this.filterSeedMappings = null;
		this.reportAll = false;
		this.reportBest = true;
		this.reportNBest = null;
		this.reportNHits = null;
		this.sendAnalysisComponent = null;
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
	 * @return the inputReadFile
	 */
	public Part getInputReadFile() {
		return inputReadFile;
	}

	/**
	 * @param inputReadFile the readInputFile to set
	 */
	public void setInputReadFile(Part inputReadFile) {
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
	 * @return the pairedEndModeFile
	 */
	public Part getPairedEndModeFile() {
		return pairedEndModeFile;
	}

	/**
	 * @param pairedEndModeFile the pairedEndModeFile to set
	 */
	public void setPairedEndModeFile(Part pairedEndModeFile) {
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

	/**
	 * @return the sendAnalysisComponent
	 */
	public UIComponent getSendAnalysisComponent() {
		return sendAnalysisComponent;
	}

	/**
	 * @param sendAnalysisComponent the sendAnalysisComponent to set
	 */
	public void setSendAnalysisComponent(UIComponent sendAnalysisComponent) {
		this.sendAnalysisComponent = sendAnalysisComponent;
	}

	public String sendAnalysis() {
		
		User user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user == null) {
			return "index?faces-redirect=true";
		}
		
		String inputReadFileName = this.getInputReadFile().getSubmittedFileName();
		
		String pairedEndModeFile = null;
		
		if(this.getPairedEndModeFile() != null) {
			pairedEndModeFile = this.getPairedEndModeFile().getSubmittedFileName();
		}
		
		try {
			FileUtils.saveFileUploadedByUser(user, this.getInputReadFile(), inputReadFileName);
			
			new CreateMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute(
				new CreateMethylationAnalysisRequest(
					user,
					this.getIdentifier(),
					inputReadFileName,
					this.getWriteMethylationContext(), 
					this.getReadBatchSize(), 
					this.getWriteBatchSize(), 
					this.getPairedMode() == 1 ? PairedMode.PAIRED_END_MODE : PairedMode.SINGLE_END_MODE,
					pairedEndModeFile, 
					this.getPairedMaxDistance(), 
					this.getPairedMinDistance(),
					this.getSwaMinimunScore(), 
					this.getSwaMatchScore(), 
					this.getSwaMismatchScore(), 
					this.getSwaGapOpen(),
					this.getSwaGapExtend(), 
					this.getCalFlankSize(), 
					this.getMinimumCalSize(),
					this.getCalUmbralLengthFactor(), 
					this.getMaximumBetweenSeeds(), 
					this.getMaximumSeedSize(),
					this.getMinimumSeedSize(), 
					this.getNumberSeedsPerRead(), 
					this.getReadMinimumDiscardLength(),
					this.getReadMaximumInnerGap(), 
					this.getMinimumNumberSeeds(), 
					this.getFilterReadMappings(),
					this.getFilterSeedMappings(), 
					this.getReportAll(), 
					this.getReportBest(), 
					this.getReportNBest(),
					this.getReportNHits()
				)				
			);
			
			String successMessage = FacesContextUtils.geti18nMessage("analysis.requestSentSuccessfully");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_INFO, successMessage, successMessage);

		} catch (CreateMethylationAnalysisException e) {
			String defaultErrorMessage = e.getMessage();//FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		} catch (DuplicatedIdentifier e) {
			String duplicatedIdentifierErrorMessage = e.getMessage();//FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, duplicatedIdentifierErrorMessage, duplicatedIdentifierErrorMessage);
		} catch (IOException e) {
			String defaultErrorMessage = e.getMessage();//FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getSendAnalysisComponent(), FacesMessage.SEVERITY_ERROR, defaultErrorMessage, defaultErrorMessage);
		}
		
		return "analysis";
	}
}
