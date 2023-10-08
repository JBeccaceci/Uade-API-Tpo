package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uade.tpo.models.dto.ReclamoDTO;
import uade.tpo.models.entity.Reclamo;
import uade.tpo.services.reclamo.IReclamoService;

@RestController
@RequestMapping("/api")
public class ReclamoController {
	@Autowired
	private IReclamoService reclamoService;
	
	@GetMapping("/usuarios")
	public List<ReclamoDTO> findAll() {
		List<Reclamo> listaReclamos = reclamoService.findAll();
        List<ReclamoDTO> listaReclamosDTOs = new ArrayList<>();

        for (Reclamo reclamo: listaReclamos) {
            ReclamoDTO reclamoDTO = convertToDTO(reclamo);
            listaReclamosDTOs.add(reclamoDTO);
        }

        return listaReclamosDTOs;
	}

	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<?> getReclamo(@PathVariable int reclamoId) {
		Reclamo reclamo = reclamoService.findById(reclamoId);		

		if (reclamo == null) {
			String mensaje = "reclamo no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		ReclamoDTO reclamoDTO = convertToDTO(reclamo);
		return new ResponseEntity<>(reclamoDTO, HttpStatus.OK);
	}

	@GetMapping("/clientesParam")
	public ResponseEntity<?> getClienteParam(@RequestParam("clienteId") int reclamoId) {
		Reclamo reclamo= reclamoService.findById(reclamoId);

		if (reclamo == null) {
			String mensaje = "Inquilino no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		ReclamoDTO reclamoDTO = convertToDTO(reclamo);
		return new ResponseEntity<>(reclamoDTO, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<ReclamoDTO> addCliente(@RequestBody ReclamoDTO reclamoDTO) {
		Reclamo reclamo = convertToEntity(reclamoDTO);
		
		reclamoService.save(reclamo);
		
		ReclamoDTO reclamoDTO = convertToDTO(reclamo);

		return new ResponseEntity<>(reclamoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<?> updateCliente(@PathVariable int reclamoId, @RequestBody ReclamoDTO reclamoDTO) {
		Reclamo reclamoOld = reclamoService.findById(reclamoId);

		if (reclamoOld == null) {
			String mensaje = "reclamo no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		Reclamo reclamoToUpdate = convertToEntity(reclamoDTO);
		reclamoService.update(reclamoId, reclamoToUpdate);
		
		ReclamoDTO reclamoUpdatedDTO = convertToDTO(reclamoToUpdate);
		return new ResponseEntity<>(reclamoUpdatedDTO, HttpStatus.OK);
	}

	@DeleteMapping("clientes/{clienteId}")
	public ResponseEntity<String> deleteCliente(@PathVariable int reclamoId) {
		Reclamo reclamo= reclamoService.findById(reclamoId);

		if (reclamo == null) {
			String mensaje = "reclamo no encontrado con ID: " + reclamoId;
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}

		reclamoService.deleteById(reclamoId);

		String mensaje = "reclamo eliminado [reclamoID: " + reclamoId + "]";
		return new ResponseEntity<>(mensaje, HttpStatus.OK);
	}

	/**
	 * Método auxiliar para convertir a ClienteDTO
	 * @param cliente
	 * @return
	 */
	private ReclamoDTO convertToDTO(Reclamo reclamo) {
		ReclamoDTO reclamoDTO = new ReclamoDTO(reclamo.getId(), reclamo.getTipoReclamo(), reclamo.getDescripcion(), reclamo.getCreado(), reclamo.getActualizado(), reclamo.getUsuario(), null, reclamo.getEstadoReclamo());
		return reclamoDTO;
	}
	//Tenemos que cambiar el tema de los dto para que sean iguales los parametros al constructor, para poder crear los 
	// inquilinos desde los dto del front. Es posible que tengamos que crear INQUILINODTO

	/**
	 * Método auxiliar para convertir a Cliente
	 * @param clienteDTO
	 * @return
	 */
	private Reclamo convertToEntity(ReclamoDTO reclamoDTO) {
		Reclamo reclamo= new Reclamo();
		reclamo.setTipoReclamo(reclamoDTO.getTipoReclamo());
		reclamo.setDescripcion(reclamoDTO.getDescripcion());
		reclamo.setCreado(reclamoDTO.getCreado());
		reclamo.setActualizado(reclamoDTO.getActualizado());
		reclamo.setUsuario(reclamoDTO.getUsuario());
		reclamo.setEstadoReclamo(reclamoDTO.getEstadoReclamo());
	
		return reclamo;
	}


}
