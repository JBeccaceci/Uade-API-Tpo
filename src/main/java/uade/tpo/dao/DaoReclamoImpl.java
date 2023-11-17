package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.DAO;
import uade.tpo.models.UnidadUsuarioDTO;
import uade.tpo.models.entity.AreaComun;
import uade.tpo.models.entity.Reclamo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoReclamoImpl implements DAO<Reclamo> {

    @PersistenceContext
    private EntityManager entityManager;

    public DaoReclamoImpl() { }

    @Override
    @Transactional(readOnly = true)
    public List<Reclamo> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Reclamo> getQuery = currentSession.createQuery("FROM reclamos", Reclamo.class);
        return getQuery.list();
    }

    @Override
    public Reclamo findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Reclamo.class, id);
    }

    @Override
    @Transactional
    public void save(Reclamo persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional // TODO: Update va en el service
    public void update(Reclamo persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from reclamos where id=:idReclamo");
        theQuery.setParameter("idReclamo", id);
        theQuery.executeUpdate();
    }

    public List<Reclamo> getReclamosByEdificioId(int edificioId) {
        Session currentSession = entityManager.unwrap(Session.class);

        String jpql = "SELECT r FROM Reclamo r WHERE r.edificio.id = :edificioId";
        List<Reclamo> userResults = currentSession.createQuery(jpql, Reclamo.class)
                .setParameter("edificioId", edificioId)
                .getResultList();

        return userResults;
    }
}
