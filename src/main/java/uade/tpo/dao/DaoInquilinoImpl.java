package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.models.Inquilino;

import java.util.List;

@Repository
public class DaoInquilinoImpl implements DAO<Inquilino> {
    @PersistenceContext
    private EntityManager entityManager;

    public DaoInquilinoImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inquilino> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Inquilino> getQuery = currentSession.createQuery("FROM usuarios where tipo_usuario=:tipoUsuario", Inquilino.class);
        getQuery.setParameter("tipoUsuario", "IN");
        getQuery.executeUpdate();
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Inquilino findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Inquilino.class, id);
    }

    @Override
    @Transactional
    public void save(Inquilino persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional
    public void update(Inquilino persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from usuarios where id=:idUsuario");
        theQuery.setParameter("idUsuario", id);
        theQuery.executeUpdate();
    }
}
