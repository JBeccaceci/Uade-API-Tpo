package uade.tpo.models.dto;

public class NewMedidaDto {
    private String id;
    private String descripcion;
    private String reclamoId;

    public NewMedidaDto() {
    }

    public NewMedidaDto(String id, String descripcion, String reclamoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.reclamoId = reclamoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReclamoId() {
        return reclamoId;
    }

    public void setReclamoId(String reclamoId) {
        this.reclamoId = reclamoId;
    }

    @Override
    public String toString() {
        return "NewMedidaDto{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", reclamoId='" + reclamoId + '\'' +
                '}';
    }
}
