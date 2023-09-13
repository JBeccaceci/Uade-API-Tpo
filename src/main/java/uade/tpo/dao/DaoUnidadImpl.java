package uade.tpo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import uade.tpo.models.Unidad;

public class DaoUnidadImpl implements DAO<Unidad> {

    private Session session;


    public DaoUnidadImpl(Session session) {
        super();
        this.session = session;
    }

    @Override
    public List<Unidad> getAll() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Unidad persistible) throws Exception {

        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();


    }

    @Override
    public void update(Unidad persistible) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {
        Unidad res = session.get(Unidad.class, id);

        if (res != null) {
            Transaction tx = session.beginTransaction();
            session.delete(res);
            session.getTransaction().commit();
        }

    }
}
