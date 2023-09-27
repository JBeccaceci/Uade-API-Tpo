package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uade.tpo.dao.definition.DAO;
import uade.tpo.dao.definition.IDaoUsuario;
import uade.tpo.models.Usuario;

import java.util.List;

public class DaoUsuarioImpl implements IDaoUsuario<Usuario> {
    @PersistenceContext
    private EntityManager entityManager;

    public DaoUsuarioImpl() {
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> getQuery = currentSession.createQuery("FROM usuarios", Usuario.class);
        getQuery.executeUpdate();
        return getQuery.list();
    }

    @Override
    public Usuario findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Usuario.class, id);
    }

    @Override
    public void save(Usuario persistible) throws Exception {

    }

    @Override
    public void update(Usuario persistible) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }
}
