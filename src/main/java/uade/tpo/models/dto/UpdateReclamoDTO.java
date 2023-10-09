package uade.tpo.models.dto;

import uade.tpo.models.types.EstadoReclamo;

import java.util.List;

public class UpdateReclamoDTO {
    private Integer reclamoId;
    private List<String> medidas;
    private EstadoReclamo estadoReclamo;

    public UpdateReclamoDTO(Integer reclamoId, List<String> medidas, EstadoReclamo estadoReclamo) {
        this.reclamoId = reclamoId;
        this.medidas = medidas;
        this.estadoReclamo = estadoReclamo;
    }

    public Integer getReclamoId() {
        return reclamoId;
    }

    public void setReclamoId(Integer reclamoId) {
        this.reclamoId = reclamoId;
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
                "reclamoId=" + reclamoId +
                ", medidas=" + medidas +
                ", estadoReclamo=" + estadoReclamo +
                '}';
    }
}
