package uade.tpo.models.dto;

import uade.tpo.models.entity.Imagen;
import uade.tpo.models.entity.Medida;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;

import java.util.Date;
import java.util.List;

public class NewReclamoDTO {
    private String id;
    private TipoReclamo tipoReclamo;
    private String descripcion;
    private Date creado;
    private Date actualizado;
    private boolean areaComun;
    private List<Imagen> imagenes;
    private EstadoReclamo estadoReclamo;
    private List<Medida> medidas;

    public NewReclamoDTO() {
    }

    public NewReclamoDTO(String id, TipoReclamo tipoReclamo, String descripcion, boolean areaComun, List<Imagen> imagenes, EstadoReclamo estadoReclamo, List<Medida> medidas) {
        this.id = id;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.areaComun = areaComun;
        this.imagenes = imagenes;
        this.estadoReclamo = estadoReclamo;
        this.medidas = medidas;
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

    public boolean getAreaComun() {
        return areaComun;
    }

    public void setAreaComun(boolean areaComun) {
        this.areaComun = areaComun;
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
