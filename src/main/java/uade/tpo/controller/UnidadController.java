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

import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.unidad.IUnidadService;
import uade.tpo.services.usuario.IUsuarioService;
import uade.tpo.models.dto.UnidadDTO;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Unidad;
import uade.tpo.models.entity.Usuario;


@RestController
@RequestMapping("/api")
public class UnidadController {
    @Autowired
    private IUnidadService unidadService;
    @Autowired
    private IEdificioService edificioService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/unidad")
    public List<UnidadDTO> findAll() {
        List<Unidad> listaUnidades = unidadService.findAll();
        List<UnidadDTO> listaUnidadDTOs = new ArrayList<>();

        for (Unidad unidad : listaUnidades) {
            UnidadDTO unidadDTO = convertToDTO(unidad);
            listaUnidadDTOs.add(unidadDTO);
        }

        return listaUnidadDTOs;
    }

    @GetMapping("/unidad/{unidadId}")
    public ResponseEntity<?> get(@PathVariable int unidadId) {
        Unidad unidad = unidadService.findById(unidadId);

        if (unidad == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        UnidadDTO unidadDTO = convertToDTO(unidad);
        return new ResponseEntity<>(unidadDTO, HttpStatus.OK);
    }

    @PostMapping("/unidad")
    public ResponseEntity<?> add(@RequestBody UnidadDTO unidadDTO) {
		Edificio edificio = edificioService.findById(unidadDTO.getEdificio_id());
        if (edificio == null) {
        	String mensaje = "edificio not found: " + unidadDTO.getEdificio_id();
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioService.findById(unidadDTO.getUsuario_id());
        if(usuario == null) {
        	String mensaje = "usuario not found: " + unidadDTO.getEdificio_id();
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        Unidad nuevaUnidad = convertToEntity(unidadDTO, edificio);
        unidadService.save(nuevaUnidad);
        return new ResponseEntity<>(nuevaUnidad, HttpStatus.CREATED);
    }

    @PutMapping("/unidad/{unidadId}")
    public ResponseEntity<?> update(@PathVariable int unidadId, @RequestBody UnidadDTO unidadDTO) {
        Unidad unidadOld = unidadService.findById(unidadId);

        if (unidadOld == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Unidad unidadToUpdate = convertToEntity(unidadDTO,unidadOld.getEdificio());//Revisar si el getEdificio() esta bien 
        unidadService.update(unidadId, unidadToUpdate);

        UnidadDTO unidadUpdatedDTO = convertToDTO(unidadToUpdate);
        return new ResponseEntity<>(unidadUpdatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("unidad/{unidadId}")
    public ResponseEntity<String> delete(@PathVariable int unidadId) {
        Unidad unidad = unidadService.findById(unidadId);

        if (unidad == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        unidadService.deleteById(unidadId);

        String mensaje = "unidad eliminado [UnidadID: " + unidadId + "]";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    private UnidadDTO convertToDTO(Unidad unidad) {
        UnidadDTO unidadDTO = new UnidadDTO(unidad.getDpto(),unidad.getPiso());
        return unidadDTO;
    }

    private Unidad convertToEntity(UnidadDTO unidadDTO,Edificio edificio) {
        Unidad unidad = new Unidad();
        unidad.setDpto(unidadDTO.getDpto());
        unidad.setPiso(unidadDTO.getPiso());
        unidad.setEdificio(edificio);
        return unidad;
    }
}






