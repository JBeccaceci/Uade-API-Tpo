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

import org.springframework.web.multipart.MultipartFile;
import uade.tpo.models.dto.UsuarioDto;
import uade.tpo.models.entity.AreaComun;
import uade.tpo.models.entity.Edificio;
import uade.tpo.models.entity.Imagen;
import uade.tpo.models.entity.Usuario;
import uade.tpo.services.areascomunes.IAreaComunService;
import uade.tpo.models.dto.AreaComunDto;
import uade.tpo.services.edificio.IEdificioService;

@RestController
@RequestMapping("/api")
public class AreasComunesController {
    @Autowired
    private IAreaComunService areaComunService;

    @Autowired
    private IEdificioService edificioService;

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
    public ResponseEntity<?> addAreaComun(@RequestBody AreaComunDto areaComunDto) {
        Edificio edificio = edificioService.findById(areaComunDto.getEdificioId());
        if (edificio == null) {
            String mensaje = "Edificio no encontrado ";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        AreaComun areaComun = new AreaComun(areaComunDto.getTipoAreaComun(), 0, edificio);
        edificio.setAreaComun(areaComun);
        areaComunService.save(areaComun);

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

        List<AreaComunDto> listaAreasComunesEdificioDTOs = new ArrayList<>();
        for (AreaComun areaComun : listaAreasComunesEdificio) {
            AreaComunDto areaComunDto = convertToAreaComunDto(areaComun);
            listaAreasComunesEdificioDTOs.add(areaComunDto);
        }

        return new ResponseEntity<>(listaAreasComunesEdificioDTOs, HttpStatus.OK);
    }



    public AreaComunDto convertToAreaComunDto(AreaComun areaComun) {
      return new AreaComunDto(areaComun.getTipoAreaComun(), areaComun.getEdificio().getId(), areaComun.getCapacidad());
    }


    private AreaComun dtoToAreaComun(AreaComunDto areaComunDto, Edificio edificio) {
        AreaComun areaComun = new AreaComun();
        areaComun.setTipoAreaComun(areaComunDto.getTipoAreaComun());
        areaComun.setCapacidad(areaComunDto.getCapacidad());
        areaComun.setEdificio(edificio);
        return areaComun;

    }
}