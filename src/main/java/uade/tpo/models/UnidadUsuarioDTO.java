package uade.tpo.models;

public class UnidadUsuarioDTO {
    private int unidadId;
    private String edificioName;

    public UnidadUsuarioDTO(int unidadId, String edificioName) {
        this.unidadId = unidadId;
        this.edificioName = edificioName;
    }

    public int getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(int unidadId) {
        this.unidadId = unidadId;
    }

    public String getEdificioName() {
        return edificioName;
    }

    public void setEdificioName(String edificioName) {
        this.edificioName = edificioName;
    }
}
