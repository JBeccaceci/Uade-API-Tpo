package uade.tpo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.entity.Inquilino;
import uade.tpo.models.entity.Propietario;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.usuario.IUsuarioService;

import java.util.Date;

@SpringBootApplication
public class App {

    @Autowired
    private IEdificioService edificioService;

    @Autowired
    private IUsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

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


        Propietario prop1 = usuarioService.findByUsername("pedro4", Propietario.class);
        System.out.println(prop1.toString());

        Inquilino inq1 = usuarioService.findByUsername("juan1", Inquilino.class);
        System.out.println(inq1.toString());
    }

}