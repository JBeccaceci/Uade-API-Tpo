package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uade.tpo.models.dto.EdificioDTO;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Unidad;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.unidad.IUnidadService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EdificioController {
    @Autowired
    private IEdificioService edificioService;

	@Autowired
    private IUnidadService unidadService;

    @GetMapping("/edificios")
    public List<EdificioDTO> findAll() {
        List<Edificio> listaEdificios = edificioService.findAll();
        List<EdificioDTO> listaEdificioDTOs = new ArrayList<>();

        for (Edificio edificio : listaEdificios) {
            EdificioDTO edificioDTO = convertToDTO(edificio);
            listaEdificioDTOs.add(edificioDTO);
        }

        return listaEdificioDTOs;
    }
    
    @GetMapping("/edificio/{usuarioId}")
    public List<EdificioDTO> devolverRelacionado(@PathVariable int usuarioId){
    	List<Edificio> listaEdificiosRelacionados = edificioService.findAllRelacionados(usuarioId);
        List<EdificioDTO> listaEdificioDTOs = new ArrayList<>();
        
        for(Edificio edificio : listaEdificiosRelacionados) {
        	EdificioDTO edificioDTO = convertToDTO(edificio);
        	listaEdificioDTOs.add(edificioDTO);
        }
        return listaEdificioDTOs;
    }

    @GetMapping("/edificio/{edificioId}")
    public ResponseEntity<?> get(@PathVariable int edificioId) {
        Edificio edificio = edificioService.findById(edificioId);

        if (edificio == null) {
            String mensaje = "edificio no encontrado con ID: " + edificioId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        EdificioDTO edificioDTO = convertToDTO(edificio);
        return new ResponseEntity<>(edificioDTO, HttpStatus.OK);
    }

    @PostMapping("/edificio")
    public ResponseEntity<EdificioDTO> add(@RequestBody EdificioDTO edificioDTO) {
        Edificio edificio = convertToEntity(edificioDTO);

        edificioService.save(edificio);

        EdificioDTO nuevoEdificioDTO = convertToDTO(edificio);

        return new ResponseEntity<>(nuevoEdificioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/edificio/{edificioId}")
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

    @DeleteMapping("/edificio/{edificioId}")
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
}





