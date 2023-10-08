package uade.tpo.models.dto;

public class UsuarioDTO {
	private String nombre;
	private String username;
    private String password;
    private String apellido;
    private String dni;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nombre,String apellido,String dni,String username, String password) {
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.dni = dni;
        this.username = username;
        this.password = password;
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

	@Override
	public String toString() {
		return "UsuarioDTO [nombre=" + nombre + ", username=" + username + ", password=" + password + ", apellido="
				+ apellido + ", dni=" + dni + "]";
	}

	
    
}
