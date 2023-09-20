package uade.tpo.services.propietario;

import java.util.List;

import uade.tpo.models.Propietario;

public interface IPropietarioService {
    public List<Propietario> findAll();

    public Propietario findById(int id);

    public void save(Propietario cliente);

    public void update(int id, Propietario cliente);

    public void deleteById(int id);
}

