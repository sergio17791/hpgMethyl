package es.hpgMethyl.dao;

import java.util.UUID;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.AnalysisResult;

public interface AnalysisResultDAO extends BaseDAO<AnalysisResult, UUID> {
	AnalysisResult findByAnalysisRequest(AnalysisRequest analysisRequest);
}
