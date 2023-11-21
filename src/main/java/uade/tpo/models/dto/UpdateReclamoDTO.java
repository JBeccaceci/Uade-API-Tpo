package uade.tpo.models.dto;

import uade.tpo.models.entity.*;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;

import java.util.Date;
import java.util.List;

public class UpdateReclamoDTO {
    private String id;
    private TipoReclamo tipoReclamo;
    private String descripcion;
    private Date creado;
    private Date actualizado;
    private boolean esAreaComun;
    private Usuario usuario;
    private Unidad unidad;
    private Edificio edificio;
    private List<Imagen> imagenes;
    private String estadoReclamo;
    private List<Medida> medidas;

    public UpdateReclamoDTO() {
    }

    public UpdateReclamoDTO(TipoReclamo tipoReclamo, String descripcion, Date creado, Date actualizado, boolean esAreaComun, Usuario usuario, Unidad unidad, Edificio edificio, List<Imagen> imagenes, String estadoReclamo, List<Medida> medidas) {
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.creado = creado;
        this.actualizado = actualizado;
        this.esAreaComun = esAreaComun;
        this.usuario = usuario;
        this.unidad = unidad;
        this.edificio = edificio;
        this.imagenes = imagenes;
        this.estadoReclamo = estadoReclamo;
        this.medidas = medidas;
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

    public boolean isEsAreaComun() {
        return esAreaComun;
    }

    public void setEsAreaComun(boolean esAreaComun) {
        this.esAreaComun = esAreaComun;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public String getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(String estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public List<Medida> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<Medida> medidas) {
        this.medidas = medidas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdateReclamoDTO{" +
                "tipoReclamo=" + tipoReclamo +
                ", descripcion='" + descripcion + '\'' +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", esAreaComun=" + esAreaComun +
                ", usuario=" + usuario +
                ", unidad=" + unidad +
                ", edificio=" + edificio +
                ", imagenes=" + imagenes +
                ", estadoReclamo=" + estadoReclamo +
                ", medidas=" + medidas +
                '}';
    }
}
