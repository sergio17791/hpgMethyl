package es.hpgMethyl.dao;

import java.util.List;
import java.util.UUID;

import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;

public interface HPGMethylFileDAO extends BaseDAO<HPGMethylFile, UUID> {
	Boolean existsFile(User user, String fileName, Boolean stored);
	List<HPGMethylFile> list(User user);	
}
