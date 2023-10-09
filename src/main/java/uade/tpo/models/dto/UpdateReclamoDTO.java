package uade.tpo.models.dto;

import uade.tpo.models.types.EstadoReclamo;

import java.util.List;

public class UpdateReclamoDTO {
    private List<String> medidas;
    private EstadoReclamo estadoReclamo;

    public UpdateReclamoDTO(List<String> medidas, EstadoReclamo estadoReclamo) {
        this.medidas = medidas;
        this.estadoReclamo = estadoReclamo;
    }

    public List<String> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<String> medidas) {
        this.medidas = medidas;
    }

    public EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    @Override
    public String toString() {
        return "UpdateReclamoDTO{" +
                ", medidas=" + medidas +
                ", estadoReclamo=" + estadoReclamo +
                '}';
    }
}
