package uade.tpo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.entity.*;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.unidad.IUnidadService;
import uade.tpo.services.usuario.IUsuarioService;

import java.util.Date;

@SpringBootApplication
public class App {

    @Autowired
    private IEdificioService edificioService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IUnidadService unidadService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /*
    @PostConstruct
    public void init() {
        Inquilino inquilino1 = new Inquilino("juan1", "123456", "Juan", "Perez", "40247775", new Date(), 5000, 100);
        usuarioService.save(inquilino1);
        Inquilino inquilino2 = new Inquilino("pedro1", "123456", "Pedro", "Perez", "40247775", new Date(), 2000, 300);
        usuarioService.save(inquilino2);

        Propietario propietario1 = new Propietario("pedro4", "123456", "Pedro", "Perez", "40247775");
        usuarioService.save(propietario1);
        Propietario propietario2 = new Propietario("juan5", "123456", "Juan", "Perez", "40247775");
        usuarioService.save(propietario2);

        // Crear direcciones
        Direccion direccion1 = new Direccion("Calle 123", 123, "12345");
        Direccion direccion2 = new Direccion("Avenida XYZ", 123, "67890");


        // Crear edificios
        Edificio edificio1 = new Edificio();
        edificio1.setNombre("Edificio 1");
        edificio1.setDireccion(direccion1);
        edificio1.setNumeroPisos(10);
        edificio1.setTieneAscensor(true);
        edificioService.save(edificio1);

        // Crear unidades
        Unidad unidad1 = new Unidad();
        unidad1.setPropietario(propietario1);
        unidad1.setHabitantes(Collections.singletonList(inquilino1));
        unidad1.setEdificio(edificio1);
        unidadService.save(unidad1);

        Edificio edificio2 = new Edificio();
        edificio2.setNombre("Edificio 2");
        edificio2.setDireccion(direccion2);
        edificio2.setNumeroPisos(5);
        edificio2.setTieneAscensor(false);
        edificioService.save(edificio2);
    }
     */

    @PostConstruct
    public void init() {
        // Crear direcciones
        Direccion direccion1 = new Direccion("Calle 123", 123, "12345");

        // Crear edificios
        Edificio edificio1 = new Edificio();
        edificio1.setNombre("Edificio 1");
        edificio1.setDireccion(direccion1);
        edificio1.setNumeroPisos(10);
        edificio1.setTieneAscensor(true);
        edificioService.save(edificio1);


        /*
            Orden
            1. Edificios
            2. Propietarios
            3. Unidades (porque necesitan un propietario y edificio)
            4. Inquilinos (porque necesitan unidades)
         */
        Propietario propietario1 = new Propietario("pedro4", "123456", "Pedro", "Perez", "40247775");
        //usuarioService.save(propietario1);

        // Crear unidades
        Unidad unidad1 = new Unidad();
        unidad1.setPropietario(propietario1);   // TODO: No puede existir una unidad sin antes un propietario
        unidad1.setEdificio(edificio1);         // TODO: No puede existir una unidad sin antes un edificio
        unidad1.getHabitantes().add(propietario1);
        propietario1.setUnidad(unidad1);
        unidadService.save(unidad1);


        Inquilino inquilino1 = new Inquilino("juan1", "123456", "Juan", "Perez", "40247775", new Date(), 5000, 100);
        inquilino1.setUnidad(unidad1); // TODO: Un inquilino si o si debe tener una propiedad asignada
        usuarioService.save(inquilino1);

        System.out.println("Edificio id" + inquilino1.getUnidad().getEdificio().getId());
    }

}