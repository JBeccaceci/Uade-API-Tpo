package uade.tpo.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IN")
public class Inquilino extends Usuario {
    private String vencimiento;
    private String ingreso;
    private float montoAlquiler;

    public Inquilino() {
    }

    public Inquilino(String nombre, String password, String apellido, String dni, String username, String vencimiento, String ingreso, float montoAlquiler) {
        super(nombre, password, apellido, dni, username);
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