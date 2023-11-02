package uade.tpo.models.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import uade.tpo.models.types.TipoUsuario;

import java.util.Date;

@Entity
@DiscriminatorValue("IN")
public class Inquilino extends Usuario {
    private Date vencimiento;
    private float ingreso;
    private float montoAlquiler;

    public Inquilino() {
    }

    public Inquilino(String username, String password, String nombre,String apellido, String dni, Date vencimiento, float ingreso, float montoAlquiler) {
        super(username, password, nombre, apellido, dni);
        this.vencimiento = vencimiento;
        this.ingreso = ingreso;
        this.montoAlquiler = montoAlquiler;
    }

    @Override
    public TipoUsuario getType() {
        return TipoUsuario.INQUILINO;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public float getIngreso() {
        return ingreso;
    }

    public void setIngreso(float ingreso) {
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
        return "Inquilino{" +
                "id=" + this.getId() +
                ", nombre='" + this.getNombre() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", apellido='" + this.getApellido() + '\'' +
                ", dni='" + this.getDni() + '\'' +
                ", username='" + this.getUsername() + '\'' +
                ", permisos=" + this.getRole() +
                ", vencimiento=" + vencimiento +
                ", ingreso=" + ingreso +
                ", montoAlquiler=" + montoAlquiler +
                '}';
    }
}