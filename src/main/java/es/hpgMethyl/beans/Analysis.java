package es.hpgMethyl.beans;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.servlet.http.Part;

public class Analysis {
	
	private String identifier;
	private String pairedMode;
	private Part inputReadFile;
	private BigDecimal swaMinimunScore;
	private BigDecimal swaMatchScore;
	private BigDecimal swaMismatchScore;
	private BigDecimal swaGapOpen;
	private BigDecimal swaGapExtend;
	private UIComponent sendAnalysisComponent;

	public Analysis() {
		this.identifier = "";
		this.pairedMode = "0";
		this.inputReadFile = null;
		this.swaMinimunScore = new BigDecimal("0.8");
		this.swaMatchScore = new BigDecimal("5.0");
		this.swaMismatchScore = new BigDecimal("-4.0");
		this.swaGapOpen = new BigDecimal(10.0);
		this.swaGapExtend = new BigDecimal(0.5);
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
	public String getPairedMode() {
		return pairedMode;
	}

	/**
	 * @param pairedMode the pairedMode to set
	 */
	public void setPairedMode(String pairedMode) {
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
		return "";
	}
}
