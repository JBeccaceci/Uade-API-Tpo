package uade.tpo.models.dto;

public class TokenOutputDTO {
    private String token;

    public TokenOutputDTO() {
    }

    public TokenOutputDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenOutput{" +
                "token='" + token + '\'' +
                '}';
    }
}
