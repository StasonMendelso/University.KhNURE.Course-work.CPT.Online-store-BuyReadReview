package org.teamone.onlinestorebuyreadreview.database.repository;

import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
public class CartRepository implements CrudRepository<Long, Cart>{
    @Override
    public Optional<Cart> create(Cart entity) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Optional<Cart> read(Long id) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Optional<Cart> update(Long id, Cart entity) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<Cart> readAll() {
        throw new UnsupportedOperationException();
    }
}
