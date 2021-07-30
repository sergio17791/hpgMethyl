package es.hpgMethyl.junit.usecases.configuration;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveApplicationConfigurationException;
import es.hpgMethyl.exceptions.SaveObjectException;
import es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration.SaveApplicationConfiguration;
import es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration.SaveApplicationConfigurationRequest;
import es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration.SaveApplicationConfigurationResponse;

public class SaveApplicationConfigurationTest {

	private SaveApplicationConfiguration saveApplicationConfiguration;
	
	private ConfigurationDAO configurationDAO;
	
	private Configuration configuration;
	
	@Before
	public void setUp() {
		this.configurationDAO = Mockito.mock(ConfigurationDAO.class);
		this.saveApplicationConfiguration = new SaveApplicationConfiguration(this.configurationDAO);
		this.configuration = new Configuration(
				UUID.randomUUID(), 
				new Date(), 
				new Date(),
				"/data/tomcat/files/users/",
				"/opt/hpg-methyl",
				"/data/tomcat/genome/bs-index/",
				4,
				4,
				2,
				null,
				null,
				null
		);
	}
	
	@Test(expected = SaveApplicationConfigurationException.class)
	public void test_execute_givenConstraintViolation_expectThrowSaveApplicationConfigurationException() throws HpgMethylException {
		
		Mockito.doReturn(configuration).when(configurationDAO).getApplicationConfiguration();
		
		Mockito.doThrow(SaveObjectException.class).when(configurationDAO).save(Mockito.any(Configuration.class));
		
		SaveApplicationConfigurationRequest request = new SaveApplicationConfigurationRequest(
				"/data/tomcat/files/new_users/", 
				"/opt/hpg-methyl", 
				"/data/tomcat/new_genome/bs-index/", 
				5, 
				6, 
				1, 
				Long.valueOf(20),
				6, 
				9
		);
		
		this.saveApplicationConfiguration.execute(request);
	}	
	
	public void test_execute_givenNonExistentConfiguration_expectConfiguration() throws HpgMethylException {
		
		Mockito.doReturn(null).when(configurationDAO).getApplicationConfiguration();
		
		Mockito.doNothing().when(configurationDAO).save(Mockito.any(Configuration.class));
		
		String usersDirectoryAbsolutePath = "/data/tomcat/files/users/new/";
		String hpgMethylAbsolutePath = "/opt/hpg-methyl";
		String bwtIndexAbsolutePath = "/data/tomcat/genome/bs-index/new/";
		Integer cpuThreads = 2;
		Integer maximumUserAnalysisPending = 3;
		Integer maximumUserFilesStored = 2;	
		Long fileSizeLimit = Long.valueOf(20);
		Integer readBatchSize = 5;
		Integer writeBatchSize = 4;
		
		SaveApplicationConfigurationRequest request = new SaveApplicationConfigurationRequest(
				usersDirectoryAbsolutePath, 
				hpgMethylAbsolutePath, 
				bwtIndexAbsolutePath, 
				cpuThreads, 
				maximumUserAnalysisPending, 
				maximumUserFilesStored, 
				fileSizeLimit,
				readBatchSize, 
				writeBatchSize
		);
		
		SaveApplicationConfigurationResponse useCaseResponse = this.saveApplicationConfiguration.execute(request);
		
		Configuration response = useCaseResponse.getConfiguration();
		
		Assert.assertEquals(request.getUsersDirectoryAbsolutePath(), response.getUsersDirectoryAbsolutePath());
		Assert.assertEquals(request.getHpgMethylAbsolutePath(), response.getHpgMethylAbsolutePath());
		Assert.assertEquals(request.getBwtIndexAbsolutePath(), response.getBwtIndexAbsolutePath());
		Assert.assertEquals(request.getCpuThreads(), response.getCpuThreads());
		Assert.assertEquals(request.getMaximumUserAnalysisPending(), response.getMaximumUserAnalysisPending());
		Assert.assertEquals(request.getMaximumUserFilesStored(), response.getMaximumUserFilesStored());
		Assert.assertEquals(request.getFileSizeLimit(), response.getFileSizeLimit());
		Assert.assertEquals(request.getReadBatchSize(), response.getReadBatchSize());
		Assert.assertEquals(request.getWriteBatchSize(), response.getWriteBatchSize());
	}	
	
public void test_execute_givenExistentConfiguration_expectConfiguration() throws HpgMethylException {
		
		Mockito.doReturn(null).when(configurationDAO).getApplicationConfiguration();
		
		Mockito.doNothing().when(configurationDAO).save(Mockito.any(Configuration.class));
		
		String usersDirectoryAbsolutePath = "/data/tomcat/files/users/updated/";
		String hpgMethylAbsolutePath = "/opt/hpg-methyl";
		String bwtIndexAbsolutePath = "/data/tomcat/genome/bs-index/updated/";
		Integer cpuThreads = 8;
		Integer maximumUserAnalysisPending = 7;
		Integer maximumUserFilesStored = 9;	
		Long fileSizeLimit = Long.valueOf(30);
		Integer readBatchSize = 10000;
		Integer writeBatchSize = 20000;
		
		SaveApplicationConfigurationRequest request = new SaveApplicationConfigurationRequest(
				usersDirectoryAbsolutePath, 
				hpgMethylAbsolutePath, 
				bwtIndexAbsolutePath, 
				cpuThreads, 
				maximumUserAnalysisPending, 
				maximumUserFilesStored, 
				fileSizeLimit,
				readBatchSize, 
				writeBatchSize
		);
		
		SaveApplicationConfigurationResponse useCaseResponse = this.saveApplicationConfiguration.execute(request);
		
		Configuration response = useCaseResponse.getConfiguration();
		
		Assert.assertEquals(request.getUsersDirectoryAbsolutePath(), response.getUsersDirectoryAbsolutePath());
		Assert.assertEquals(request.getHpgMethylAbsolutePath(), response.getHpgMethylAbsolutePath());
		Assert.assertEquals(request.getBwtIndexAbsolutePath(), response.getBwtIndexAbsolutePath());
		Assert.assertEquals(request.getCpuThreads(), response.getCpuThreads());
		Assert.assertEquals(request.getMaximumUserAnalysisPending(), response.getMaximumUserAnalysisPending());
		Assert.assertEquals(request.getMaximumUserFilesStored(), response.getMaximumUserFilesStored());
		Assert.assertEquals(request.getFileSizeLimit(), response.getFileSizeLimit());
		Assert.assertEquals(request.getReadBatchSize(), response.getReadBatchSize());
		Assert.assertEquals(request.getWriteBatchSize(), response.getWriteBatchSize());
	}
}
