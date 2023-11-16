package uade.tpo.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "areasComunes")
public class AreaComun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int capacidad;

    @OneToOne
    private Edificio edificio;

    public AreaComun(String nombre, int capacidad, Edificio edificio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.edificio = edificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AreaComun{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", edificio=" + edificio +
                '}';
    }
}