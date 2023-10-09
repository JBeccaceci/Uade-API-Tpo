package uade.tpo.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import uade.tpo.models.dto.ImagenOutputDTO;
import uade.tpo.models.entity.Imagen;
import uade.tpo.services.Imagen.ImagenServiceImpl;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {
	@Autowired
	private ImagenServiceImpl imagenService;

	@PostMapping("/subir") // TODO: Finalizado OK
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo) {
		try {
			Imagen imagen = new Imagen();
			imagen.setImagen(archivo.getBytes());
			Imagen newImage = imagenService.save(imagen);
			return ResponseEntity.ok(new ImagenOutputDTO(newImage.getId()));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen.");
		}
	}

	@GetMapping("/{id}") // TODO: Finalizado OK
	public ResponseEntity<byte[]> download(@PathVariable Long id) {
		Imagen imagen = imagenService.findById(id);
		if (imagen != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen.getImagen());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
