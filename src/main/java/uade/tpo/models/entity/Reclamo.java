package uade.tpo.models.entity;

import jakarta.persistence.*;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reclamos")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private TipoReclamo tipoReclamo;
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

    public Reclamo(TipoReclamo tipoReclamo, String descripcion,
                   Usuario usuario, List<Imagen> imagenes, EstadoReclamo estadoReclamo,
                   List<Medida> medidas) {
        this.tipoReclamo = tipoReclamo;
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