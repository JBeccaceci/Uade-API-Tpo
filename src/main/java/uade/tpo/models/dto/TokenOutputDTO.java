package uade.tpo.models.dto;

import uade.tpo.models.types.TipoRole;

public class TokenOutputDTO {
    private String token;
    private Integer id;
    private String role;

    public TokenOutputDTO() {
    }

    public TokenOutputDTO(String token, Integer id, TipoRole tipoRole ) {
        this.token = token;
        this.id = id;
        this.role = tipoRole.toString();
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



	@Override
	public String toString() {
		return "TokenOutputDTO [token=" + token + ", id=" + id + ", role=" + role + "]";
	}

	public Integer getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
    
}
