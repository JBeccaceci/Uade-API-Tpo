package uade.tpo.models;

/**
 * 
 */
public class Medida {

    private String id;
    private String descripcion;

    
    
    
    public Medida(String id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		
	}
	@Override
	public String toString() {
		return "Medida [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
    
    

    
}