package uade.tpo.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uade.tpo.dao.DaoUsuarioImpl;
import uade.tpo.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private DaoUsuarioImpl daoUsuarioImpl;

    public UsuarioService() {

    }

    public List<Usuario> findAll() {
        return daoUsuarioImpl.getAll();
    }

    @Override
    public <T extends Usuario> T findById(int id, Class<T> userType) {
        Usuario usuario = this.daoUsuarioImpl.findById(id);

        if (usuario != null && userType.isInstance(usuario)) {
            return userType.cast(usuario);
        }

        return null;
    }

    @Override
    public Usuario findById(int id) {
        return this.daoUsuarioImpl.findById(id);
    }

    @Override
    public <T extends Usuario> T findByUsername(String username, Class<T> userType) {
        Usuario usuario = this.daoUsuarioImpl.findByUsername(username);

        if (usuario != null && userType.isInstance(usuario)) {
            return userType.cast(usuario);
        }

        return null;
    }

    @Override
    public <T extends Usuario> void save(T usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());

        usuario.setPassword(encodedPassword);

        this.daoUsuarioImpl.save(usuario);
    }

    @Override
    public <T extends Usuario> void update(int id, T usuario) {
        daoUsuarioImpl.update(usuario);
    }

    @Override
    public void deleteById(int id) {
        daoUsuarioImpl.delete(id);
    }

    @Override
    public Usuario findByUserAndPassword(String username, String password) {
        return this.daoUsuarioImpl.findByUserAndPassword(username, password);
    }
}
