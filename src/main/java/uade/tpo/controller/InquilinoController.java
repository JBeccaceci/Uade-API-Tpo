package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uade.tpo.models.dto.UsuarioDTO;
import uade.tpo.models.entity.Inquilino;
import uade.tpo.services.inquilino.InquilinoService;
import uade.tpo.services.inquilino.IInquilinoService;
import uade.tpo.dao.DaoInquilinoImpl;

@RestController
@RequestMapping("/api")
public class InquilinoController {
	@Autowired
	private IInquilinoService inquilinoService;
	
	@GetMapping("/clientes")
	public List<UsuarioDTO> findAll() {
		List<Inquilino> listaInquilinos = inquilinoService.findAll();
        List<UsuarioDTO> listaInquilinosDTOs = new ArrayList<>();

        for (Inquilino inquilino: listaInquilinos) {
            UsuarioDTO usuarioDTO = convertToDTO(inquilino);
            listaInquilinosDTOs.add(usuarioDTO);
        }

        return listaInquilinosDTOs;
	}

	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<?> getCliente(@PathVariable int usuarioId) {
		Inquilino inquilino = inquilinoService.findById(usuarioId);		

		if (inquilino == null) {
			String mensaje = "Inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		UsuarioDTO usuarioDTO = convertToDTO(inquilino);
		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
	}

	@GetMapping("/clientesParam")
	public ResponseEntity<?> getClienteParam(@RequestParam("clienteId") int usuarioId) {
		Inquilino inquilino = inquilinoService.findById(usuarioId);

		if (inquilino == null) {
			String mensaje = "Inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		UsuarioDTO inquilinoDTO = convertToDTO(inquilino);
		return new ResponseEntity<>(inquilinoDTO, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<UsuarioDTO> addCliente(@RequestBody UsuarioDTO usuarioDTO) {
		Inquilino inquilino = convertToEntity(usuarioDTO);
		
		inquilinoService.save(inquilino);
		
		UsuarioDTO nuevoUsuarioDTO = convertToDTO(inquilino);

		return new ResponseEntity<>(nuevoClienteDTO, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<?> updateCliente(@PathVariable int usuarioId, @RequestBody UsuarioDTO inquilinoDTO) {
		Inquilino inquilinoOld = inquilinoService.findById(usuarioId);

		if (inquilinoOld == null) {
			String mensaje = "inquilino no encontrado con ID: " + usuarioId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		Inquilino inquilinoToUpdate = convertToEntity(inquilinoDTO);
		inquilinoService.update(usuarioId, inquilinoToUpdate);
		
        UsuarioDTO inquilinoUpdatedDTO = convertToDTO(inquilinoToUpdate);
		return new ResponseEntity<>(inquilinoUpdatedDTO, HttpStatus.OK);
	}

	@DeleteMapping("clientes/{clienteId}")
	public ResponseEntity<String> deleteCliente(@PathVariable int inquilinoId) {
		Inquilino inquilino= inquilinoService.findById(inquilinoId);

		if (inquilino == null) {
			String mensaje = "Cliente no encontrado con ID: " + inquilinoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		inquilinoService.deleteById(inquilinoId);

		String mensaje = "Inquilino eliminado [InquilinoID: " + inquilinoId + "]";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	/**
	 * Método auxiliar para convertir a ClienteDTO
	 * @param cliente
	 * @return
	 */
	private UsuarioDTO convertToDTO(Inquilino inquilino) {
		UsuarioDTO inquilinoDTO = new UsuarioDTO(inquilino.getNombre(), inquilino.getApellido());
		return inquilinoDTO ;
	}
	//Tenemos que cambiar el tema de los dto para que sean iguales los parametros al constructor, para poder crear los 
	// inquilinos desde los dto del front. Es posible que tengamos que crear INQUILINODTO

	/**
	 * Método auxiliar para convertir a Cliente
	 * @param clienteDTO
	 * @return
	 */
	private Inquilino convertToEntity(UsuarioDTO clienteDTO) {
		Inquilino cliente = new Inquilino();
		cliente.setNombre(clienteDTO.getUsername());
		cliente.setApellido(clienteDTO.getPassword());
	
		return cliente;
	}

}
