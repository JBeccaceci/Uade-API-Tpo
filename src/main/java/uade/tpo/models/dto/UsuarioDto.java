package uade.tpo.models.dto;

import uade.tpo.models.types.TipoRole;
import uade.tpo.models.types.TipoUsuario;

import java.util.Date;


public class UsuarioDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String username;
    private String password;
    private TipoRole role;

    public UsuarioDto(Integer id, String nombre, String apellido,  String dni, String username, String password,  TipoRole role) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public UsuarioDto() {
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

    public TipoRole getRole() {
        return role;
    }

    public void setRole(TipoRole tipoUsuario) {
        this.role = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                " id=" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role + '\'' +
                '}';
    }
}
