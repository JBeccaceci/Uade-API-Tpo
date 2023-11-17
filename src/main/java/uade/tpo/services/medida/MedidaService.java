package uade.tpo.services.medida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uade.tpo.dao.DaoMedidaImpl;
import uade.tpo.models.entity.Medida;

import java.util.List; 

@Service
public class MedidaService implements IMedidaService {
	@Autowired
	private DaoMedidaImpl daoMedidaImpl;
	
	private MedidaService() {
	}

	@Override
    public List<Medida> findAll() {
        return daoMedidaImpl.getAll();
    }

    @Override
    public Medida findById(int id) {
        return daoMedidaImpl.findById(id);
    }

    @Override
    public void save(Medida edificio) {
        this.daoMedidaImpl.save(edificio);
    }

    @Override
    public void update(int id, Medida cliente) { }

    @Override
    public void deleteById(int id) {
        daoMedidaImpl.delete(id);
    }

    @Override
    public List<Medida> getMedidaReclamo(int reclamoId) {
        return daoMedidaImpl.getMedidaReclamo(reclamoId);
    }
}
