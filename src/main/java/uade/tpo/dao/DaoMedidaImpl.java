package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.models.Imagen;
import uade.tpo.models.Medida;

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
}


