package uade.tpo.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PR")
public class Propietario extends Usuario {

    public Propietario() {
    }

	public Propietario(String nombre, String password, String apellido, String dni, String username) {
		super(nombre, password, apellido, dni, username);
	}

}