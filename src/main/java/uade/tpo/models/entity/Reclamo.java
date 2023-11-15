package uade.tpo.models.entity;

import jakarta.persistence.*;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.ObjetoReclamo;
import uade.tpo.models.types.TipoReclamo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reclamos")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private ObjetoReclamo objetoReclamo;
    private TipoReclamo tipoReclamo;
    private String descripcion;
    private Date creado;
    private Date actualizado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private Unidad unidad;

    @ManyToOne
    @JoinColumn
    private AreaComun areaComun;
    
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;

    private EstadoReclamo estadoReclamo;

    @OneToMany(mappedBy = "reclamo", cascade = CascadeType.ALL)
    private List<Medida> medidas;

    public Reclamo(TipoReclamo tipoReclamo, String descripcion, Usuario usuario, ObjetoReclamo objetoReclamo ,Unidad unidad,AreaComun areaComun ,Edificio edificio) {
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.creado = new Date();
        this.actualizado = new Date();
        this.usuario = usuario;
        this.unidad = unidad;
        this.edificio = edificio;
        this.objetoReclamo = objetoReclamo;
        this.areaComun = areaComun;
        this.imagenes = new ArrayList<>();
        this.estadoReclamo = EstadoReclamo.NUEVO;
        this.medidas = new ArrayList<>();
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

	public ObjetoReclamo getObjetoReclamo() {
		return objetoReclamo;
	}

	public void setObjetoReclamo(ObjetoReclamo objetoReclamo) {
		this.objetoReclamo = objetoReclamo;
	}

	public AreaComun getAreaComun() {
		return areaComun;
	}


    @Override
    public String toString() {
        return "Reclamo{" +
                "id=" + id +
                ", objetoReclamo=" + objetoReclamo +
                ", tipoReclamo=" + tipoReclamo +
                ", descripcion='" + descripcion + '\'' +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", usuario=" + usuario +
                ", unidad=" + unidad +
                ", areaComun=" + areaComun +
                ", edificio=" + edificio +
                ", imagenes=" + imagenes +
                ", estadoReclamo=" + estadoReclamo +
                ", medidas=" + medidas +
                '}';
    }
}