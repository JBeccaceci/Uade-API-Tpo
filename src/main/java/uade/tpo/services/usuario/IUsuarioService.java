package uade.tpo.services.usuario;

import java.util.List;

import uade.tpo.models.entity.Usuario;

public interface IUsuarioService {
    public List<Usuario> findAll();

    public <T extends Usuario> T findById(int id, Class<T> userType);
    public Usuario findById(int id);
    public <T extends Usuario> T findByUsername(String username);

    public <T extends Usuario> void save(T cliente);

    public <T extends Usuario> void update(int id, T cliente);

    public void deleteById(int id);

    public Usuario findByUserAndPassword(String username, String password);
}
