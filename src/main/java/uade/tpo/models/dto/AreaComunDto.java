package uade.tpo.models.dto;

import uade.tpo.models.entity.Edificio;
import uade.tpo.models.types.TipoAreaComun;

public class AreaComunDto {
    private TipoAreaComun tipoAreaComun;
    private int edificioId;
    private int capacidad;

	public AreaComunDto() {
	}

	public AreaComunDto(TipoAreaComun tipoAreaComun, int edificioId, int capacidad) {
		this.tipoAreaComun = tipoAreaComun;
		this.edificioId = edificioId;
		this.capacidad = capacidad;
	}

	public TipoAreaComun getTipoAreaComun() {
		return tipoAreaComun;
	}

	public void setTipoAreaComun(TipoAreaComun tipoAreaComun) {
		this.tipoAreaComun = tipoAreaComun;
	}

	public int getEdificioId() {
		return edificioId;
	}

	public void setEdificioId(int edificioId) {
		this.edificioId = edificioId;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "AreaComunDto{" +
				", tipoAreaComun=" + tipoAreaComun +
				", edificioId='" + edificioId + '\'' +
				", capacidad=" + capacidad +
				'}';
	}
}
