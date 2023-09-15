package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.models.Reclamo;
import uade.tpo.models.Unidad;

import java.util.List;

@Repository
public class DaoUnidadImpl implements DAO<Unidad> {

    @PersistenceContext
    private EntityManager entityManager;

    public DaoUnidadImpl() { }

    @Override
    @Transactional(readOnly = true)
    public List<Unidad> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Unidad> getQuery = currentSession.createQuery("FROM unidades", Unidad.class);
        return getQuery.list();
    }

    @Override
    @Transactional
    public void save(Unidad persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional // TODO: Update va en el service
    public void update(Unidad persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from reclamos where id=:idUnidad");
        theQuery.setParameter("idUnidad", id);
        theQuery.executeUpdate();
    }
}
