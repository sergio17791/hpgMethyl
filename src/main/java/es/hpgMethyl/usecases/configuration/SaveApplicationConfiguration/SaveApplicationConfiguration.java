package es.hpgMethyl.usecases.configuration.SaveApplicationConfiguration;

import es.hpgMethyl.dao.ConfigurationDAO;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.exceptions.SaveApplicationConfigurationException;
import es.hpgMethyl.exceptions.SaveObjectException;

public class SaveApplicationConfiguration {

	private ConfigurationDAO configurationDAO;

	public SaveApplicationConfiguration(ConfigurationDAO configurationDAO) {
		this.configurationDAO = configurationDAO;
	}
	
	public SaveApplicationConfigurationResponse execute(SaveApplicationConfigurationRequest request) throws SaveApplicationConfigurationException {
		
		Configuration configuration = this.configurationDAO.getApplicationConfiguration();
		
		if(configuration == null) {
			configuration = new Configuration();
		}
		
		configuration.setUsersDirectoryAbsolutePath(request.getUsersDirectoryAbsolutePath());
		configuration.setHpgMethylAbsolutePath(request.getHpgMethylAbsolutePath());
		configuration.setBwtIndexAbsolutePath(request.getBwtIndexAbsolutePath());
		configuration.setCpuThreads(request.getCpuThreads());
		configuration.setMaximumUserAnalysisPending(request.getMaximumUserAnalysisPending());
		configuration.setMaximumUserFilesStored(request.getMaximumUserFilesStored());
		configuration.setFileSizeLimit(request.getFileSizeLimit());
		configuration.setReadBatchSize(request.getReadBatchSize());
		configuration.setWriteBatchSize(request.getWriteBatchSize());
		
		try {
			this.configurationDAO.save(configuration);
		} catch (SaveObjectException e) {
			throw new SaveApplicationConfigurationException(e);
		}
		
		return new SaveApplicationConfigurationResponse(configuration);
	}
}
