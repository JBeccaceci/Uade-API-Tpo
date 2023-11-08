package uade.tpo.services.edificio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uade.tpo.dao.DaoEdificioImpl;
import uade.tpo.models.entity.Edificio;

import java.util.List;

@Service
public class EdificioService implements IEdificioService {
    @Autowired
    private DaoEdificioImpl daoEdificioImpl;

    public EdificioService() {
    }

    @Override
    public List<Edificio> findAll() {
        return daoEdificioImpl.getAll();
    }

    @Override
    public Edificio findById(int id) {
        return daoEdificioImpl.findById(id);
    }

    @Override
    public void save(Edificio edificio) {
        this.daoEdificioImpl.save(edificio);
    }

    @Override
    public void update(int id, Edificio cliente) {
    	

    }

    @Override
    public void deleteById(int id) {
        daoEdificioImpl.delete(id);
    }

	@Override
	public List<Edificio> findAllRelacionados(int usuarioId) {
		// TODO Auto-generated method stub
		return daoEdificioImpl.getEdificiosRelacionados(usuarioId);
	}
}
