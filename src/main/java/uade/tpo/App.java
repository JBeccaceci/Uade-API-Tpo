package uade.tpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api_clase_3_hibernate_hql.model.Persona;
import uade.tpo.models.Direccion;
import uade.tpo.models.Edificio;
import uade.tpo.models.Medida;
import uade.tpo.models.Reclamo;
import uade.tpo.models.Unidad;
import uade.tpo.models.Usuario;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
  //---------------------No se si el manejo de spring ya reemplaza el uso de estas clases en la configuracion, probe 
  //					Hacer los inserts asi.      
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(Edificio.class);
        conf.addAnnotatedClass(Usuario.class);
        conf.addAnnotatedClass(Medida.class);
        conf.addAnnotatedClass(Reclamo.class);
        conf.addAnnotatedClass(Unidad.class);
        conf.addAnnotatedClass(Direccion.class);
        
      //-----------------------------Abrimos la sesion----------------------
        
        SessionFactory sf = conf.buildSessionFactory();
        Session session = sf.openSession();
        
     //-------------------------Creates---------------------------   
       // createDireccionesRandom(session);
        
       
        createGeneralRandom(session);
        createUsuarioRandom(session);
       
        
        
        
        
        
        
    }

	private static void createUsuarioRandom(Session session) {
		
			Usuario persona1 = new Usuario( "Nazarena", "jgfbsjfb", "Hernandez", "33623432", "Nazample") {
			};
			Usuario persona2 = new Usuario( "Juan", "hhhhhhh", "Beca", "633636", "juansito") {
			};
			Usuario persona3 = new Usuario( "Thiago", "contraseñaxd", "Fernandez", "44444", "tkuser") {
			};

			Transaction tx = session.beginTransaction();
			session.save(persona1);
			session.save(persona2);
			session.save(persona3);
			tx.commit();
		
	}



	private static void createGeneralRandom(Session session) {
		 Edificio edificio1 = new Edificio("Torre Trump", new Direccion("Ohiggins", 671, "2981") , 4, true);
		 Edificio edificio2 = new Edificio("Uade Residencia", new Direccion("Lima", 999, "1291"), 10, true);
		 Edificio edificio3 = new Edificio("Hilary Tower",new Direccion("9 de julio", 888, "1511"), 3, false);
		 Edificio edificio4 = new Edificio("Alto DelPiero",new Direccion("Chile", 1010, "3422"), 5, true);
		 
		 Unidad unidad1 = new Unidad(null, null, edificio4, 10, 2);
		 Unidad unidad2 = new Unidad(null, null, edificio1, 20, 4);
		 Unidad unidad3 = new Unidad(null, null, edificio4, 5, 1);
		 Unidad unidad4 = new Unidad(null, null, edificio4, 8, 2);
		 
		 
		 Reclamo reclamo = new Reclamo(TipoReclamo.PROBLEMA, unidad1, "Las cañerias pierden agua", null, null, EstadoReclamo.ABIERTO, null);
		 
	      
		 Transaction tx = session.beginTransaction();
		 session.save(edificio1);
		 session.save(edificio2);
		 session.save(edificio3);
		 session.save(edificio4);
		 session.save(unidad1);
		 session.save(unidad2);
		 session.save(unidad3);
		 session.save(unidad4);
		 tx.commit();
		 
		 
		 
		 
	        
	       
	}
}
