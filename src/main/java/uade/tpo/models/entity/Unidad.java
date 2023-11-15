package uade.tpo.models.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "unidades")
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "unidades", cascade = CascadeType.ALL)
    private List<Usuario> habitantes = new ArrayList<Usuario>();

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Reclamo> reclamos;

    private int dpto;
    private int piso;

    public Unidad() {
        this.habitantes = new ArrayList<>();
        this.reclamos = new ArrayList<>();
    }

    public Unidad(Edificio edificio, int dpto, int piso) {
        this.edificio = edificio;
        this.dpto = dpto;
        this.piso = piso;
        this.habitantes = new ArrayList<>();
        this.reclamos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Usuario> getHabitantes() {
        return habitantes;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public int getDpto() {
        return dpto;
    }

    public void setDpto(int dpto) {
        this.dpto = dpto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

	public List<Reclamo> getReclamos() {
		return reclamos;
	}

    public void setHabitantes(List<Usuario> habitantes) {
        this.habitantes = habitantes;
    }

    public void setHabitante(Usuario usuario) {
        if (habitantes == null) {
            habitantes = new ArrayList<>();
        }
        habitantes.add(usuario);
    }

    @Override
    public String toString() {
        return "Unidad{" +
                "id=" + id +
                ", habitantes=" + habitantes +
                ", edificio=" + edificio +
                ", dpto=" + dpto +
                ", piso=" + piso +
                '}';
    }
}