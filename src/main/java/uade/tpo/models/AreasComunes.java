package uade.tpo.models;

/**
 * 
 */
public class AreasComunes {
    private String nombre;
    private int capacidad; 
    private String edificio;
    
    public AreasComunes(String nombre, int capacidad, String edificio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.edificio= edificio;
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

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	@Override
	public String toString() {
		return "AreasComunes [nombre=" + nombre + ", capacidad=" + capacidad + ", edificio=" + edificio + "]";
	}
}