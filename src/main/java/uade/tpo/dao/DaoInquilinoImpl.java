package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uade.tpo.models.Inquilino;

import java.util.List;

public class DaoInquilinoImpl implements DAO<Inquilino> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Inquilino> getAll() throws Exception {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Inquilino> getQuery = currentSession.createQuery("FROM usuarios", Inquilino.class);
        return getQuery.list();
    }

    @Override
    public Inquilino findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Inquilino.class, id);
    }

    @Override
    public void save(Inquilino persistible) throws Exception {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    public void update(Inquilino persistible) throws Exception {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    public void delete(int id) throws Exception {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from usuarios where id=:idUsuario");
        theQuery.setParameter("idUsuario", id);
        theQuery.executeUpdate();
    }
}
