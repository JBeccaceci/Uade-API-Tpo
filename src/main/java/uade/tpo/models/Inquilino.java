package uade.tpo.models;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 */
@Entity
@DiscriminatorValue("IN")
public class Inquilino extends Usuario {


   private String vencimiento;
   private String ingreso;
   private float montoAlquiler;
   
   public Inquilino(String nombre, String password, String apellido, String dni, String nombreUsuario,
			List<String> permisos, List<Reclamo> reclamos, String vencimiento, String ingreso, float montoAlquiler) {
		super(nombre, password, apellido, dni, nombreUsuario, permisos, reclamos);
		this.vencimiento = vencimiento;
		this.ingreso = ingreso;
		this.montoAlquiler = montoAlquiler;
	}
   

	
    public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getIngreso() {
		return ingreso;
	}

	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}

	public float getMontoAlquiler() {
		return montoAlquiler;
	}

	public void setMontoAlquiler(float montoAlquiler) {
		this.montoAlquiler = montoAlquiler;
	}



	@Override
	public String toString() {
		return "Inquilino [vencimiento=" + vencimiento + ", ingreso=" + ingreso + ", montoAlquiler=" + montoAlquiler
				+ "]";
	}

	



}