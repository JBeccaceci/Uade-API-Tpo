package uade.tpo.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uade.tpo.models.dto.TokenOutputDTO;
import uade.tpo.models.dto.PropietarioDTO;
import uade.tpo.models.entity.Usuario;
import uade.tpo.services.usuario.IUsuarioService;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final int EXPIRATION_TIME_IN_MIN = 1;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private SecretKey secretKey;

	@PostMapping("/login")
	public ResponseEntity<TokenOutputDTO> login(@RequestBody PropietarioDTO credentials) {
		Usuario user = usuarioService.findByUserAndPassword(credentials.getUsername(), credentials.getPassword());
		if (user != null) {
			String token = Jwts.builder()
					.setSubject(credentials.getUsername())
					.setIssuedAt(new Date())
					.claim("rol", user.getRole())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
					.signWith(secretKey, SignatureAlgorithm.HS256).compact();

			TokenOutputDTO output = new TokenOutputDTO(token);
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
