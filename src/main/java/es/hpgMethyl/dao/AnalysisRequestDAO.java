package es.hpgMethyl.dao;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.AnalysisRequestNotFound;

public interface AnalysisRequestDAO extends BaseDAO<AnalysisRequest, String> {
	AnalysisRequest findByIdentifier(User user, String identifier) throws AnalysisRequestNotFound;
}
