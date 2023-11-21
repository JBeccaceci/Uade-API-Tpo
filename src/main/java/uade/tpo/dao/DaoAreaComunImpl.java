package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.DAO;
import uade.tpo.models.entity.AreaComun;

import java.util.List;

@Repository
public class DaoAreaComunImpl implements DAO<AreaComun> {

    @PersistenceContext
    private EntityManager entityManager;

    public DaoAreaComunImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<AreaComun> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<AreaComun> getQuery = currentSession.createQuery("FROM areasComunes", AreaComun.class);
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public AreaComun findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        AreaComun areaComun = currentSession.get(AreaComun.class, id);

        return areaComun;
    }

    @Override
    @Transactional
    public void save(AreaComun persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.persist(persistible);
    }

    @Override
    @Transactional
    public void update(AreaComun persistible) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.update(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from areasComunes where id=:idAreaComun");
        theQuery.setParameter("idAreaComun", id);
        theQuery.executeUpdate();
    }

    
   
    @Transactional(readOnly = true)
    public List<AreaComun> findByEdificioId(int edificioId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<AreaComun> getQuery = currentSession.createQuery("FROM areasComunes where edificio_id=:edificioId", AreaComun.class);
        getQuery.setParameter("edificioId", edificioId);
        return getQuery.list();
    }
    

}



    
    


