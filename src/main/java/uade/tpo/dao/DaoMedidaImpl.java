package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.DAO;
import uade.tpo.models.entity.Medida;
import uade.tpo.models.entity.Reclamo;

import java.util.List;

@Repository
public class DaoMedidaImpl implements DAO<Medida> {

    @PersistenceContext
    private EntityManager entityManager;

    public DaoMedidaImpl() { }

    @Override
    @Transactional(readOnly = true)
    public List<Medida> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Medida> getQuery = currentSession.createQuery("FROM medidas", Medida.class);
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Medida findById(int id) {
        return null;
    }

    @Override
    @Transactional
    public void save(Medida persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional // TODO: Update va en el service
    public void update(Medida persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from medidas where id=:idMedida");
        theQuery.setParameter("idMedida", id);
        theQuery.executeUpdate();
    }

    public List<Medida> getMedidaReclamo(int reclamoId) {
        Session currentSession = entityManager.unwrap(Session.class);

        String jpql = "SELECT m FROM Medida m WHERE m.reclamo.id = :reclamoId";
        return currentSession.createQuery(jpql, Medida.class)
                .setParameter("reclamoId", reclamoId)
                .getResultList();
    }
}


