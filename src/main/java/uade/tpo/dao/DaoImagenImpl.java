package uade.tpo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import uade.tpo.models.Imagen;

import java.util.List;

public class DaoImagenImpl implements DAO<Imagen> {
    private Session session;

    public DaoImagenImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Imagen> getAll() throws Exception {
        Query<Imagen> getQuery = session.createQuery("FROM imagen_table", Imagen.class);
        List<Imagen> imagenes = getQuery.list();
        return imagenes;
    }

    @Override
    public void save(Imagen persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void update(Imagen persistible) throws Exception {
        Transaction tx = session.beginTransaction();
        session.save(persistible);
        tx.commit();
    }

    @Override
    public void delete(int id) throws Exception {
        Imagen res = session.get(Imagen.class, id);

        if (res != null) {
            session.beginTransaction();
            session.delete(res);
            session.getTransaction().commit();
        }
    }
}



