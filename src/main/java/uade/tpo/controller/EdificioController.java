package uade.tpo.controller;

import java.util.ArrayList;
import java.util.Date;
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

import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.unidad.IUnidadService;
import uade.tpo.models.dto.EdificioDTO;
import uade.tpo.models.entity.Direccion;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Unidad;


@RestController
@RequestMapping("/api")
public class EdificioController {
    @Autowired
    private IEdificioService edificioService;

	@Autowired
    private IUnidadService unidadService;

    @GetMapping("/edificios") // TODO: Finalizado OK
    public List<EdificioDTO> findAll() {
        List<Edificio> listaEdificios = edificioService.findAll();
        List<EdificioDTO> listaEdificioDTOs = new ArrayList<>();

        for (Edificio edificio : listaEdificios) {
            EdificioDTO edificioDTO = convertToDTO(edificio);
            listaEdificioDTOs.add(edificioDTO);
        }

        return listaEdificioDTOs;
    }

    @GetMapping("/edificio/{edificioId}") // TODO: Finalizado OK
    public ResponseEntity<?> get(@PathVariable int edificioId) {
        Edificio edificio = edificioService.findById(edificioId);

        if (edificio == null) {
            String mensaje = "edificio no encontrado con ID: " + edificioId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        EdificioDTO edificioDTO = convertToDTO(edificio);
        return new ResponseEntity<>(edificioDTO, HttpStatus.OK);
    }

    @PostMapping("/edificios") // TODO: Finalizado OK
    public ResponseEntity<EdificioDTO> add(@RequestBody EdificioDTO edificioDTO) {
        Edificio edificio = convertToEntity(edificioDTO);

        edificioService.save(edificio);

        EdificioDTO nuevoEdificioDTO = convertToDTO(edificio);

        return new ResponseEntity<>(nuevoEdificioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/edificio/{edificioId}") // TODO: Finalizado OK
    public ResponseEntity<?> update(@PathVariable int edificioId, @RequestBody EdificioDTO edificioDTO) {
        Edificio edificioOld = edificioService.findById(edificioId);
        if (edificioOld == null) {
            String mensaje = "edificio no encontrado con ID: " + edificioId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Edificio edificioToUpdate = convertToEntity(edificioDTO);
        edificioService.update(edificioId, edificioToUpdate);

        EdificioDTO edificioUpdatedDTO = convertToDTO(edificioToUpdate);
        return new ResponseEntity<>(edificioUpdatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("edificio/{edificioId}") // TODO: Finalizado OK
    public ResponseEntity<String> deleteEdificio(@PathVariable int edificioId) {
        Edificio edificio = edificioService.findById(edificioId);
        if (edificio == null) {
            String mensaje = "edificio no encontrado con ID: " + edificioId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        edificioService.deleteById(edificioId);

        String mensaje = "edificio eliminado [EdificioID: " + edificioId + "]";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    private EdificioDTO convertToDTO(Edificio edificio) {
        return new EdificioDTO(edificio.getNombre(), edificio.getDireccion(), edificio.getNumeroPisos(), edificio.isTieneAscensor());
    }

    private Edificio convertToEntity(EdificioDTO edificioDTO) {
        Edificio edificio = new Edificio();
        edificio.setDireccion(edificioDTO.getDireccion());
        edificio.setNombre(edificioDTO.getNombre());
        edificio.setNumeroPisos(edificioDTO.getNumeroPisos());
        edificio.setTieneAscensor(edificioDTO.isTieneAscensor());
        return edificio;
    }

	@PostMapping("/edificio/{edificioId}/unidad")
	public ResponseEntity<String> addUnidad(int edificioID, int unidadID ) {
		Edificio edificio = edificioService.findById(edificioID);
		if (edificio == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Edificio no existe");
		}
		Unidad unidad = unidadService.findById(unidadID);
		if (unidad == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unidad no existe");
		}

		edificio.getUnidades().add(unidad);

		edificioService.update(edificioID, edificio);

		return new ResponseEntity<>(mensaje, HttpStatus.OK);

		if(edificio != null && unidad != null) {
			edificio.getUnidades().add(unidad);
			edificioService.update(edificioID, edificio);

			if(unidad.getEdificio() == null) {
				unidad.setEdificio(edificio);
				String mensaje = "La unidad se ha asociado correctamente con el edificio: "+ edificio.getNombre();
				return new ResponseEntity<>(mensaje, HttpStatus.OK);
			}
			else {
				String mensaje = "La unidad ya se encuentra asociada a un edificio";
				return new ResponseEntity<>(mensaje, HttpStatus.OK);
			}
		}
		else {
			String mensaje = "Se ha producido un error";
			return new ResponseEntity<>(mensaje, HttpStatus.OK);
		}

	}
}





