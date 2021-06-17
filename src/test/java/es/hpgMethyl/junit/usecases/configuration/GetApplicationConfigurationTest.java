package es.hpgMethyl.junit.usecases.configuration;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.exceptions.ConfigurationNotFound;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.usecases.configuration.GetApplicationConfiguration.GetApplicationConfiguration;
import es.hpgMethyl.usecases.configuration.GetApplicationConfiguration.GetApplicationConfigurationResponse;

public class GetApplicationConfigurationTest {

	private GetApplicationConfiguration getApplicationConfiguration;
	
	private ConfigurationDAO configurationDAO;
	
	@Before
	public void setUp() {
		this.configurationDAO = Mockito.mock(ConfigurationDAO.class);
		this.getApplicationConfiguration = new GetApplicationConfiguration(this.configurationDAO);
	}
	
	@Test(expected = ConfigurationNotFound.class)
	public void test_execute_givenNonExistentConfiguration_expectThrowConfigurationNotFound() throws HpgMethylException {
				
		Mockito.doReturn(null).when(configurationDAO).getApplicationConfiguration();
		
		this.getApplicationConfiguration.execute();
	}
	
	@Test
	public void test_execute_givenExistentConfiguration_expectConfiguration() throws HpgMethylException {
		
		UUID id = UUID.randomUUID();
		
		Configuration configuration = new Configuration();
		configuration.setId(id);
		
		Mockito.doReturn(configuration).when(configurationDAO).getApplicationConfiguration();
		
		GetApplicationConfigurationResponse response = this.getApplicationConfiguration.execute();
		
		Assert.assertEquals(id, response.getConfiguration().getId());
	}
}
