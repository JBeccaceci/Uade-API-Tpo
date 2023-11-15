package uade.tpo.models.dto;

import java.util.List;

public class UnidadDTO {
	private List<String> habitantes;
    private String edificio_id;
    private String dpto;
    private String piso;

	public UnidadDTO(List<String> habitantes, String edificio_id, String dpto, String piso) {
		this.habitantes = habitantes;
		this.edificio_id = edificio_id;
		this.dpto = dpto;
		this.piso = piso;
	}
	
	public UnidadDTO() {	
	}

	public List<String> getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(List<String> habitantes) {
		this.habitantes = habitantes;
	}

	public String getEdificio_id() {
		return edificio_id;
	}

	public void setEdificio_id(String edificio_id) {
		this.edificio_id = edificio_id;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
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
