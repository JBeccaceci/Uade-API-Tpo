package uade.tpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.Direccion;
import uade.tpo.models.Edificio;
import uade.tpo.services.EdificioService;
import uade.tpo.services.IEdificioService;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        Edificio edificio1 = new Edificio("Las Lilas", new Direccion("Av Libertador", 200, "23"), 25, true);
        IEdificioService edificioService = new EdificioService();
        edificioService.save(edificio1);
    }
}
