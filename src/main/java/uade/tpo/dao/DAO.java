package uade.tpo.dao;

import uade.tpo.models.Edificio;

import java.util.List;

public interface DAO<T> {
	List<T> getAll() throws Exception;
	T findById(int id);
	void save(T persistible) throws Exception;
	void update(T persistible) throws Exception;
	void delete(int id) throws Exception;	
}

