package br.com.vacine_se;

import java.util.Collection;
import java.util.Optional;

public interface InMemoryRepository<T> {
    Optional<T> getById(int id);
    Collection<T> getAll();
    T save (T entity);
    T update(T entity);
    void delete(T entity);
}
