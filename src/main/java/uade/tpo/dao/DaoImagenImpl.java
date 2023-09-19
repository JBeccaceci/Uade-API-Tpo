package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.models.Imagen;

import java.util.List;

@Repository
public class DaoImagenImpl implements DAO<Imagen> {

    @PersistenceContext
    private EntityManager entityManager;

    public DaoImagenImpl() { }

    @Override
    @Transactional(readOnly = true)
    public List<Imagen> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Imagen> getQuery = currentSession.createQuery("FROM imagenes", Imagen.class);
        return getQuery.list();
    }

    @Override
    public Imagen findById(int id) {
        return null;
    }

    @Override
    @Transactional
    public void save(Imagen persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional // TODO: Update va en el service
    public void update(Imagen persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from imagenes where id=:idImagen");
        theQuery.setParameter("idImagen", id);
        theQuery.executeUpdate();
    }
}



