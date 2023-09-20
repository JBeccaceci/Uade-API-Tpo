package uade.tpo.services.propietario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uade.tpo.dao.DaoPropietarioImpl;
import uade.tpo.models.Propietario;
@Service
public class PropietarioService implements IPropietarioService {
	@Autowired
	private DaoPropietarioImpl daoPropietarioImpl;
	
	public PropietarioService() {
		
	}

	@Override
	public List<Propietario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Propietario findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Propietario cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int id, Propietario cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
