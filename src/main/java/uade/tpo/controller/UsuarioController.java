package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uade.tpo.models.dto.UsuarioDto;
import uade.tpo.models.entity.Usuario;

import uade.tpo.services.usuario.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> get(@PathVariable int usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);
		if (usuario == null) {
			String mensaje = "Inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
			UsuarioDto usuarioDTO = convertToUsuarioDto( usuario);
			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
		
		
	}

	@PostMapping("/usuario")
	public ResponseEntity<UsuarioDto> add(@RequestBody UsuarioDto usuarioDto) {
		
			Usuario usuario= this.dtoToUsuario(usuarioDto);
			usuarioService.save(usuario);
		
		
		return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
	}

	@PutMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> update(@PathVariable int usuarioId, @RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = usuarioService.findById(usuarioId);
		if (usuario == null) {
			String mensaje = "inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		
			Usuario usuarioUpdated = dtoToUsuario(usuarioDto) ;
			usuarioService.update(usuarioId, usuarioUpdated);
		
		return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
	}

	@DeleteMapping("/usuario/{usuarioId}")
	public ResponseEntity<String> delete(@PathVariable int usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId, Usuario.class);

		if (usuario == null) {
			String mensaje = "Cliente no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		usuarioService.deleteById(usuarioId);

		String mensaje = "Usuario eliminado [UsuarioID: " + usuarioId + "]";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	private UsuarioDto convertToUsuarioDto(Usuario usuario) {
		return new UsuarioDto(usuario.getNombre(), usuario.getApellido(), usuario.getDni(), usuario.getUsername(), usuario.getPassword(), usuario.getRole());
	}





	private Usuario dtoToUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setApellido(usuarioDto.getApellido());
		usuario.setDni(usuarioDto.getDni());
		usuario.setUsername(usuarioDto.getUsername());
		usuario.setPassword(usuarioDto.getPassword());
		return usuario;
	}
}
