package uade.tpo.services.areascomunes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uade.tpo.dao.DaoAreaComunImpl;
import uade.tpo.models.entity.AreaComun;


public class AreaComunService implements IAreaComunService {
	@Autowired
	private DaoAreaComunImpl daoAreaComunImpl;
	
	public AreaComunService() {
		
	}



	@Override
	public void deleteById(int id) {
		daoAreaComunImpl.delete(id);
		
		
	}

	@Override
	public void save(AreaComun cliente) {
		this.daoAreaComunImpl.save(cliente);
		
	}

	@Override
	public void update(int id, AreaComun cliente) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<AreaComun> findAll() {
		// TODO Auto-generated method stub
		return daoAreaComunImpl.getAll();
	}



	@Override
	public AreaComun findById(int id) {
		// TODO Auto-generated method stub
		return daoAreaComunImpl.findById(id);
	}
	



}
