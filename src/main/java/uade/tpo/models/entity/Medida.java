package uade.tpo.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "medidas")
public class Medida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre", nullable = false, length = 100)
    private String descripcion;

	@ManyToOne
	@JoinColumn(name="reclamo_id")
	@JsonIgnore
	private Reclamo reclamo;

	public Medida() {
	}

	public Medida(String descripcion) {
		this.descripcion = descripcion;
	}

	public Medida(String descripcion, Reclamo reclamo) {
		this.descripcion = descripcion;
		this.reclamo = reclamo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public int getId() {
		return id;
	}

	public Reclamo getReclamo() {
		return reclamo;
	}

	@Override
	public String toString() {
		return "Medida [id=" + id + ", descripcion=" + descripcion + "]";
	}
}