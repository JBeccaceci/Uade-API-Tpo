package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uade.tpo.config.JWTAuthInfo;
import uade.tpo.models.dto.NewReclamoDTO;
import uade.tpo.models.dto.NewReclamoInputDto;
import uade.tpo.models.dto.ReclamoDTO;
import uade.tpo.models.dto.UpdateReclamoDTO;
import uade.tpo.models.entity.*;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.TipoReclamo;
import uade.tpo.services.Imagen.IImagenService;
import uade.tpo.services.edificio.IEdificioService;
import uade.tpo.services.reclamo.IReclamoService;
import uade.tpo.services.unidad.IUnidadService;
import uade.tpo.services.usuario.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReclamoController {
    @Autowired
    private IReclamoService reclamoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IEdificioService edificioService;

    @Autowired
    private IUnidadService unidadService;

    @Autowired
    private IImagenService iImagenService;

    @PostMapping(value = "/reclamo", consumes = "multipart/form-data")
    public ResponseEntity<?> add(
            @RequestPart("files") List<MultipartFile> files,
            @RequestPart("data") NewReclamoInputDto data
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTAuthInfo jwtAuthInfo = (JWTAuthInfo) authentication.getPrincipal();

        Usuario usuario = usuarioService.findById(Integer.parseInt(jwtAuthInfo.getUserId()));
        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        Unidad unidad = unidadService.findById(Integer.parseInt(data.getUnidadId()));
        if (unidad == null) {
            return new ResponseEntity<>("Unidad no encontrada", HttpStatus.NOT_FOUND);
        }
        Edificio edificio = edificioService.findById(Integer.parseInt(data.getEdificioId()));
        if (edificio == null) {
            return new ResponseEntity<>("Edificio no encontrado", HttpStatus.NOT_FOUND);
        }
        boolean perceneceEdificio = edificio.getUnidades().stream()
                .flatMap(uni -> uni.getHabitantes().stream())
                .anyMatch(hab -> hab.getId() == Integer.parseInt(jwtAuthInfo.getUserId()));
        if (!perceneceEdificio) {
            return new ResponseEntity<>("Usuario no pertenece al edificio", HttpStatus.NOT_FOUND);
        }

        if (data.isAreaComun()) {
            try {
                TipoReclamo tipoReclamo = TipoReclamo.valueOf(data.getTipoReclamo());
                Reclamo newReclamo = new Reclamo(tipoReclamo, data.getDescripcion(), usuario, unidad, edificio, data.isAreaComun());
                unidad.setReclamos(newReclamo);
                usuario.setReclamos(newReclamo);
                edificio.setReclamos(newReclamo);
                reclamoService.save(newReclamo);

                for (MultipartFile file : files) {
                    Imagen img = new Imagen(file.getBytes());
                    newReclamo.addImagen(img);
                    iImagenService.save(img);
                }

                ReclamoDTO reclamoDTO = convertToDTO(newReclamo);
                return new ResponseEntity<>(reclamoDTO, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error no controlado", HttpStatus.NOT_FOUND);
            }
        }

        if (data.getUnidadId() != null) {
            boolean perteneceUnidad = unidad.getHabitantes().stream()
                    .anyMatch(hab -> hab.getId() == Integer.parseInt(jwtAuthInfo.getUserId()));
            if (!perteneceUnidad) {
                return new ResponseEntity<>("Debes ser dueño o perteneces a la unidad", HttpStatus.NOT_FOUND);
            }
            try {
                TipoReclamo tipoReclamo = TipoReclamo.valueOf(data.getTipoReclamo());
                Reclamo newReclamo = new Reclamo(tipoReclamo, data.getDescripcion(), usuario, unidad, edificio, data.isAreaComun());
                unidad.setReclamos(newReclamo);
                usuario.setReclamos(newReclamo);
                edificio.setReclamos(newReclamo);
                reclamoService.save(newReclamo);

                for (MultipartFile file : files) {
                    Imagen img = new Imagen(file.getBytes());
                    newReclamo.addImagen(img);
                    iImagenService.save(img);
                }

                ReclamoDTO reclamoDTO = convertToDTO(newReclamo);
                return new ResponseEntity<>(reclamoDTO, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error no controlado", HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>("Error no controlado", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reclamo")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String estado) {
        EstadoReclamo estadoReclamo = EstadoReclamo.get(estado).orElse(EstadoReclamo.NUEVO);
        List<Reclamo> reclamoList = reclamoService.findAll()
                .stream()
                .filter(r -> r.getEstadoReclamo().getName().equals(estadoReclamo.getName()))
                .collect(Collectors.toList());

        List<ReclamoDTO> reclamoDTOList = new ArrayList<>();
        for (Reclamo reclamo : reclamoList) {
            reclamoDTOList.add(this.convertToDTO(reclamo));
        }

        return new ResponseEntity<>(reclamoDTOList, HttpStatus.OK);
    }

    @GetMapping("/reclamos")
    public ResponseEntity<?> getEdificioReclamo(@RequestParam(name = "edificioId") String edificioId) {
        List<Reclamo> reclamoList = reclamoService
                .getReclamosByEdificioId(Integer.parseInt(edificioId));

        List<NewReclamoDTO> reclamoDTOList = new ArrayList<>();
        for (Reclamo reclamo : reclamoList) {
            reclamoDTOList.add(this.converToNewReclamoDTO(reclamo));
        }
        return new ResponseEntity<>(reclamoDTOList, HttpStatus.OK);
    }

    @PutMapping("/reclamo/{reclamoId}")
    public ResponseEntity<?> update(@PathVariable int reclamoId, @RequestBody UpdateReclamoDTO updateReclamoDTO) {
        Reclamo reclamo = reclamoService.findById(reclamoId);
        if (reclamo == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Reclamo inexistente");
        }

        List<Medida> medidaList = new ArrayList<>(reclamo.getMedidas());
        for (String medida : updateReclamoDTO.getMedidas()) {
            medidaList.add(new Medida(medida, reclamo));
        }
        List<Imagen> imagenes = new ArrayList<>();
        if (reclamo.getImagenes() != null) {
			/*
			for (byte[] imagenBytes : reclamo.getImagenes()) {
				Imagen imagen = new Imagen();
				imagen.setImagen(imagenBytes);
				imagenes.add(imagen);
			}

			 */
        }
		/*
		if(updateReclamoDTO.getImagenes() != null){
			for (byte[] imagenBytes : reclamo.getImagenes()) {
				Imagen imagen = new Imagen();
				imagen.setImagen(imagenBytes);
				imagenes.add(imagen);
			}
		}

		 */

        reclamo.setImagenes(imagenes);
        reclamo.setEstadoReclamo(updateReclamoDTO.getEstadoReclamo());
        reclamo.setMedidas(medidaList);
        reclamoService.update(reclamo.getId(), reclamo);
        return new ResponseEntity<>(updateReclamoDTO, HttpStatus.OK);
    }

    private ReclamoDTO convertToDTO(Reclamo reclamo) {
        return new ReclamoDTO(
                reclamo.getTipoReclamo(),
                reclamo.getDescripcion(),
                reclamo.getUsuario().getId(),
                reclamo.getUnidad().getId(),
                reclamo.getEdificio().getId(),
                reclamo.isEsAreaComun(),
                reclamo.getEstadoReclamo(),
                reclamo.getImagenes());
    }

    private NewReclamoDTO converToNewReclamoDTO(Reclamo reclamo) {
        return new NewReclamoDTO(
                String.valueOf(reclamo.getId()),
                reclamo.getTipoReclamo(),
                reclamo.getDescripcion(),
                reclamo.isEsAreaComun(),
                reclamo.getImagenes(),
                reclamo.getEstadoReclamo(),
                reclamo.getMedidas());
    }

    private Reclamo convertToEntity(ReclamoDTO reclamoDTO, Usuario usuario, Edificio edificio, Unidad unidad) {
        return new Reclamo(reclamoDTO.getTipoReclamo(), reclamoDTO.getDescripcion(), usuario, unidad, edificio, false);
    }
}
