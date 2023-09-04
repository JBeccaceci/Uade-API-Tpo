package uade.tpo.models;

import java.util.*;

/**
 * 
 */
public class Reclamo {
	private String id;

	private TipoReclamo tipoReclamo;

	private IUtilizable objReclamo;
	private String descripcion;
	private Date creado;
	private Date actualizado;
	private Usuario usuario;
	private List<Imagen> imagenes;
	private EstadoReclamo estadoReclamo;
	private List<Medida> medidas;

	

	
	
	public Reclamo(String id, TipoReclamo tipoReclamo, IUtilizable objReclamo, String descripcion, Date creado,
			Date actualizado, Usuario usuario, List<Imagen> imagenes, EstadoReclamo estadoReclamo,
			List<Medida> medidas) {
		super();
		this.id = id;
		this.tipoReclamo = tipoReclamo;
		this.objReclamo = objReclamo;
		this.descripcion = descripcion;
		this.creado = creado;
		this.actualizado = actualizado;
		this.usuario = usuario;
		this.imagenes = imagenes;
		this.estadoReclamo = estadoReclamo;
		this.medidas = medidas;
	}

	public void subirImagen(Imagen imagen) {
		// TODO implement here
	}

	/**
	 * @return
	 */

	
//--------------------------Getters y setters--------------
	public String guardar() {
		// TODO implement here
		return "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public IUtilizable getObjReclamo() {
		return objReclamo;
	}

	public void setObjReclamo(IUtilizable objReclamo) {
		this.objReclamo = objReclamo;
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

}