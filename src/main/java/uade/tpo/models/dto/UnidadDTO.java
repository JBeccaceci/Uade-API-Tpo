package uade.tpo.models.dto;

import uade.tpo.models.entity.Usuario;

import java.util.List;

public class UnidadDTO {
	private List<Usuario> habitantes;
    private int edificio_id;
    private int dpto;
    private int piso;

	public UnidadDTO(List<Usuario> habitantes, int edificio_id, int dpto, int piso) {
		this.habitantes = habitantes;
		this.edificio_id = edificio_id;
		this.dpto = dpto;
		this.piso = piso;
	}
	
	public UnidadDTO() {	
	}

	public List<Usuario> getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(List<Usuario> habitantes) {
		this.habitantes = habitantes;
	}

	public int getEdificio_id() {
		return edificio_id;
	}

	public void setEdificio_id(int edificio_id) {
		this.edificio_id = edificio_id;
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
		return "UnidadDTO{" +
				"habitantes=" + habitantes +
				", edificio_id=" + edificio_id +
				", dpto=" + dpto +
				", piso=" + piso +
				'}';
	}
}
