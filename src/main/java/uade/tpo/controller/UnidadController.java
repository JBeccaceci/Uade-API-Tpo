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

import uade.tpo.services.unidad.IUnidadService;
import uade.tpo.models.dto.UnidadDTO;
import uade.tpo.models.entity.Unidad;


@RestController
@RequestMapping("/api")
public class UnidadController {
    @Autowired
    private IUnidadService unidadService;

    @GetMapping("/usuarios")
    public List<UnidadDTO> findAll() {
        List<Unidad> listaUnidades = unidadService.findAll();
        List<UnidadDTO> listaUnidadDTOs = new ArrayList<>();

        for (Unidad unidad : listaUnidades) {
            UnidadDTO unidadDTO = convertToDTO(unidad);
            listaUnidadDTOs.add(unidadDTO);
        }

        return listaUnidadDTOs;
    }

    @GetMapping("/unidades/{unidadId}")
    public ResponseEntity<?> getUnidad(@PathVariable int unidadId) {
        Unidad unidad = unidadService.findById(unidadId);

        if (unidad == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        UnidadDTO unidadDTO = convertToDTO(unidad);
        return new ResponseEntity<>(unidadDTO, HttpStatus.OK);
    }

    @GetMapping("/unidadesParam")
    public ResponseEntity<?> getUnidadParam(@RequestParam("unidadId") int unidadId) {
        Unidad unidad = unidadService.findById(unidadId);

        if (unidad == null) {
            String mensaje = "Inquilino no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        UnidadDTO unidadDTO = convertToDTO(unidad);
        return new ResponseEntity<>(unidadDTO, HttpStatus.OK);
    }

    @PostMapping("/unidades")
    public ResponseEntity<UnidadDTO> addUnidad(@RequestBody UnidadDTO unidadDTO) {
        Unidad unidad = convertToEntity(unidadDTO);

        unidadService.save(unidad);

        UnidadDTO nuevoUnidadDTO = convertToDTO(unidad);

        return new ResponseEntity<>(nuevoUnidadDTO, HttpStatus.CREATED);
    }

    @PutMapping("/unidadess/{unidadId}")
    public ResponseEntity<?> updateUnidad(@PathVariable int unidadId, @RequestBody UnidadDTO unidadDTO) {
        Unidad unidadOld = unidadService.findById(unidadId);

        if (unidadOld == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Unidad unidadToUpdate = convertToEntity(unidadDTO);
        unidadService.update(unidadId, unidadToUpdate);

        UnidadDTO unidadUpdatedDTO = convertToDTO(unidadToUpdate);
        return new ResponseEntity<>(unidadUpdatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("unidades/{unidadId}")
    public ResponseEntity<String> deleteUnidad(@PathVariable int unidadId) {
        Unidad unidad = unidadService.findById(unidadId);

        if (unidad == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        unidadService.deleteById(unidadId);

        String mensaje = "unidad eliminado [UnidadID: " + unidadId + "]";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    /**
     * Método auxiliar para convertir a UnidadDTO
     *
     * @param unidad
     * @return
     */
    private UnidadDTO convertToDTO(Unidad unidad) {
        UnidadDTO unidadDTO = new UnidadDTO(unidad.getDpto(), unidad.getPiso(), unidad.getEdificio());
        return unidadDTO;
    }


    private Unidad convertToEntity(UnidadDTO unidadDTO) {
        Unidad unidad = new Unidad();
        unidad.setDpto(unidadDTO.getDpto());
        unidad.setPiso(unidadDTO.getPiso());
        unidad.setEdificio(unidadDTO.getEdificio());

        return unidad;
    }


}






