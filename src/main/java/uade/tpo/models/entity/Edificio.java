package uade.tpo.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "edificios")
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private Direccion direccion;
    private int numeroPisos;
    private boolean tieneAscensor;


    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Unidad> unidades = new ArrayList<>();

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<Reclamo> reclamos = new ArrayList<>();

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AreaComun> areasComunes = new ArrayList<>();


    public Edificio(String nombre, Direccion direccion, int numeroPisos, boolean tieneAscensor) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroPisos = numeroPisos;
        this.tieneAscensor = tieneAscensor;

    }

    public Edificio() {
        // TODO Auto-generated constructor stub
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }

    public boolean isTieneAscensor() {
        return tieneAscensor;
    }

    public void setTieneAscensor(boolean tieneAscensor) {
        this.tieneAscensor = tieneAscensor;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReclamos(Reclamo reclamo) {
        this.reclamos.add(reclamo);
    }

	public void setAreaComun(AreaComun areaComun) {
		this.areasComunes.add(areaComun);
	}

    public List<AreaComun> getAreasComunes() {
        return areasComunes;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", numeroPisos=" + numeroPisos +
                ", tieneAscensor=" + tieneAscensor +
                '}';
    }
}