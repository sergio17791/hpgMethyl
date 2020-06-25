package es.hpgMethyl.dao;

import java.io.Serializable;
import java.util.List;

import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.SaveObjectException;

public interface BaseDAO<T, ID extends Serializable> {
	void  save(T entity) throws SaveObjectException;
	T get(ID id) throws HpgMethylException;
	void delete(T entity) throws HpgMethylException;
	List<T> listAll() throws HpgMethylException;
}
