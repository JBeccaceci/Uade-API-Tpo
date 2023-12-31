package uade.tpo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uade.tpo.dao.definition.DAO;
import uade.tpo.models.UnidadUsuarioDTO;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Unidad;

import java.util.ArrayList;
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
        Query<Unidad> getQuery = currentSession.createQuery("FROM Unidad", Unidad.class);
        return getQuery.list();
    }

    @Override
    @Transactional(readOnly = true)
    public Unidad findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Unidad.class, id);
    }


    @Override
    @Transactional
    public void save(Unidad persistible) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(persistible);
    }

    @Override
    @Transactional
    public void update(Unidad persistible) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(persistible);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("DELETE FROM Unidad u WHERE u.id = :idUnidad");
        theQuery.setParameter("idUnidad", id);
        theQuery.executeUpdate();
    }

    public List<UnidadUsuarioDTO> getUnitsByOccupant(int usuarioId, int edificioId) {
        Session currentSession = entityManager.unwrap(Session.class);
        String hql = "SELECT u.id, u.edificio.nombre FROM Unidad u " +
                "JOIN u.habitantes h " +
                "WHERE h.id = :usuarioId " +
                "AND u.edificio.id = :edificioId";
        List<Object[]> userResults = currentSession.createQuery(hql, Object[].class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("edificioId", edificioId)
                .getResultList();

        List<UnidadUsuarioDTO> unidadUsuarioDTOS = new ArrayList<>();
        for (Object[] obj: userResults) {
            unidadUsuarioDTOS.add(new UnidadUsuarioDTO((int) obj[0], (String) obj[1]));
        }
        return unidadUsuarioDTOS;
    }
    
    
    @Transactional
    public void eliminarHabitanteUnidad(int unidadId, int  usuarioId ) {
    	 Session currentSession = entityManager.unwrap(Session.class);
    	 Query query = currentSession.createQuery("DELETE FROM usuario_unidad WHERE unidadId = :unidadId AND usuarioId = :usuarioId");
    	    query.setParameter("unidadId", unidadId);
    	    query.setParameter("usuarioId", usuarioId);
    	 
    	    query.executeUpdate();
         
    }
}
