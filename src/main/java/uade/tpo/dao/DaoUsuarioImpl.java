package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.IDaoUsuario;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Usuario;

import java.util.List;

@Repository
public class DaoUsuarioImpl implements IDaoUsuario<Usuario> {
    @PersistenceContext
    private EntityManager entityManager;

    public DaoUsuarioImpl() {
    }
/* 
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> getQuery = currentSession.createQuery("FROM Usuario", Usuario.class);
        getQuery.executeUpdate();
        return getQuery.list();
    }*/
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getAll() {
        Session currentSession = this.entityManager.unwrap(Session.class);

        Query<Usuario> getQuery = currentSession.createQuery("FROM Usuario", Usuario.class);
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Usuario.class, id);
    }

    @Override
    @Transactional
    public void save(Usuario persistible) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(persistible);
    }

    @Override
    @Transactional
    public void update(Usuario usuario) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(usuario);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Usuario usuario = currentSession.get(Usuario.class, id);
        if(usuario != null) {
            currentSession.remove(usuario);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUserAndPassword(String username, String password) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Usuario> query = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
        query.setParameter("username", username);

        Usuario user = query.uniqueResult();
        if (user == null) {
            return null;
        }
        if (!this.checkPassword(password, user.getPassword())) {
            return null;
        }

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Usuario> query = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
        query.setParameter("username", username);

        return query.uniqueResult();
    }

    private boolean checkPassword(String password, String passwordDB) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, passwordDB);
    }
}
