package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uade.tpo.dao.definition.DAO;
import uade.tpo.models.Propietario;

@Repository
public class DaoPropietarioImpl implements DAO<Propietario> {
    @PersistenceContext
    private EntityManager entityManager;

    public DaoPropietarioImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Propietario> getQuery = currentSession.createQuery("FROM usuarios where tipo_usuario=:tipoUsuario", Propietario.class);
        getQuery.setParameter("tipoUsuario", "IN");
        getQuery.executeUpdate();
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Propietario findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Propietario.class, id);
    }

    @Override
    @Transactional
    public void save(Propietario persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional
    public void update(Propietario persistible) {
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
