package uade.tpo.models;

import java.util.*;
import jakarta.persistence.*;
import uade.tpo.models.types.TipoUsuario;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Table(name = "usuarios")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    private String password;
    private String apellido;
    private String dni;
    private String nombreUsuario;
    private List<String> permisos;
    private TipoUsuario tipo_usuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reclamo> reclamos;

    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private Unidad unidad;

    public Usuario(String nombre, String password, String apellido, String dni, String nombreUsuario) {
        this.nombre = nombre;
        this.password = password;
        this.apellido = apellido;
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.permisos = new ArrayList<>();
        this.reclamos = new ArrayList<>();
    }

    public Usuario() {
    }

    public int getId() {
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<String> permisos) {
        this.permisos = permisos;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", permisos=" + permisos +
                '}';
    }
}