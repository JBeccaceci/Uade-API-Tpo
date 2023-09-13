package uade.tpo.models;

import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;
import uade.tpo.models.types.tipoDelReclamado;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "reclamo_table")
public class Reclamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private TipoReclamo tipoReclamo;
	private tipoDelReclamado tipoDelReclamado; 
	private String descripcion;
	private Date creado;
	private Date actualizado;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
	private List<Imagen> imagenes;

	private EstadoReclamo estadoReclamo;

	@OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
	private List<Medida> medidas;

	public Reclamo(TipoReclamo tipoReclamo, tipoDelReclamado objReclamo, String descripcion,
				   Usuario usuario, List<Imagen> imagenes, EstadoReclamo estadoReclamo,
				   List<Medida> medidas) {
		this.tipoReclamo = tipoReclamo;
		this.tipoDelReclamado = objReclamo;
		this.descripcion = descripcion;
		this.creado = new Date();
		this.actualizado = new Date();
		this.usuario = usuario;
		this.imagenes = imagenes;
		this.estadoReclamo = estadoReclamo;
		this.medidas = medidas;
	}

	public Reclamo() {
	}

	public void subirImagen(Imagen imagen) {
		// TODO implement here
	}

	public String guardar() {
		// TODO implement here
		return "";
	}

	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}



	public tipoDelReclamado getTipoDelReclamado() {
		return tipoDelReclamado;
	}

	public void setTipoDelReclamado(tipoDelReclamado tipoDelReclamado) {
		this.tipoDelReclamado = tipoDelReclamado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getCreado() {
		return creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}

	public Date getActualizado() {
		return actualizado;
	}

	public void setActualizado(Date actualizado) {
		this.actualizado = actualizado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public EstadoReclamo getEstadoReclamo() {
		return estadoReclamo;
	}

	public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
		this.estadoReclamo = estadoReclamo;
	}

	public List<Medida> getMedidas() {
		return medidas;
	}

	public void setMedidas(List<Medida> medidas) {
		this.medidas = medidas;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Reclamo{" +
				"id=" + id +
				", tipoReclamo=" + tipoReclamo +
				", objReclamo=" + tipoDelReclamado +
				", descripcion='" + descripcion + '\'' +
				", creado=" + creado +
				", actualizado=" + actualizado +
				", usuario=" + usuario +
				", imagenes=" + imagenes +
				", estadoReclamo=" + estadoReclamo +
				", medidas=" + medidas +
				'}';
	}
}