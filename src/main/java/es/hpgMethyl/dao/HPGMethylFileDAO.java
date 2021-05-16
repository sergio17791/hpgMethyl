package es.hpgMethyl.dao;

import java.util.UUID;

import es.hpgMethyl.entities.HPGMethylFile;

public interface HPGMethylFileDAO extends BaseDAO<HPGMethylFile, UUID> {
	Boolean existsFile(String fileName, String folder);
}
