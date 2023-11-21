package uade.tpo.services.reclamo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uade.tpo.dao.DaoReclamoImpl;

import uade.tpo.models.entity.Reclamo;


@Service
public class ReclamoService implements IReclamoService {
    @Autowired
    private DaoReclamoImpl daoReclamoImpl;

    public ReclamoService() { }

    @Override
    public List<Reclamo> findAll() {
        return daoReclamoImpl.getAll();
    }

    @Override
    public Reclamo findById(int id) {
        return daoReclamoImpl.findById(id);
    }

    @Override
    public void save(Reclamo reclamo) {
        this.daoReclamoImpl.save(reclamo);
    }

    @Override
    public void update(int id, Reclamo reclamo) { 
    	this.daoReclamoImpl.update(reclamo);
    }

    @Override
    public void deleteById(int id) {
        daoReclamoImpl.delete(id);
    }

    @Override
    public List<Reclamo> getReclamosByEdificioId(int edificioId) {
        return this.daoReclamoImpl.getReclamosByEdificioId(edificioId);
    }
}
