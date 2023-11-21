package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uade.tpo.models.dto.EdificioDTO;
import uade.tpo.models.dto.UsuarioDto;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Usuario;
import uade.tpo.services.edificio.EdificioService;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.usuario.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;
	private static UsuarioController usuarioController;
	
	public static UsuarioController getInstance() {
		if(usuarioController == null) {
			return new UsuarioController();
		}
		return usuarioController;
	}

	@GetMapping("/usuarios")
    public List<UsuarioDto> findAll() {
        List<Usuario> listaUsuarios = usuarioService.findAll();
        List<UsuarioDto> listaUsuarioDTOs = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            UsuarioDto usuarioDto = convertToUsuarioDto(usuario);
            listaUsuarioDTOs.add(usuarioDto);
        }

        return listaUsuarioDTOs;
    }

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> get(@PathVariable int usuarioId) {
		Usuario usuario = usuarioService.findById(usuarioId);
		if (usuario == null) {
			String mensaje = "Usuario no encontrado con ID: " + usuarioId; 
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		
			UsuarioDto usuarioDTO = convertToUsuarioDto( usuario);
			return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
		
		
	}

	@PostMapping("/usuario")
	public ResponseEntity<UsuarioDto> add(@RequestBody UsuarioDto usuarioDto) {
		
			Usuario usuario= this.dtoToUsuario(usuarioDto);
			usuarioService.save(usuario);
			System.out.println(" ");
	        System.out.println("Se ha creado el usuario: "+ usuario.getUsername() + " Correctamente");
		
		
		return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
	}

	@PutMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> update(@PathVariable int usuarioId, @RequestBody UsuarioDto usuarioDto) {
		Usuario usuario = usuarioService.findById(usuarioId);
		if (usuario == null) {
			String mensaje = "Usuario no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		
			Usuario usuarioUpdated = dtoToUsuario(usuarioDto) ;
			usuarioService.update(usuarioId, usuarioUpdated);
			 System.out.println(" ");
		        System.out.println("Se ha modificado el usuario: "+ usuarioUpdated.getNombre() + " Correctamente");
		
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
        System.out.println(" ");
        System.out.println("Se ha eliminado el usuario: "+ usuario.getNombre() + " Correctamente");

		String mensaje = "Usuario eliminado [UsuarioID: " + usuarioId + "]";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	public UsuarioDto convertToUsuarioDto(Usuario usuario) {
		return new UsuarioDto(usuario.getNombre(), usuario.getApellido(), usuario.getDni(), usuario.getUsername(), usuario.getPassword(), usuario.getRole());
	}
	
	@GetMapping("/usuarios/usuariosEdificio/{edificioId}")
	public ResponseEntity<?> obtenerUsuariosPorEdificio(@PathVariable int edificioId) {
	    List<Usuario> listaUsuariosEdificios = usuarioService.findByEdificioId(edificioId);
	    
	    if (listaUsuariosEdificios.isEmpty()) {
	        String mensaje = "No pertenece al edificio con ID: " + edificioId;
	        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    }
	    List<UsuarioDto> listaUsuarioEdificioDTOs = new ArrayList<>();
        for (Usuario usuario : listaUsuariosEdificios) {
            UsuarioDto usuarioDto = convertToUsuarioDto(usuario);
            listaUsuarioEdificioDTOs.add(usuarioDto);
        }

	    return new ResponseEntity<>(listaUsuarioEdificioDTOs, HttpStatus.OK);
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
