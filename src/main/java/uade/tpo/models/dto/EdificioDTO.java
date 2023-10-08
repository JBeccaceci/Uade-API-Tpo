package uade.tpo.models.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import uade.tpo.models.entity.Direccion;
import uade.tpo.models.entity.Unidad;

public class EdificioDTO {
    private String nombre;
    private Direccion direccion;
    private int numeroPisos;
    private boolean tieneAscensor;

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<Unidad> unidades;

	public EdificioDTO(String nombre, Direccion direccion, int numeroPisos, boolean tieneAscensor) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroPisos = numeroPisos;
		this.tieneAscensor = tieneAscensor;
		this.unidades = new ArrayList<>();
	}

	public EdificioDTO() {
		super();
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

	@Override
	public String toString() {
		return "EdificioDTO [nombre=" + nombre + ", direccion=" + direccion + ", numeroPisos=" + numeroPisos
				+ ", tieneAscensor=" + tieneAscensor + ", unidades=" + unidades + "]";
	}

    

}
