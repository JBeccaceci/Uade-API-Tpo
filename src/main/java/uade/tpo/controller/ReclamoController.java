package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uade.tpo.models.dto.ReclamoDTO;
import uade.tpo.models.dto.UpdateReclamoDTO;
import uade.tpo.models.entity.*;
import uade.tpo.models.types.EstadoReclamo;
import uade.tpo.models.types.ObjetoReclamo;
import uade.tpo.services.areascomunes.AreaComunService;
import uade.tpo.services.areascomunes.IAreaComunService;
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
    private IAreaComunService areaComunService;


    @PostMapping("/reclamo")
    public ResponseEntity<?> add(@RequestBody ReclamoDTO reclamoDTO){
    	if(reclamoDTO.getUsuario_id() != null) {
    		Usuario usuario = usuarioService.findById(reclamoDTO.getUsuarioId());
    		if(reclamoDTO.getEdificio_id() != null) {
    			Edificio edificio = edificioService.findById(reclamoDTO.getEdificio_id());
    			if(usuario != null && edificio != null) {
    				if(reclamoDTO.getObjetoReclamo() == ObjetoReclamo.AreaComun.name()) {
	    				if(reclamoDTO.getAreaComun_id() != null) {
	    					AreaComun areaComun = areaComunService.findById(reclamoDTO.getAreaComun_id());
	    					if(areaComun != null) {
	    						if(areaComun.getEdificio().getId() == edificio.getId()) {
	    		    				List<Unidad>unidadesEdificio = edificio.getUnidades();
	    		    				for(Unidad unidad : unidadesEdificio) {
	    		    					if(unidad.getPropietario().getId() == usuario.getId() || unidad.getHabitantes().contains(usuario)) {
											if(reclamoDTO.getImagenes() != null){
												List<Imagen> imagenes = new ArrayList<>();
												for (byte[] imagenBytes : reclamoDTO.getImagenes()) {
													Imagen imagen = new Imagen();
													imagen.setImagen(imagenBytes);
													imagenes.add(imagen);
												}
											}
	    		    						Reclamo reclamo = convertToEntity(reclamoDTO, ObjetoReclamo.AreaComun, usuario, edificio, null, areaComun);
											reclamo.setImagenes(imagenes);
	    		    		                reclamoService.save(reclamo);
	    		    		                ReclamoDTO reclamoDTOoutput = convertToDTO(reclamo);
	    		    		                return new ResponseEntity<>(reclamoDTOoutput, HttpStatus.CREATED);
	    		    					}
	    		    					else {
	    		    						String mensaje = "El Usuario no pertenece al edificio: " ;
	    		    	                    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    		    					}
	    		    				}
	    		    			}
	    		    			else {
	    		    				String mensaje = "El Area Comun no pertenece al edificio: " ;
	    		                    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    		    				
	    		    			}
	    						
	    					}
	    					else if(areaComun == null) {
	    						String mensaje = "No se encontro ese Area Comun: " + reclamoDTO.getAreaComun_id() ;
		                        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    					}
	    						
	  
	    				}
	    				else if (reclamoDTO.getAreaComun_id() == null) {
	    					String mensaje = "No fue ingresada el area comun";
	                        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    				}
	    			}
	    			else if(reclamoDTO.getObjetoReclamo() == ObjetoReclamo.Unidad.name()) {
	    				if(reclamoDTO.getUnidad_id() != null) {
	    					Unidad unidad = unidadService.findById(reclamoDTO.getUnidad_id());
	    					if(unidad != null) {
	    						if(unidad.getHabitantes().isEmpty()) {
	    							if(unidad.getPropietario().getId() == reclamoDTO.getUsuarioId()) {
	    								Reclamo reclamo = convertToEntity(reclamoDTO, ObjetoReclamo.Unidad, usuario, edificio, unidad, null);
    		    		                reclamoService.save(reclamo);
    		    		                ReclamoDTO reclamoDTOoutput = convertToDTO(reclamo);
    		    		                return new ResponseEntity<>(reclamoDTOoutput, HttpStatus.CREATED);
	    							}
	    							else if(unidad.getPropietario().getId() != reclamoDTO.getUsuarioId()) {
	    								String mensaje = "El usuario: "+reclamoDTO.getUsuarioId() +" no tiene relacion con la unidad: "+ reclamoDTO.getAreaComun_id();
	    		                        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    							}
	    						}
	    						else {
	    							if(unidad.getHabitantes().contains(usuario)) {
	    								Reclamo reclamo = convertToEntity(reclamoDTO, ObjetoReclamo.Unidad, usuario, edificio, unidad, null);
    		    		                reclamoService.save(reclamo);
    		    		                ReclamoDTO reclamoDTOoutput = convertToDTO(reclamo);
    		    		                return new ResponseEntity<>(reclamoDTOoutput, HttpStatus.CREATED);
	    							}
	    							else {
	    								String mensaje = "El usuario: "+reclamoDTO.getUsuarioId() +" no es habitante de la unidad: "+ reclamoDTO.getAreaComun_id();
	    		                        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    							}
	    							
	    						}
	    						
	    					}
	    					else if(unidad == null) {
	    						String mensaje = "La unidad no existe: "+ reclamoDTO.getUnidad_id();
		                        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    					}
	    				}
	    				else if(reclamoDTO.getUnidad_id() == null) {
	    					String mensaje = "No fue ingresada la unidad: "+ reclamoDTO.getUnidad_id();
	                        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
	    				}
	    				
	    			}
    		
    			}
    			else if(usuario == null || edificio == null) {
    				String mensaje = "Uno de los datos no existe";
                    return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    			}
    			
	    			
    		}
    		else {
    			 String mensaje = "El edificio no fue ingresado";
                 return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    		}
    	}
    	
    	
    	
    	String mensaje = "Ocurrio un error";
        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }
    

    @GetMapping("/reclamo")
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
		if(reclamo.getImagenes() != null){
			for (byte[] imagenBytes : reclamo.getImagenes()) {
				Imagen imagen = new Imagen();
				imagen.setImagen(imagenBytes);
				imagenes.add(imagen);
			}
		}
		if(updateReclamoDTO.getImagenes() != null){
			for (byte[] imagenBytes : reclamo.getImagenes()) {
				Imagen imagen = new Imagen();
				imagen.setImagen(imagenBytes);
				imagenes.add(imagen);
			}
		}
		
		reclamo.setImagenes(imagenes);
        reclamo.setEstadoReclamo(updateReclamoDTO.getEstadoReclamo());
        reclamo.setMedidas(medidaList);
        reclamoService.update(reclamo.getId(), reclamo);
        return new ResponseEntity<>(updateReclamoDTO, HttpStatus.OK);
    }

    private ReclamoDTO convertToDTO(Reclamo reclamo) {
        return new ReclamoDTO(
                 reclamo.getTipoReclamo(),
                reclamo.getDescripcion(), reclamo.getUsuario().getId(),reclamo.getObjetoReclamo().name(),
                reclamo.getUnidad().getId(), reclamo.getEdificio().getId(),reclamo.getAreaComun().getId() , reclamo.getEstadoReclamo());
    }

    private Reclamo convertToEntity(ReclamoDTO reclamoDTO,ObjetoReclamo objetoReclamo ,Usuario usuario, Edificio edificio, Unidad unidad, AreaComun areaComun) {
        return new Reclamo(reclamoDTO.getTipoReclamo(), reclamoDTO.getDescripcion(), usuario, objetoReclamo, unidad, areaComun, edificio);
    }
}
