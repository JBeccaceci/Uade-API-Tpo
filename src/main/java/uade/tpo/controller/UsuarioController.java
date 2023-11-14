package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uade.tpo.models.dto.InquilinoDTO;
import uade.tpo.models.dto.PropietarioDTO;
import uade.tpo.models.dto.UsuarioDto;
import uade.tpo.models.entity.Usuario;
import uade.tpo.models.types.TipoUsuario;
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
		if (usuario.getType().getName().equals(TipoUsuario.PROPIETARIO.getName())) {
			PropietarioDTO propietarioDTO = convertToPropietarioDto((Propietario) usuario);
			return new ResponseEntity<>(propietarioDTO, HttpStatus.OK);
		} else {
			InquilinoDTO inquilinoDTO = convertToInquilinoDto((Inquilino) usuario);
			return new ResponseEntity<>(inquilinoDTO, HttpStatus.OK);
		}
	}

	@PostMapping("/usuario")
	public ResponseEntity<UsuarioDto> add(@RequestBody UsuarioDto usuarioDto) {
		if (usuarioDto.getTipoUsuario().equals(TipoUsuario.PROPIETARIO)) {
			Propietario propietario = this.dtoToPropietario(usuarioDto);
			usuarioService.save(propietario);
		} else {
			Inquilino inquilino = this.dtoToInquilino(usuarioDto);
			usuarioService.save(inquilino);
		}
		return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
	}

	@PutMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> update(@PathVariable int usuarioId, @RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = usuarioService.findById(usuarioId);
		if (usuario == null) {
			String mensaje = "inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		if (usuario.getType().getName().equals(TipoUsuario.PROPIETARIO.getName())) {
			Propietario propietario = dtoToPropietario(usuarioDto);
			usuarioService.update(usuarioId, propietario);
		} else {
			Inquilino inquilino = dtoToInquilino(usuarioDto);
			usuarioService.update(usuarioId, inquilino);
		}
		return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
	}

	@DeleteMapping("/usuario/{usuarioId}")
	public ResponseEntity<String> delete(@PathVariable int inquilinoId) {
		Inquilino inquilino = usuarioService.findById(inquilinoId, Inquilino.class);

		if (inquilino == null) {
			String mensaje = "Cliente no encontrado con ID: " + inquilinoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		usuarioService.deleteById(inquilinoId);

		String mensaje = "Inquilino eliminado [InquilinoID: " + inquilinoId + "]";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	private PropietarioDTO convertToPropietarioDto(Propietario propietario) {
		return new PropietarioDTO(propietario.getNombre(), propietario.getApellido(), propietario.getDni(), propietario.getUsername(), propietario.getPassword());
	}

	private InquilinoDTO convertToInquilinoDto(Inquilino inquilino) {
		return new InquilinoDTO(inquilino.getUsername(), inquilino.getPassword(), inquilino.getNombre(), inquilino.getApellido(), inquilino.getDni(), inquilino.getVencimiento(), inquilino.getIngreso(), inquilino.getMontoAlquiler());
	}

	private Inquilino dtoToInquilino(UsuarioDto usuarioDto) {
		Inquilino inquilino = new Inquilino();
		inquilino.setNombre(usuarioDto.getNombre());
		inquilino.setApellido(usuarioDto.getApellido());
		inquilino.setDni(usuarioDto.getDni());
		inquilino.setUsername(usuarioDto.getUsername());
		inquilino.setPassword(usuarioDto.getPassword());
		inquilino.setIngreso(usuarioDto.getIngreso());
		inquilino.setVencimiento(usuarioDto.getVencimiento());
		inquilino.setMontoAlquiler(usuarioDto.getMontoAlquiler());
		return inquilino;
	}

	private Propietario dtoToPropietario(UsuarioDto usuarioDto) {
		Propietario propietario = new Propietario();
		propietario.setNombre(usuarioDto.getNombre());
		propietario.setApellido(usuarioDto.getApellido());
		propietario.setDni(usuarioDto.getDni());
		propietario.setUsername(usuarioDto.getUsername());
		propietario.setPassword(usuarioDto.getPassword());
		return propietario;
	}
}
