package uade.tpo.models.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "imagenes")
public class Imagen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;

    private Date fecha;

	@ManyToOne
	@JoinColumn(name = "reclamo_id")
	private Reclamo reclamo;

	public Imagen() { }

	public Imagen(byte[] imagen) {
		this.imagen = imagen;
		this.fecha = new Date();
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Imagen{" +
				", fecha=" + fecha +
				'}';
	}
}