package uade.tpo.services.inquilino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uade.tpo.dao.DaoInquilinoImpl;
import uade.tpo.models.entity.Inquilino;

import java.util.List;

@Service
public class InquilinoService implements IInquilinoService {
    @Autowired
    private DaoInquilinoImpl daoInquilino;

    public InquilinoService() {
    }

    @Override
    public List<Inquilino> findAll() {
        return daoInquilino.getAll();
    }

    @Override
    public Inquilino findById(int id) {
        return daoInquilino.findById(id);
    }

    @Override
    public void save(Inquilino inquilino) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(inquilino.getPassword());

        inquilino.setPassword(encodedPassword);

        this.daoInquilino.save(inquilino);
    }

    @Override
    public void update(int id, Inquilino inquilino) {

    }

    @Override
    public void deleteById(int id) {
        daoInquilino.delete(id);
    }
}
