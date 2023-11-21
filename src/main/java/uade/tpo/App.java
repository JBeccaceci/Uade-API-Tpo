package uade.tpo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uade.tpo.models.entity.Usuario;
import uade.tpo.models.types.TipoRole;
import uade.tpo.services.usuario.IUsuarioService;

@SpringBootApplication
public class App {

    @Autowired
    private IUsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    public void init() {
    	Usuario propietario1 = new Usuario("Pedro", "Perez", "admin", "admin", "40247775", TipoRole.ADMIN);
        usuarioService.save(propietario1);	
    }
}