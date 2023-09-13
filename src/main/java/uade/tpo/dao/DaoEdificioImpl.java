package uade.tpo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import uade.tpo.models.Edificio;

public class DaoEdificioImpl implements DAO<Edificio> {
    private Session session;

    public DaoEdificioImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Edificio> getAll() throws Exception {
        Query<Edificio> getQuery = session.createQuery("FROM edificio_table", Edificio.class);
        List<Edificio> edificios = getQuery.list();
        return edificios;
    }

    @Override
    public void save(Edificio persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void update(Edificio persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
        // TODO: Comprobar con el profesor si hay que pisar el objeto
    }

    @Override
    public void delete(int id) throws Exception {
        Edificio res = session.get(Edificio.class, id);

        if (res != null) {
            session.beginTransaction();
            session.delete(res);
            session.getTransaction().commit();
        }
    }
}



