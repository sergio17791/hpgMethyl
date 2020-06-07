package es.hpgMethyl.dao;

import java.io.Serializable;
import java.util.List;

import es.hpgMethyl.exceptions.HpgMethylException;

public interface BaseDAO<T, ID extends Serializable> {
	void  save(T entity) throws HpgMethylException;
	T get(ID id) throws HpgMethylException;
	void delete(T entity) throws HpgMethylException;
	List<T> listAll() throws HpgMethylException;
}
