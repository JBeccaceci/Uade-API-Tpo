package uade.tpo.models;

import java.util.*;

/**
 * 
 */
public class Reclamo {

    /**
     * Default constructor
     */
    public Reclamo() {
    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private TipoReclamo tipoReclamo;

    /**
     * 
     */
    private IUtilizable objReclamo;

    /**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private Date creado;

    /**
     * 
     */
    private Date actualizado;

    /**
     * 
     */
    private Usuario usuario;

    /**
     * 
     */
    private List<Imagen> imagenes;

    /**
     * 
     */
    private EstadoReclamo estadoReclamo;

    /**
     * 
     */
    private List<Medida> medidas;

    /**
     * @param imagen 
     * @return
     */
    public void subirImagen(Imagen imagen) {
        // TODO implement here
    }

    /**
     * @return
     */
    public String guardar() {
        // TODO implement here
        return "";
    }

}