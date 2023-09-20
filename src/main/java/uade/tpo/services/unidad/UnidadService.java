package uade.tpo.services.unidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uade.tpo.dao.DaoUnidadImpl;
import uade.tpo.models.Unidad;

@Service
public class UnidadService implements IUnidadService {
	@Autowired
	private DaoUnidadImpl daoUnidadImpl;
	
	public UnidadService() {
		
	}

	@Override
	public List<Unidad> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Unidad findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Unidad cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int id, Unidad cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
