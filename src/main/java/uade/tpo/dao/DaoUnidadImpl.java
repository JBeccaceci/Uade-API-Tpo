package dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import uade.tpo.models.Edificio;
import uade.tpo.models.Unidad;

public class DaoUnidadImpl implements DAO<Unidad> {

	private Session session;
	
	
	
	public DaoUnidadImpl(Session session) {
		super();
		this.session = session;
	}

	@Override
	public List<Unidad> getAll() throws Exception {
		Query<Unidad> getQuery = session.createQuery("FROM unidad_table", Unidad.class);
		List<Unidad> unidades = getQuery.list();
		return unidades;
		
	}

	@Override
	public void save(Unidad persistible) throws Exception {
		
			Transaction tx = session.beginTransaction();
			session.save(persistible);
			tx.commit();
			
		
		
	}

	@Override
	public void update(Unidad persistible) throws Exception {
		Transaction tx = session.beginTransaction();
		session.save(persistible);
		tx.commit();
		// TODO: Comprobar con el profesor si hay que pisar el objeto
	
	}

	@Override
	public void delete(int id) throws Exception {
		Unidad res = session.get(Unidad.class,  id);
		
		if(res != null) {
		Transaction tx = session.beginTransaction();
		session.delete(res);
		session.getTransaction().commit();
		}

	}
}
