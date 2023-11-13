package uade.tpo.services.unidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uade.tpo.dao.DaoUnidadImpl;
import uade.tpo.models.UnidadUsuarioDTO;
import uade.tpo.models.entity.Unidad;

@Service
public class UnidadService implements IUnidadService {
	@Autowired
	private DaoUnidadImpl daoUnidadImpl;
	
	public UnidadService() { }

	@Override
	public List<Unidad> findAll() {
		return daoUnidadImpl.getAll();
	}

	@Override
	public Unidad findById(int id) {
		return daoUnidadImpl.findById(id);
	}

	@Override
	public void save(Unidad unidad) {
		this.daoUnidadImpl.save(unidad);
	}

	@Override
	public void update(int id, Unidad unidad) { }

	@Override
	public void deleteById(int id) {
		daoUnidadImpl.delete(id);
	}

	public List<UnidadUsuarioDTO> getUnitsByOccupant(int usuarioId, int edificioId) {
		return daoUnidadImpl.getUnitsByOccupant(usuarioId, edificioId);
	}

}
