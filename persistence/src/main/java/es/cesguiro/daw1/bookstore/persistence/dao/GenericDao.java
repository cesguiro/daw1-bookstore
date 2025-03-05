package es.cesguiro.daw1.bookstore.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID> {

    List<T> findAll(int page, int size);
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteById(ID id);
    Long count();

}
