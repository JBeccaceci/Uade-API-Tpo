package uade.tpo.services.unidad;

import java.util.List;

import uade.tpo.models.Unidad;

public interface IUnidadService {
	  public List<Unidad> findAll();

	    public Unidad findById(int id);

	    public void save(Unidad cliente);

	    public void update(int id, Unidad cliente);

	    public void deleteById(int id);
	}