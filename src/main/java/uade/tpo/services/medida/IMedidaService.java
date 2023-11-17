package uade.tpo.services.medida;

import java.util.List;


import uade.tpo.models.entity.Medida;

public interface IMedidaService {
	
    public List<Medida> findAll();

    public Medida findById(int id);

    public void save(Medida cliente);

    public void update(int id, Medida cliente);

    public void deleteById(int id);

    public List<Medida> getMedidaReclamo(int reclamoId);
}


