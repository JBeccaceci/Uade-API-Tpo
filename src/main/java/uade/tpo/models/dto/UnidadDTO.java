package uade.tpo.models.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Usuario;

public class UnidadDTO {
    private Integer usuario_id;
    private Integer edificio_id;
    private int dpto;
    private int piso;

	public UnidadDTO(Integer usuario_id, Integer edificio_id, int dpto, int piso) {
		this.usuario_id = usuario_id;
		this.edificio_id = edificio_id;
		this.dpto = dpto;
		this.piso = piso;
	}

	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}

	public Integer getEdificio_id() {
		return edificio_id;
	}

	public void setEdificio_id(Integer edificio_id) {
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
