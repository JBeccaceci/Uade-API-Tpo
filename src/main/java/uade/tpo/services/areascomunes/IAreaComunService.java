package uade.tpo.services.areascomunes;

import java.util.List;

import uade.tpo.models.entity.AreaComun;

public interface IAreaComunService {
	  public List<AreaComun> findAll();

	    public AreaComun findById(int id);

	    public void save(AreaComun cliente);

	    public void update(int id, AreaComun cliente);

	    public void deleteById(int id);

		public List<AreaComun> findByEdificioId(int edificioId);

}
