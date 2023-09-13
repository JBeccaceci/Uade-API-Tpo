package uade.tpo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import uade.tpo.models.*;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(Edificio.class);
        conf.addAnnotatedClass(Imagen.class);
        conf.addAnnotatedClass(Medida.class);
        conf.addAnnotatedClass(Reclamo.class);
        conf.addAnnotatedClass(Unidad.class);
        conf.addAnnotatedClass(Usuario.class);

        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();

        // Crea un usuario de ejemplo
        crearUsuario(session);

        session.close();
    }

    public static void crearUsuario(Session session) {
        /*
        Usuario usuario1 = new Usuario("Juan", "123", "Perez", "84754832", "juanperezkpo", new ArrayList<>(), new ArrayList<>());
        Transaction tx = session.beginTransaction();
        session.save(usuario1);
        tx.commit();
         */
    }
}
