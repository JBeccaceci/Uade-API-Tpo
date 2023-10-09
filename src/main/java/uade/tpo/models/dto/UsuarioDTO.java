package uade.tpo.models.dto;

import uade.tpo.models.types.TipoUsuario;

public class UsuarioDTO {
	private String nombre;
	private String username;
    private String password;
    private String apellido;
    private String dni;
    private TipoUsuario tipoUsuario;

    public UsuarioDTO() {
    }

	public UsuarioDTO(String nombre, String username, String password, String apellido, String dni, TipoUsuario tipoUsuario) {
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.apellido = apellido;
		this.dni = dni;
		this.tipoUsuario = tipoUsuario;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [nombre=" + nombre + ", username=" + username + ", password=" + password + ", apellido="
				+ apellido + ", dni=" + dni + "]";
	}

	
    
}
