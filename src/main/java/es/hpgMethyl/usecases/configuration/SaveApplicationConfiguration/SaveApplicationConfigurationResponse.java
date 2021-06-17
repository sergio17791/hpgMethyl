package es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration;

import es.hpgMethyl.entities.Configuration;

public class SaveApplicationConfigurationResponse {

	private Configuration configuration;

	public SaveApplicationConfigurationResponse(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
}
