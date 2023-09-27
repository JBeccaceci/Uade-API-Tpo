package uade.tpo.services.inquilino;

import uade.tpo.models.entity.Inquilino;

import java.util.List;

public interface IInquilinoService {
    public List<Inquilino> findAll();

    public Inquilino findById(int id);

    public void save(Inquilino inquilino);

    public void update(int id, Inquilino inquilino);

    public void deleteById(int id);
}
