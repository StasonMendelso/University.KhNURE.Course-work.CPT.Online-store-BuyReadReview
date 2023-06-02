package org.teamone.onlinestorebuyreadreview.database.repository;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
public interface CrudRepository<I,V> {
    Optional<V> create(V entity);
    Optional<V> read(I id);
    Optional<V> update(I id, V entity);
    void delete(I id);
}
