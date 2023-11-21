package uade.tpo.models.entity;

import jakarta.persistence.*;
import uade.tpo.models.types.TipoRole;
import uade.tpo.models.types.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    private String password;
    private String apellido;
    private String dni;
    private String username;

    @Enumerated(EnumType.STRING)
    private TipoRole role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reclamo> reclamos;

    @ManyToMany
    @JoinTable(name = "Usuario_Unidad", joinColumns = @JoinColumn(name = "usuario_FK_id"), inverseJoinColumns = @JoinColumn(name = "unidad_FK_id"))
    private List<Unidad> unidades = new ArrayList<>();

    
    public Usuario( String nombre, String apellido,String username, String password, String dni, TipoRole role) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.dni = dni;
        this.reclamos = new ArrayList<>();
        this.role = role;
        
    }

    public Usuario() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public TipoRole getRole() {
        return role;
    }

    public void setRole(TipoRole role) {
        this.role = role;
    }

    public void setUnidad(Unidad unidad) {
        this.unidades.add(unidad);
    }

    public void setReclamos(Reclamo reclamo) {
        this.reclamos.add(reclamo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}