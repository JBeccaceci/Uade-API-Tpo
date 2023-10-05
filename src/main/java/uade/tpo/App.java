package uade.tpo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.entity.Inquilino;
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

    @PostConstruct
    public void init() {
        Inquilino inquilino1 = new Inquilino("Juan", "123", "Perez","40247775", "jhon", "10/23/64", "100", 10);
        inquilinoService.save(inquilino1);
        
        
    }

}