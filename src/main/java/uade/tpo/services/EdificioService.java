package uade.tpo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uade.tpo.dao.DaoEdificioImpl;
import uade.tpo.models.Edificio;

import java.util.List;

@Service
public class EdificioService implements IEdificioService {
    @Autowired
    private DaoEdificioImpl daoEdificio;

    @Override
    public List<Edificio> findAll() {
        return daoEdificio.getAll();
    }

    @Override
    public Edificio findById(int id) {
        return daoEdificio.findById(id);
    }

    @Override
    public void save(Edificio edificio) {
        daoEdificio.save(edificio);
    }

    @Override
    public void update(int id, Edificio cliente) {

    }

    @Override
    public void deleteById(int id) {
        daoEdificio.delete(id);
    }
}
