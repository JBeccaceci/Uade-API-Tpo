package uade.tpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uade.tpo.models.dto.MedidaDto;
import uade.tpo.models.dto.NewMedidaDto;
import uade.tpo.models.entity.Medida;
import uade.tpo.models.entity.Reclamo;
import uade.tpo.services.medida.IMedidaService;
import uade.tpo.services.reclamo.IReclamoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MedidaController {
    @Autowired
    private IMedidaService iMedidaService;

    @Autowired
    private IReclamoService iReclamoService;

    @GetMapping("/medidas")
    public ResponseEntity<?> getMedidaReclamo(@RequestParam(name = "reclamoId") String reclamoId) {
        List<Medida> medidaList = iMedidaService
                .getMedidaReclamo(Integer.parseInt(reclamoId));

        List<MedidaDto> medidaDtoList = new ArrayList<>();
        for (Medida medida : medidaList) {
            medidaDtoList.add(new MedidaDto(String.valueOf(medida.getId()), medida.getDescripcion()));
        }
        return new ResponseEntity<>(medidaDtoList, HttpStatus.OK);
    }

    @PostMapping("/medida")
    public ResponseEntity<?> add(@RequestBody NewMedidaDto medidaDto) {
        Reclamo reclamo = iReclamoService.findById(Integer.parseInt(medidaDto.getReclamoId()));
        if (reclamo == null) {
            return new ResponseEntity<>("Reclamo no encontrado", HttpStatus.NOT_FOUND);
        }

        Medida medida = new Medida(medidaDto.getDescripcion());
        medida.setReclamo(reclamo);
        reclamo.addMedida(medida);
        iMedidaService.save(medida);
        System.out.println(" ");
        System.out.println("Se ha creado la medida: "+ medida.getId() + " Correctamente, perteneciente al reclamo " + reclamo.getId());
        return new ResponseEntity<>(new NewMedidaDto(String.valueOf(medida.getId()), medida.getDescripcion(), medidaDto.getReclamoId()), HttpStatus.OK);
    }
    
    @DeleteMapping("/medida/{medidaId}")
    public ResponseEntity<String>deleteMedida( @PathVariable int medidaId){
    	Medida medida = iMedidaService.findById(medidaId);
    	if(medida != null) {
    		String mensaje = "medida no encontrada con ID: " + medidaId;
            return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    	}
    	iMedidaService.deleteById(medidaId);
    	System.out.println(" ");
        System.out.println("Se ha eliminado la medida: "+ medida.getId()+ " Correctamente");
        
        String mensaje = "medida eliminada [MedidaID: " + medidaId + "]";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
    
    
    
    
}
