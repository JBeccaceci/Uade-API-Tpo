package uade.tpo.services.Imagen;

import uade.tpo.models.entity.Imagen;

public interface IImagenService {
	public Imagen findById(Long id);

	public void save(Imagen imagen);
}
