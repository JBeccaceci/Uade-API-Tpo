package uade.tpo.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "unidad_table")
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //TODO: Preguntar al profe como se hace en estos casos..
    private Usuario propietario;

    //TODO: Preguntar al profe como se hace en estos casos..
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Usuario> habitantes;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    private int dpto;
    private int piso;

    public Unidad() {
    }

    public Unidad(Usuario propietario, List<Usuario> habitantes, Edificio edificio, int dpto, int piso) {
        this.propietario = propietario;
        this.habitantes = habitantes;
        this.edificio = edificio;
        this.dpto = dpto;
        this.piso = piso;
    }

    public int getId() {
        return id;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public List<Usuario> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<Usuario> habitantes) {
        this.habitantes = habitantes;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public int getDpto() {
        return dpto;
    }

    public void setDpto(int dpto) {
        this.dpto = dpto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    @Override
    public String toString() {
        return "Unidad{" +
                "id=" + id +
                ", propietario=" + propietario +
                ", habitantes=" + habitantes +
                ", edificio=" + edificio +
                ", dpto=" + dpto +
                ", piso=" + piso +
                '}';
    }
}