package uade.tpo.models.dto;

public class NewReclamoInputDto {
    private String edificioId;
    private String tipoReclamo;
    private boolean areaComun;
    private String descripcion;
    private String unidadId;

    public NewReclamoInputDto() {
    }

    public NewReclamoInputDto(String edificioId, String tipoReclamo, boolean areaComun, String descripcion, String unidadId) {
        this.edificioId = edificioId;
        this.tipoReclamo = tipoReclamo;
        this.areaComun = areaComun;
        this.descripcion = descripcion;
        this.unidadId = unidadId;
    }

    public String getEdificioId() {
        return edificioId;
    }

    public void setEdificioId(String edificioId) {
        this.edificioId = edificioId;
    }

    public String getTipoReclamo() {
        return tipoReclamo;
    }

    public void setTipoReclamo(String tipoReclamo) {
        this.tipoReclamo = tipoReclamo;
    }

    public boolean isAreaComun() {
        return areaComun;
    }

    public void setAreaComun(boolean areaComun) {
        this.areaComun = areaComun;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(String unidadId) {
        this.unidadId = unidadId;
    }

    @Override
    public String toString() {
        return "NewReclamoInputDto{" +
                "edificioId='" + edificioId + '\'' +
                ", tipoReclamo='" + tipoReclamo + '\'' +
                ", areaComun=" + areaComun +
                ", descripcion='" + descripcion + '\'' +
                ", unidadId='" + unidadId + '\'' +
                '}';
    }
}
