package uade.tpo.models;

import jakarta.persistence.*;

@Embeddable
public class Direccion {
	private String calle;
	private int altura;
	private String cp;

	public Direccion() {
	}

	public Direccion (String calle, int altura, String cp) {
		this.calle = calle;
		this.altura = altura;
		this.cp = cp;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", altura=" + altura + ", cp=" + cp + "]";
	}
}