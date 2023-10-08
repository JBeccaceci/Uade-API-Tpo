package uade.tpo.dao.definition;

public interface IDaoUsuario<T> extends DAO<T> {
    T findByUserAndPassword(String username, String password);
    T findByUsername(String username);
}
