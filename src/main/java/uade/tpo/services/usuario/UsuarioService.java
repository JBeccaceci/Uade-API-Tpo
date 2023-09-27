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
    public Usuario findById(int id) {
        return daoUsuarioImpl.findById(id);
    }

    @Override
    public void save(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());

        usuario.setPassword(encodedPassword);

        this.daoUsuarioImpl.save(usuario);
    }

    @Override
    public void update(int id, Usuario cliente) {

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
