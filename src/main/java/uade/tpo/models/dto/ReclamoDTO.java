package uade.tpo.models.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uade.tpo.models.entity.Imagen;
import uade.tpo.models.entity.Usuario;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;

public class ReclamoDTO {
    private TipoReclamo tipoReclamo;
    private String descripcion;
    private Date creado;
    private Date actualizado;
    private Integer usuario_id;
    private Integer unidad_id;
    private Integer edificio_id;
    private Integer areaComun_id;
    private String objetoReclamo;
    private List<Imagen> imagen;

    private EstadoReclamo estadoReclamo;

    public ReclamoDTO(TipoReclamo tipoReclamo, String descripcion, Integer usuario_id, String objetoReclamo, Integer unidad_id, Integer edificio_id, Integer areaComun_id, EstadoReclamo estadoReclamo, List<Imagen> imagen) {
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.creado = new Date();
        this.actualizado = new Date();
        this.usuario_id = usuario_id;
        this.unidad_id = unidad_id;
        this.edificio_id = edificio_id;
        this.imagen = imagen;
        this.estadoReclamo = estadoReclamo;
        this.areaComun_id = areaComun_id;
        this.objetoReclamo = objetoReclamo;
    }

    public ReclamoDTO() {
        this.imagen = new ArrayList<>();
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(Integer unidad_id) {
        this.unidad_id = unidad_id;
    }

    public Integer getEdificio_id() {
        return edificio_id;
    }

    public void setEdificio_id(Integer edificio_id) {
        this.edificio_id = edificio_id;
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

    public int getUsuarioId() {
        return usuario_id;
    }

    public void setUsuarioId(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public List<Imagen> getImagenes() {
        return imagen;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagen = imagenes;
    }

    public EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public Integer getAreaComun_id() {
        return areaComun_id;
    }

    public String getObjetoReclamo() {
        return objetoReclamo;
    }

    @Override
    public String toString() {
        return "ReclamoDTO [tipoReclamo=" + tipoReclamo + ", descripcion=" + descripcion + ", creado=" + creado
                + ", actualizado=" + actualizado + ", usuario_id=" + usuario_id + ", unidad_id=" + unidad_id
                + ", edificio_id=" + edificio_id + ", areaComun_id=" + areaComun_id + ", objetoReclamo=" + objetoReclamo
                + ", imagenes=" + imagen + ", estadoReclamo=" + estadoReclamo + "]";
    }


}
