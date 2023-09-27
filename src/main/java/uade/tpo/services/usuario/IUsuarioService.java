package uade.tpo.services.usuario;

import java.util.List;

import uade.tpo.models.entity.Usuario;

public interface IUsuarioService {
    public List<Usuario> findAll();

    public Usuario findById(int id);

    public void save(Usuario cliente);

    public void update(int id, Usuario cliente);

    public void deleteById(int id);

    public Usuario findByUserAndPassword(String username, String password);
}
