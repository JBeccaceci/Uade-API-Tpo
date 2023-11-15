package uade.tpo.models.dto;

public class UnidadEdificioDto {
    private String unidadId;
    private String edificioName;

    public UnidadEdificioDto(String unidadId, String edificioName) {
        this.unidadId = unidadId;
        this.edificioName = edificioName;
    }

    public String getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(String unidadId) {
        this.unidadId = unidadId;
    }

    public String getEdificioName() {
        return edificioName;
    }

    public void setEdificioName(String edificioName) {
        this.edificioName = edificioName;
    }

    @Override
    public String toString() {
        return "UnidadEdificioDto{" +
                "unidadId='" + unidadId + '\'' +
                ", edificioName='" + edificioName + '\'' +
                '}';
    }
}
