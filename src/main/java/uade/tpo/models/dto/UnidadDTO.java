package uade.tpo.models.dto;

public class UnidadDTO {
    private int usuario_id;
    private int edificio_id;
    private int dpto;
    private int piso;

	public UnidadDTO(int usuario_id,int edificio_id,int dpto, int piso) {
		this.usuario_id= usuario_id;
		this.edificio_id = edificio_id;
		this.dpto = dpto;
		this.piso = piso;
	}
	
	public UnidadDTO() {	
	}
	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
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
				"usuario_id=" + usuario_id +
				", edificio_id=" + edificio_id +
				", dpto=" + dpto +
				", piso=" + piso +
				'}';
	}
}
