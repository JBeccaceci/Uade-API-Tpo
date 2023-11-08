package uade.tpo.dao.definition;

import java.util.List;

import uade.tpo.models.entity.Edificio;

public interface DAO<T> {
	List<T> getAll() throws Exception;
	T findById(int id);
	void save(T persistible) throws Exception;
	void update(T persistible) throws Exception;
	void delete(int id) throws Exception;	
	
	
	List<Edificio> getEdificiosRelacionados(int usuarioId);
}

