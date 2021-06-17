package es.hpgMethyl.usecases.configuration.GetApplicationConfiguration;

import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.exceptions.ConfigurationNotFound;

public class GetApplicationConfiguration {

	private ConfigurationDAO configurationDAO;

	public GetApplicationConfiguration(ConfigurationDAO configurationDAO) {
		this.configurationDAO = configurationDAO;
	}
	
	public GetApplicationConfigurationResponse execute() throws ConfigurationNotFound {
		
		Configuration configuration = this.configurationDAO.getApplicationConfiguration();
		
		if(configuration == null) {
			throw new ConfigurationNotFound("No configuration found");
		}
			
		return new GetApplicationConfigurationResponse(configuration);	
	}
}
