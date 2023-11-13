package uade.tpo.models.dto;

import uade.tpo.models.entity.Direccion;

public class EdificioDTO {
	private Integer id;
    private String nombre;
    private Direccion direccion;
    private int numeroPisos;
    private boolean tieneAscensor;

	public EdificioDTO(Integer id, String nombre, Direccion direccion, int numeroPisos, boolean tieneAscensor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroPisos = numeroPisos;
		this.tieneAscensor = tieneAscensor;
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

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "EdificioDTO{" +
				"id='" + id + '\'' +
				", nombre='" + nombre + '\'' +
				", direccion=" + direccion +
				", numeroPisos=" + numeroPisos +
				", tieneAscensor=" + tieneAscensor +
				'}';
	}
}
