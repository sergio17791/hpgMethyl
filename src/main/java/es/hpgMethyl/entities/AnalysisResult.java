package es.hpgMethyl.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "analysis_result")
public class AnalysisResult extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "analysis_request", nullable = false, unique = true)
	private AnalysisRequest analysisRequest;

	@ManyToOne
	@JoinColumn(name = "result_file", nullable = false)
	private HPGMethylFile resultFile;
	
	public AnalysisResult() {
		super();
		this.analysisRequest = null;
		this.resultFile = null;
	}
	
	public AnalysisResult(			
			UUID id, 
			Date createdAt, 
			Date updatedAt,
			AnalysisRequest analysisRequest,
			HPGMethylFile resultFile
	) {
		super(id, createdAt, updatedAt);
		this.analysisRequest = analysisRequest;
		this.resultFile = resultFile;
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
}
