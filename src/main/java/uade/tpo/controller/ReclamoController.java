package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import uade.tpo.models.dto.UpdateReclamoDTO;
import uade.tpo.models.entity.*;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.reclamo.IReclamoService;
import uade.tpo.services.unidad.IUnidadService;
import uade.tpo.services.usuario.IUsuarioService;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {
    @Autowired
    private IReclamoService reclamoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IEdificioService edificioService;

    @Autowired
    private IUnidadService unidadService;

    @PostMapping("/add") // TODO: Finalizado OK
    public ResponseEntity<?> add(@RequestBody ReclamoDTO reclamoDTO) {
        Usuario usuario = usuarioService.findById(reclamoDTO.getUsuarioId());
        if (usuario == null) {
            String mensaje = "usuario not found: " + reclamoDTO.getUsuarioId();
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        Edificio edificio = null;
        if (reclamoDTO.getEdificio_id() != null) {
            edificio = edificioService.findById(reclamoDTO.getEdificio_id());
        }
        Unidad unidad = null;
        if (reclamoDTO.getUnidad_id() != null) {
            unidad = unidadService.findById(reclamoDTO.getUnidad_id());
        }
        if (edificio == null && unidad == null) {
            String mensaje = "El reclamo tiene que formar parte de un edificio o unidad";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Reclamo reclamo = convertToEntity(reclamoDTO, usuario, edificio, unidad);
        reclamoService.save(reclamo);

        ReclamoDTO reclamoDTOoutput = convertToDTO(reclamo);
        return new ResponseEntity<>(reclamoDTOoutput, HttpStatus.CREATED);
    }

    @GetMapping("/find") // TODO: Finalizado OK
    public ResponseEntity<?> getAll(@RequestParam(required = false) String estado) {
        EstadoReclamo estadoReclamo = EstadoReclamo.get(estado).orElse(EstadoReclamo.NUEVO);
        List<Reclamo> reclamoList = reclamoService.findAll()
                .stream()
                .filter(r -> r.getEstadoReclamo().getName().equals(estadoReclamo.getName()))
                .collect(Collectors.toList());

        List<ReclamoDTO> reclamoDTOList = new ArrayList<>();
        for (Reclamo reclamo: reclamoList) {
            reclamoDTOList.add(this.convertToDTO(reclamo));
        }

        return new ResponseEntity<>(reclamoDTOList, HttpStatus.OK);
    }

    @PostMapping("/update") // TODO: Finalizado OK
    public ResponseEntity<?> update(@RequestBody UpdateReclamoDTO updateReclamoDTO) {
        Reclamo reclamo = reclamoService.findById(updateReclamoDTO.getReclamoId());
        if (reclamo == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Reclamo inexistente");
        }

        List<Medida> medidaList = new ArrayList<>(reclamo.getMedidas());
        for (String medida : updateReclamoDTO.getMedidas()) {
            medidaList.add(new Medida(medida, reclamo));
        }

        reclamo.setEstadoReclamo(updateReclamoDTO.getEstadoReclamo());
        reclamo.setMedidas(medidaList);
        reclamoService.update(reclamo.getId(), reclamo);
        return new ResponseEntity<>(updateReclamoDTO, HttpStatus.OK);
    }

    private ReclamoDTO convertToDTO(Reclamo reclamo) {
        return new ReclamoDTO(
                 reclamo.getTipoReclamo(),
                reclamo.getDescripcion(), reclamo.getUsuario().getId(),
                reclamo.getUnidad().getId(), reclamo.getEdificio().getId(), new ArrayList<>(), reclamo.getEstadoReclamo());
    }

    private Reclamo convertToEntity(ReclamoDTO reclamoDTO, Usuario usuario, Edificio edificio, Unidad unidad) {
        return new Reclamo(reclamoDTO.getTipoReclamo(), reclamoDTO.getDescripcion(), usuario, unidad, edificio);
    }
}
