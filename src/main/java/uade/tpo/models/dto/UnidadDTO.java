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
    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Usuario> habitantes;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    private int dpto;
    private int piso;
    
    public UnidadDTO(int dpto, int piso, Edificio edificio) {
		super();
		this.edificio = edificio;
		this.dpto = dpto;
		this.piso = piso;
		this.habitantes = new ArrayList<>();
		}
    
    public UnidadDTO() {
		super();
	}

	public List<Usuario> getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(List<Usuario> habitantes) {
		this.habitantes = habitantes;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
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
}
