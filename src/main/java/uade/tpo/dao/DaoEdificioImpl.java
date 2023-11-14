package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.DAO;
import uade.tpo.models.entity.Edificio;

import java.util.List;


@Repository
public class DaoEdificioImpl implements DAO<Edificio> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Edificio> getAll() {
        Session currentSession = this.entityManager.unwrap(Session.class);

        Query<Edificio> getQuery = currentSession.createQuery("FROM Edificio", Edificio.class);
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Edificio findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Edificio edificio = currentSession.get(Edificio.class, id);

        return edificio;
    }

    @Override
    @Transactional
    public void save(Edificio persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional // TODO: Update va en el service
    public void update(Edificio persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from edificios where id=:idEdificio");
        theQuery.setParameter("idEdificio", id);
        theQuery.executeUpdate();
    }

}



