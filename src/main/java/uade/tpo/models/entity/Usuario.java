package uade.tpo.models.entity;

import jakarta.persistence.*;
import uade.tpo.models.types.TipoRole;
import uade.tpo.models.types.TipoUsuario;

import java.util.ArrayList;
import java.util.List;

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
    private String username;
    private TipoRole role;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reclamo> reclamos;

    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private Unidad unidad;

    @OneToOne(mappedBy = "propietario", cascade = CascadeType.ALL)
    private Unidad propietario;

    public Usuario(String username, String password, String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.password = password;
        this.apellido = apellido;
        this.dni = dni;
        this.username = username;
        this.reclamos = new ArrayList<>();
    }

    public Usuario() {
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

    public abstract TipoUsuario getType();

    public TipoRole getRole() {
        return role;
    }

    public void setRole(TipoRole role) {
        this.role = role;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
        if (unidad != null && !unidad.getHabitantes().contains(this)) {
            unidad.setHabitante(this);
        }
    }

    public Unidad getUnidad() {
        return unidad;
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