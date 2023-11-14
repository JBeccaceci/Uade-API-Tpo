package uade.tpo.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import uade.tpo.models.types.TipoRole;
import uade.tpo.models.types.TipoUsuario;

@Entity
@DiscriminatorValue("PR")
public class Propietario extends Usuario {

    public Propietario() {
    }

	public Propietario(String username, String password, String nombre, String apellido, String dni, TipoRole role) {
		super(username, password, nombre, apellido, dni, role);
	}

	@Override
	public TipoUsuario getType() {
		return TipoUsuario.PROPIETARIO;
	}

	@Override
	public String toString() {
		return "Propietario {" +
				"id=" + this.getId() +
				", nombre='" + this.getNombre() + '\'' +
				", password='" + this.getPassword() + '\'' +
				", apellido='" + this.getApellido() + '\'' +
				", dni='" + this.getDni() + '\'' +
				", username='" + this.getUsername() + '\'' +
				", permisos=" + this.getRole() +
				'}';
	}
}