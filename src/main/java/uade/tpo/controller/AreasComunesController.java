/*package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import uade.tpo.services.areascomunes.IAreaComunService;
import uade.tpo.services.usuario.IUsuarioService;

public class AreasComunesController {
    @Autowired
    private IAreaComunService usuarioService;
    private static AreasComunesController usuarioController;

    public static AreasComunesController getInstance() {
        if (usuarioController == null) {
            return new AreasComunesController();
        }
        return usuarioController;
    }
}
*/



package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uade.tpo.models.dto.UsuarioDto;
import uade.tpo.models.entity.AreaComun;
import uade.tpo.models.entity.Usuario;
import uade.tpo.services.areascomunes.IAreaComunService;
import uade.tpo.models.dto.AreaComunDto;

@RestController
@RequestMapping("/api")
public class AreasComunesController {
    
    @Autowired
    private IAreaComunService areaComunService;

    
    @GetMapping("/areascomunes")
    public List<AreaComunDto> findAll() {
        List<AreaComun> listaAreasComunes = areaComunService.findAll();
        List<AreaComunDto> listaAreasComunesDTOs = new ArrayList<>();
        for (AreaComun areaComun : listaAreasComunes) {
            AreaComunDto areaComunDTO = convertToAreaComunDto(areaComun);
            listaAreasComunesDTOs.add(areaComunDTO);
        }

        return listaAreasComunesDTOs;
    }
    

    @GetMapping("/areacomun/{id}")
    public ResponseEntity<AreaComunDto> getAreaComunById(@PathVariable int id) {
        AreaComun areaComun = areaComunService.findById(id);
        if (areaComun == null) {
            String mensaje = "Area Comun no encontrada con ID: " + id;
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AreaComunDto areaComunDTO = convertToAreaComunDto(areaComun);
        return new ResponseEntity<AreaComunDto>(areaComunDTO, HttpStatus.OK);

    }

    @PostMapping("/areacomun")
    public ResponseEntity<AreaComunDto> addAreaComun(@RequestBody AreaComunDto areaComunDto) {
        AreaComun areaComun = dtoToAreaComun(areaComunDto);
        areaComunService.save(areaComun);
        System.out.println(" ");
        System.out.println("Se ha creado el area comun: " + areaComun.getTipoAreaComun() + " Correctamente");
        return new ResponseEntity<>(areaComunDto, HttpStatus.CREATED);
    }

    @PutMapping("/areacomun/{id}")
    public ResponseEntity<Void> updateAreaComun(@PathVariable int id, @RequestBody AreaComun areaComun) {
        // Check if the areaComun with the given id exists
        if (areaComunService.findById(id) != null) {
            areaComunService.update(id, areaComun);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/areacomun/{id}")
    public ResponseEntity<Void> deleteAreaComun(@PathVariable int id) {
        // Check if the areaComun with the given id exists
        if (areaComunService.findById(id) != null) {
            areaComunService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/areascomunes/edificio/{edificioId}")
    public ResponseEntity<?> obtenerAreasComunesPorEdificio(@PathVariable int edificioId) {
        List<AreaComun> listaAreasComunesEdificio = areaComunService.findByEdificioId(edificioId);

        if (listaAreasComunesEdificio.isEmpty()) {
            String mensaje = "No pertenece al edificio con ID: " + edificioId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        List<AreaComunDto> listaAreasComunesEdificioDTOs = new ArrayList<>();
        for (AreaComun areaComun : listaAreasComunesEdificio) {
            AreaComunDto areaComunDto = convertToAreaComunDto(areaComun);
            listaAreasComunesEdificioDTOs.add(areaComunDto);
        }

        return new ResponseEntity<>(listaAreasComunesEdificioDTOs, HttpStatus.OK);
    }



    public AreaComunDto convertToAreaComunDto(AreaComun areaComun) {
      return new AreaComunDto(areaComun.getId(), areaComun.getTipoAreaComun(), areaComun.getCapacidad(), areaComun.getEdificio());
    }


    private AreaComun dtoToAreaComun(AreaComunDto areaComunDto) {
        AreaComun areaComun = new AreaComun();
        //areaComun.setId(areaComunDto.getId());
        areaComun.setTipoAreaComun(areaComunDto.getTipoAreaComun());
        areaComun.setCapacidad(areaComunDto.getCapacidad());
        areaComun.setEdificio(areaComunDto.getEdificio());
        return areaComun;

    }
}