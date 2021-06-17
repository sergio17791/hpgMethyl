package es.hpgMethyl.usecases.configuration.GetApplicationConfiguration;

import es.hpgMethyl.entities.Configuration;

public class GetApplicationConfigurationResponse {

	private Configuration configuration;

	public GetApplicationConfigurationResponse(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
}
