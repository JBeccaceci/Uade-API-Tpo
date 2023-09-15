package uade.tpo.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PR")
public class Propietario extends Usuario {

    public Propietario() {
    }

	public Propietario(String nombre, String password, String apellido, String dni, String nombreUsuario,
			List<String> permisos, List<Reclamo> reclamos) {
		super(nombre, password, apellido, dni, nombreUsuario, permisos, reclamos);
		// TODO Auto-generated constructor stub
	}
    
	

}