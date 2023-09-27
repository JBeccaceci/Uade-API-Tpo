package uade.tpo.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uade.tpo.models.Edificio;
import uade.tpo.models.Usuario;

@Service
public class UsuarioService implements IUsuarioService {
	@Autowired
	private DaoUsuarioImpl daoUsuarioImpl;
	
	public UsuarioService() {
		
	}
	
	  public List<Usuario> findAll() {
	        return daoUsuarioImpl.getAll();
	    }

	    @Override
	    public Usuario findById(int id) {
	        return daoUsuarioImpl.findById(id);
	    }

	    @Override
	    public void save(Usuario usuario) {
	        this.daoUsuarioImpl.save(usuario);
	    }

	    @Override
	    public void update(int id, Usuario cliente) {

	    }

	    @Override
	    public void deleteById(int id) {
	    	daoUsuarioImpl.delete(id);
	    }
	

}
