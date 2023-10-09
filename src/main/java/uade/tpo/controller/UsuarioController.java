package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uade.tpo.models.dto.UsuarioDTO;
import uade.tpo.models.entity.Inquilino;
import uade.tpo.models.entity.Propietario;
import uade.tpo.models.entity.Usuario;
import uade.tpo.models.types.TipoUsuario;
import uade.tpo.services.usuario.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/{usuarioId}")
	public ResponseEntity<?> getCliente(@PathVariable int usuarioId) {
		Inquilino inquilino = usuarioService.findById(usuarioId, Inquilino.class);

		if (inquilino == null) {
			String mensaje = "Inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		UsuarioDTO usuarioDTO = convertToDTO(inquilino);
		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		if (usuarioDTO.getTipoUsuario().equals(TipoUsuario.PROPIETARIO)) {
			Propietario propietario = this.dtoToPropietario(usuarioDTO);
			usuarioService.save(propietario);
		} else {
			Inquilino inquilino = this.dtoToInquilino(usuarioDTO);
			usuarioService.save(inquilino);
		}
		return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<?> updateCliente(@PathVariable int usuarioId, @RequestBody UsuarioDTO inquilinoDTO) {
		Inquilino inquilinoOld = usuarioService.findById(usuarioId, Inquilino.class);

		if (inquilinoOld == null) {
			String mensaje = "inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		Inquilino inquilinoToUpdate = convertToEntity(inquilinoDTO);
		usuarioService.update(usuarioId, inquilinoToUpdate);
		
        UsuarioDTO inquilinoUpdatedDTO = convertToDTO(inquilinoToUpdate);
		return new ResponseEntity<>(inquilinoUpdatedDTO, HttpStatus.OK);
	}

	@DeleteMapping("{inquilinoId}")
	public ResponseEntity<String> deleteCliente(@PathVariable int inquilinoId) {
		Inquilino inquilino = usuarioService.findById(inquilinoId, Inquilino.class);

		if (inquilino == null) {
			String mensaje = "Cliente no encontrado con ID: " + inquilinoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		usuarioService.deleteById(inquilinoId);

		String mensaje = "Inquilino eliminado [InquilinoID: " + inquilinoId + "]";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	/**
	 * Método auxiliar para convertir a ClienteDTO
	 * @param inquilino
	 * @return
	 */
	private UsuarioDTO convertToDTO(Inquilino inquilino) {
		UsuarioDTO inquilinoDTO = new UsuarioDTO(inquilino.getNombre(), inquilino.getApellido(), inquilino.getDni(), inquilino.getUsername(), inquilino.getPassword());
		return inquilinoDTO ;
	}
	//Tenemos que cambiar el tema de los dto para que sean iguales los parametros al constructor, para poder crear los 
	// inquilinos desde los dto del front. Es posible que tengamos que crear INQUILINODTO

	/**
	 * Método auxiliar para convertir a Cliente
	 * @param clienteDTO
	 * @return
	 */
	private Inquilino dtoToInquilino(UsuarioDTO clienteDTO) {
		Inquilino inquilino = new Inquilino();
		inquilino.setNombre(clienteDTO.getNombre());
		inquilino.setApellido(clienteDTO.getApellido());
		inquilino.setDni(clienteDTO.getDni());
		inquilino.setUsername(clienteDTO.getUsername());
		inquilino.setPassword(clienteDTO.getPassword());
		return inquilino;
	}
	private Propietario dtoToPropietario(UsuarioDTO clienteDTO) {
		Propietario propietario = new Propietario();
		propietario.setNombre(clienteDTO.getNombre());
		propietario.setApellido(clienteDTO.getApellido());
		propietario.setDni(clienteDTO.getDni());
		propietario.setUsername(clienteDTO.getUsername());
		propietario.setPassword(clienteDTO.getPassword());
		return propietario;
	}

}
