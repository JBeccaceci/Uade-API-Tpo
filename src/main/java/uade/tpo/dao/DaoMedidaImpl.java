package uade.tpo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import uade.tpo.models.Medida;

import java.util.List;

public class DaoMedidaImpl implements DAO<Medida> {
    private Session session;

    public DaoMedidaImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Medida> getAll() throws Exception {
        Query<Medida> getQuery = session.createQuery("FROM medida_table", Medida.class);
        List<Medida> medidas = getQuery.list();
        return medidas;
    }

    @Override
    public void save(Medida persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void update(Medida persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void delete(int id) throws Exception {
        Medida res = session.get(Medida.class, id);

        if (res != null) {
            session.beginTransaction();
            session.delete(res);
            session.getTransaction().commit();
        }
    }
}


