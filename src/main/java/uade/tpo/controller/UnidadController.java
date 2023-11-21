package uade.tpo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import uade.tpo.config.JWTAuthInfo;
import uade.tpo.models.UnidadUsuarioDTO;
import uade.tpo.models.dto.UnidadEdificioDto;
import uade.tpo.models.dto.UsuarioDto;
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

        if ("ADMIN".equals(jwtAuthInfo.getRole())) {
            List<Unidad> listaUnidades = unidadService.findAll();

            List<UnidadUsuarioDTO> listaUnidadDTOs = new ArrayList<>();
            for (Unidad unidad : listaUnidades) {
                if (unidad.getEdificio().getId() == edificioId) {
                    listaUnidadDTOs.add(new UnidadUsuarioDTO(unidad.getId(), unidad.getEdificio().getNombre()));
                }
            }
            return listaUnidadDTOs;
        }

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> add(@RequestBody UnidadDTO unidadDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTAuthInfo jwtAuthInfo = (JWTAuthInfo) authentication.getPrincipal();

        Edificio edificio = edificioService.findById(Integer.parseInt(unidadDTO.getEdificio_id()));
        if (edificio == null) {
            String mensaje = "Edificio no encontrado ";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        Optional<String> existPropietario = unidadDTO.getHabitantes()
                .stream()
                .filter(h -> jwtAuthInfo.getUserName().equals(h))
                .findAny();
        if (!existPropietario.isPresent()) {
            //unidadDTO.getHabitantes().add(jwtAuthInfo.getUserName());
        }

        Unidad nuevaUnidad = new Unidad();
        nuevaUnidad.setEdificio(edificio);
        nuevaUnidad.setDpto(Integer.parseInt(unidadDTO.getDpto()));
        nuevaUnidad.setPiso(Integer.parseInt(unidadDTO.getPiso()));
        for (String usuario : unidadDTO.getHabitantes()) {
            Usuario us = usuarioService.findByUsername(usuario);
            if (us == null) {
                String mensaje = "usuario not found: " + unidadDTO.getEdificio_id();
                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
            us.setUnidad(nuevaUnidad);
            usuarioService.save(us);

            nuevaUnidad.setHabitante(us);
        }
        unidadService.save(nuevaUnidad);
        System.out.println(" ");
        System.out.println("Se ha creado la unidad: "+ nuevaUnidad.getId()+ " Correctamente");

        UnidadEdificioDto unidadDTOOutput = new UnidadEdificioDto(String.valueOf(nuevaUnidad.getId()), edificio.getNombre());
        return new ResponseEntity<>(unidadDTOOutput, HttpStatus.CREATED);
    }

    @PutMapping("/unidad/{unidadId}")
    public ResponseEntity<?> update(@PathVariable int unidadId, @RequestBody UnidadDTO unidadDTO) {
        Unidad unidadOld = unidadService.findById(unidadId);

        if (unidadOld == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        // Obtener el Edificio correspondiente
        Edificio edificio = edificioService.findById(Integer.parseInt(unidadDTO.getEdificio_id()));
        if (edificio == null) {
            String mensaje = "Edificio no encontrado ";
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }
        unidadOld.setEdificio(edificio);
        unidadOld.setDpto(Integer.parseInt(unidadDTO.getDpto()));
        unidadOld.setPiso(Integer.parseInt(unidadDTO.getPiso()));

        for (String usuario : unidadDTO.getHabitantes()) {
            Usuario us = usuarioService.findByUsername(usuario);
            if (us == null) {
                String mensaje = "usuario not found: " + unidadDTO.getEdificio_id();
                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
            us.setUnidad(unidadOld);
            unidadOld.setHabitante(us);
        }

        // Guardar la unidad actualizada
        unidadService.save(unidadOld);
        System.out.println(" ");
        System.out.println("Se ha modificado el actualizado: "+ unidadOld.getId() + " Correctamente");
        UnidadDTO unidadUpdatedDTO = convertToDTO(unidadOld);
        return new ResponseEntity<>(unidadUpdatedDTO, HttpStatus.OK);
    }


    @DeleteMapping("unidad/{unidadId}")
    public ResponseEntity<String> delete(@PathVariable int unidadId) {
        System.out.println("Received DELETE request for unidadId: " + unidadId);

        Unidad unidad = unidadService.findById(unidadId);

        if (unidad == null) {
            String mensaje = "unidad no encontrado con ID: " + unidadId;
            System.out.println(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
        }

        unidadService.deleteById(unidadId);
        System.out.println(" ");
        System.out.println("Se ha eliminado la unidad: "+ unidad.getId()+ " Correctamente");

        String mensaje = "unidad eliminado [UnidadID: " + unidadId + "]";
        System.out.println(mensaje);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    private UnidadDTO convertToDTO(Unidad unidad) {
        return new UnidadDTO(null, String.valueOf(unidad.getEdificio().getId()), String.valueOf(unidad.getDpto()), String.valueOf(unidad.getPiso()));
    }

    private Unidad convertToEntity(UnidadDTO unidadDTO, Edificio edificio, Usuario usuario) {
        Unidad unidad = new Unidad();
        unidad.setDpto(Integer.parseInt(unidadDTO.getDpto()));
        unidad.setPiso(Integer.parseInt(unidadDTO.getPiso()));
        unidad.setEdificio(edificio);
        unidad.setHabitante(usuario);
        return unidad;
    }
    
    @GetMapping("/unidad/habitantes/{unidadId}")
    public List<UsuarioDto> getHabitantes(@PathVariable int unidadId){
    	Unidad unidad = unidadService.findById(unidadId);
    	if(unidad == null) {

             return null;
    		
    	}
    	List<Usuario> usuariosUnidad = unidad.getHabitantes();
    	ArrayList<UsuarioDto>usuariosUnidadDtos = new ArrayList<UsuarioDto>();
    	for(Usuario usuario : usuariosUnidad) {
    		usuariosUnidadDtos.add(UsuarioController.getInstance().convertToUsuarioDto(usuario));
    	}
    	String mensaje = "Habitantes de : " + unidadId;
        return usuariosUnidadDtos;
    }
}






