package uade.tpo.dao;

import java.util.List;

public interface DAO<T> {
	List<T> getAll() throws Exception;
	void save(T persistible) throws Exception;
	void update(T persistible) throws Exception;
	void delete(int id) throws Exception;	
}

