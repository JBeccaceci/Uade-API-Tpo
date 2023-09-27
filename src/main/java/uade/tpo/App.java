package uade.tpo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.Direccion;
import uade.tpo.models.Edificio;
import uade.tpo.models.Inquilino;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.inquilino.IInquilinoService;

@SpringBootApplication
public class App {

    @Autowired
    private IEdificioService edificioService;

    @Autowired
    private IInquilinoService inquilinoService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /*
    @PostConstruct
    public void init() {
        Edificio edificio1 = new Edificio("Las Lilas", new Direccion("Av Libertador", 200, "23"), 25, true);
        edificioService.save(edificio1);

        Edificio edificio2 = new Edificio("Las piedras", new Direccion("Moreno", 200, "23"), 25, true);
        edificioService.save(edificio2);

        Inquilino inquilino1 = new Inquilino("Juan", "123", "Perez","40247775", "jhon", "10/23/64", "100", 10);
        inquilinoService.save(inquilino1);
    }
     */
}