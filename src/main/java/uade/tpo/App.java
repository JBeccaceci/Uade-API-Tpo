package uade.tpo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.entity.*;
import uade.tpo.models.types.TipoRole;
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


    @PostConstruct
    public void init() {

        // Crear edificios
        Edificio edificio1 = new Edificio();
        edificio1.setNombre("Laminar Plaza");
        edificio1.setDireccion(new Direccion("Av. Santa Fe", 900, "12345"));
        edificio1.setNumeroPisos(10);
        edificio1.setTieneAscensor(true);
        edificioService.save(edificio1);

        // Crear edificios
        Edificio edificio2 = new Edificio();
        edificio2.setNombre("Torre Madero");
        edificio2.setDireccion(new Direccion("Libertador", 3000, "12345"));
        edificio2.setNumeroPisos(10);
        edificio2.setTieneAscensor(true);
        edificioService.save(edificio2);

        // Crear edificios
        Edificio edificio3 = new Edificio();
        edificio3.setNombre("Alvear Palace");
        edificio3.setDireccion(new Direccion("Reconquista", 23, "12345"));
        edificio3.setNumeroPisos(10);
        edificio3.setTieneAscensor(true);
        edificioService.save(edificio3);


        /*
            Orden
            1. Edificios
            2. Propietarios
            3. Unidades (porque necesitan un propietario y edificio)
            4. Inquilinos (porque necesitan unidades)
         */
        Usuario propietario1 = new Usuario("pedro4", "123456", "Pedro", "Perez", "40247775", TipoRole.PROPIETARIO);
        Usuario propietario2 = new Usuario("Juan Maria", "123456", "Juan", "Beccaceci", "40247776", TipoRole.PROPIETARIO);
        //usuarioService.save(propietario1);

        // Crear unidades
        Unidad unidad1 = new Unidad();
        unidad1.setHabitante(propietario1);   // TODO: No puede existir una unidad sin antes un propietario
        unidad1.setEdificio(edificio1);         // TODO: No puede existir una unidad sin antes un edificio
        propietario1.setUnidad(unidad1);
        unidadService.save(unidad1);

        System.out.println(unidad1.toString());

        // Crear unidades
        Unidad unidad2 = new Unidad();
        unidad2.setHabitante(propietario2);   // TODO: No puede existir una unidad sin antes un propietario
        unidad2.setEdificio(edificio1);         // TODO: No puede existir una unidad sin antes un edificio
        propietario2.setUnidad(unidad2);
        unidadService.save(unidad2);
        System.out.println(unidad2.toString());

        Usuario inquilino1 = new Usuario("juan1", "123456", "Juan", "Perez", "40247775", TipoRole.INQUILINO);
        inquilino1.setUnidad(unidad1); // TODO: Un inquilino si o si debe tener una propiedad asignada
        usuarioService.save(inquilino1);
    }

}