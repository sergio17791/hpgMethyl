package es.hpgMethyl.dao;

import java.io.Serializable;
import java.util.List;

import es.hpgMethyl.exceptions.DeleteObjectException;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.exceptions.SaveObjectException;

public interface BaseDAO<T, ID extends Serializable> {
	void  save(T entity) throws SaveObjectException;
	T get(ID id) throws GetObjectException;
	void delete(T entity) throws DeleteObjectException;
	List<T> listAll() throws ListObjectsException;
}
