package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.DAO;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Unidad;

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
    @Transactional(readOnly = true)
    public Unidad findById(int id) {
        return null;
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

    public List<Unidad> getUnitsByOccupant(int usuarioId) {
        String jpql = "SELECT u FROM Unidad u INNER JOIN u.habitantes h WHERE h.id = :usuarioId";
        return entityManager.createQuery(jpql, Unidad.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }
}
