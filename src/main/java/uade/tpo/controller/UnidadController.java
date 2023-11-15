package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import uade.tpo.config.JWTAuthInfo;
import uade.tpo.models.UnidadUsuarioDTO;
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

    @GetMapping("/unidades")
    public List<UnidadDTO> findAll() {
        List<Unidad> listaUnidades = unidadService.findAll();
        List<UnidadDTO> listaUnidadDTOs = new ArrayList<>();

        for (Unidad unidad : listaUnidades) {
            UnidadDTO unidadDTO = convertToDTO(unidad);
            listaUnidadDTOs.add(unidadDTO);
        }

        return listaUnidadDTOs;
    }

    @GetMapping("/unidad")
    public List<UnidadUsuarioDTO> getByUserAndBuilding(@RequestParam(name = "edificioId") Integer edificioId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTAuthInfo jwtAuthInfo = (JWTAuthInfo) authentication.getPrincipal();
        return unidadService.getUnitsByOccupant(Integer.parseInt(jwtAuthInfo.getUserId()), edificioId);
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody UnidadDTO unidadDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTAuthInfo jwtAuthInfo = (JWTAuthInfo) authentication.getPrincipal();

        Edificio edificio = edificioService.findById(unidadDTO.getEdificio_id());
        if (edificio == null) {
        	String mensaje ="esta es la unidad dto ";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        Usuario propietario = usuarioService.findById(Integer.parseInt(jwtAuthInfo.getUserId()));
        if(propietario == null) {
            String mensaje = "usuario not found: " + unidadDTO.getEdificio_id();
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Unidad unidad2 = new Unidad();
        unidad2.setHabitante(propietario);
        unidad2.setEdificio(edificio);
        unidadService.save(unidad2);
        return new ResponseEntity<>(unidad2, HttpStatus.CREATED);
    }

    @PutMapping("/unidad/{unidadId}")
    public ResponseEntity<?> update(@PathVariable int unidadId, @RequestBody UnidadDTO unidadDTO) {
        Unidad unidadOld = unidadService.findById(unidadId);

        if (unidadOld == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        //Unidad unidadToUpdate = convertToEntity(unidadDTO, unidadOld.getEdificio(), unidadOld.getPropietario());//Revisar si el getEdificio() esta bien
        //unidadService.update(unidadId, unidadToUpdate);

        //UnidadDTO unidadUpdatedDTO = convertToDTO(unidadToUpdate);
        return new ResponseEntity<>("unidadUpdatedDTO", HttpStatus.OK);
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
        return new UnidadDTO(unidad.getHabitantes() ,unidad.getEdificio().getId(),unidad.getDpto(),unidad.getPiso());
    }

    private Unidad convertToEntity(UnidadDTO unidadDTO, Edificio edificio, Usuario usuario) {
        Unidad unidad = new Unidad();
        unidad.setDpto(unidadDTO.getDpto());
        unidad.setPiso(unidadDTO.getPiso());
        unidad.setEdificio(edificio);
        unidad.setHabitante(usuario);
        return unidad;
    }
}






