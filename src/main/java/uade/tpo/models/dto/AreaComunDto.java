package uade.tpo.models.dto;

import uade.tpo.models.entity.Edificio;
import uade.tpo.models.types.TipoAreaComun;

public class AreaComunDto {
	private int id;
    private TipoAreaComun tipoAreaComun;
    private int capacidad;
    
    
    
    public AreaComunDto(TipoAreaComun tipoAreaComun, int capacidad) {
		super();
		this.tipoAreaComun = tipoAreaComun;
		this.capacidad = capacidad;
	}
    

	@Override
	public String toString() {
		return "AreaComunDto [id=" + id + ", tipoAreaComun=" + tipoAreaComun + ", capacidad=" + capacidad
				+ ", edificio=" + edificio + "]";
	}


	public AreaComunDto() {
		super();
	}


	private Edificio edificio;

	public TipoAreaComun getTipoAreaComun() {
		return tipoAreaComun;
	}

	public void setTipoAreaComun(TipoAreaComun tipoAreaComun) {
		this.tipoAreaComun = tipoAreaComun;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public int getId() {
		return id;
	}
    
    

}
