package UTN.FRC.sistemas.TPI.repository;

import java.util.List;

public interface IService<T,ID> {
    void create(T entity);
    T findById(ID id);
    List<T> getAll();
    T update(T entity);
    void delete(ID id);
    boolean existsById(ID id);

}
