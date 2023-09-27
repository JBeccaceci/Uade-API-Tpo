package uade.tpo.services.edificio;

import uade.tpo.models.entity.Edificio;

import java.util.List;

public interface IEdificioService {
    public List<Edificio> findAll();

    public Edificio findById(int id);

    public void save(Edificio cliente);

    public void update(int id, Edificio cliente);

    public void deleteById(int id);
}
