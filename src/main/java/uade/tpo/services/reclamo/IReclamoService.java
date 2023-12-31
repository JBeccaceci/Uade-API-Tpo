package uade.tpo.services.reclamo;

import java.util.List;

import uade.tpo.models.entity.Reclamo;

public interface IReclamoService {
    public List<Reclamo> findAll();

    public Reclamo findById(int id);

    public void save(Reclamo reclamo);

    public void update(int id, Reclamo reclamo);

    public void deleteById(int id);

    public List<Reclamo> getReclamosByEdificioId(int edificioId);
}


