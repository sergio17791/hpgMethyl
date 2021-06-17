package es.hpgMethyl.dao;

import java.util.UUID;

import es.hpgMethyl.entities.Configuration;

public interface ConfigurationDAO extends BaseDAO<Configuration, UUID> {
	Configuration getApplicationConfiguration();
}
