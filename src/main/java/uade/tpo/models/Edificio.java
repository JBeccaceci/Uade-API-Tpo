package uade.tpo.models;
import java.util.*;

/**
 * 
 */
public class Edificio {

    private String nombre;
    private Direccion direccion;
    private int numeroPisos;
    private boolean tieneAscensor;
    private List<Unidad> unidades; 

   
    public Edificio(String nombre, Direccion direccion, int numeroPisos, boolean tieneAscensor) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroPisos = numeroPisos;
        this.tieneAscensor = tieneAscensor;
        this.unidades = new ArrayList<>();
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
		return "Edificio [nombre=" + nombre + ", direccion=" + direccion + ", numeroPisos=" + numeroPisos
				+ ", tieneAscensor=" + tieneAscensor + ", unidades=" + unidades + "]";
	}
}