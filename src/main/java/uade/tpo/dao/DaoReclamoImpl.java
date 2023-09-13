package uade.tpo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import uade.tpo.models.Reclamo;

import java.util.List;

public class DaoReclamoImpl implements DAO<Reclamo> {
    private Session session;

    public DaoReclamoImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Reclamo> getAll() throws Exception {
        Query<Reclamo> getQuery = session.createQuery("FROM reclamo_table", Reclamo.class);
        List<Reclamo> reclamos = getQuery.list();
        return reclamos;
    }

    @Override
    public void save(Reclamo persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void update(Reclamo persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void delete(int id) throws Exception {
        Reclamo res = session.get(Reclamo.class, id);

        if (res != null) {
            Transaction tx = session.beginTransaction();
            session.delete(res);
            session.getTransaction().commit();
        }
    }
}
