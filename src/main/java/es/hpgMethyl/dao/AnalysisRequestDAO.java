package es.hpgMethyl.dao;

import java.util.List;
import java.util.UUID;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;

public interface AnalysisRequestDAO extends BaseDAO<AnalysisRequest, UUID> {
	AnalysisRequest findByIdentifier(User user, String identifier);
	List<AnalysisRequest> list(User user);
}
