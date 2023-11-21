package uade.tpo.services.unidad;

import java.util.List;

import uade.tpo.models.UnidadUsuarioDTO;
import uade.tpo.models.entity.Unidad;

public interface IUnidadService {
    public List<Unidad> findAll();

    public Unidad findById(int id);

    public void save(Unidad cliente);

    public void update(int id, Unidad cliente);

    public void deleteById(int id);

    public List<UnidadUsuarioDTO> getUnitsByOccupant(int usuarioId, int edificioId);
    
    public void eliminarHabitanteUnidad(int unidadId, int  usuarioId);
}