package uade.tpo.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import uade.tpo.models.types.TipoAreaComun;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "areasComunes")
public class AreaComun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private TipoAreaComun tipoAreaComun;
    private int capacidad;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    @JsonIgnore
    private Edificio edificio;

    public AreaComun(TipoAreaComun tipoAreaComun, int capacidad, Edificio edificio) {
        this.tipoAreaComun = tipoAreaComun;
        this.capacidad = capacidad;
        this.edificio = edificio;
    }

   
  

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

    @Override
    public String toString() {
        return "AreaComun{" +
                "id=" + id +
                ", tipo='" + tipoAreaComun + '\'' +
                ", capacidad=" + capacidad +
                ", edificio=" + edificio +
                '}';
    }
}