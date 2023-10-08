package uade.tpo.models.dto;

import java.util.Date;
import java.util.List;

import uade.tpo.models.entity.Imagen;
import uade.tpo.models.entity.Usuario;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;

public class ReclamoDTO {
		private int id;
	    private TipoReclamo tipoReclamo;
	    private String descripcion;
	    private Date creado;
	    private Date actualizado;
	    private Usuario usuario;
	    private List<Imagen> imagenes;

	    private EstadoReclamo estadoReclamo;

		public ReclamoDTO(int id, TipoReclamo tipoReclamo, String descripcion, Date creado, Date actualizado,
				Usuario usuario, List<Imagen> imagenes, EstadoReclamo estadoReclamo) {
			super();
			this.id = id;
			this.tipoReclamo = tipoReclamo;
			this.descripcion = descripcion;
			this.creado = creado;
			this.actualizado = actualizado;
			this.usuario = usuario;
			this.imagenes = imagenes;
			this.estadoReclamo = estadoReclamo;
		}
	    
		public ReclamoDTO() {
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public TipoReclamo getTipoReclamo() {
			return tipoReclamo;
		}

		public void setTipoReclamo(TipoReclamo tipoReclamo) {
			this.tipoReclamo = tipoReclamo;
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

		@Override
		public String toString() {
			return "ReclamoDTO [id=" + id + ", tipoReclamo=" + tipoReclamo + ", descripcion=" + descripcion
					+ ", creado=" + creado + ", actualizado=" + actualizado + ", usuario=" + usuario + ", imagenes="
					+ imagenes + ", estadoReclamo=" + estadoReclamo + "]";
		}
	    
		


}
