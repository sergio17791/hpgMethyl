package es.hpgMethyl.dao;

import java.util.UUID;

import es.hpgMethyl.entities.File;

public interface FileDAO extends BaseDAO<File, UUID> {
	Boolean existsFile(String fileName, String folder);
}
