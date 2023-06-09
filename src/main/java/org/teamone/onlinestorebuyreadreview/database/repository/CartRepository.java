package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.CartItem;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.mapper.cart.CartExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.BatchPreparedStatementSetterWithBatchSize;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class CartRepository implements CrudRepository<Long, Cart> {
    private final JdbcTemplate jdbcTemplate;
    private final CartExtractor cartExtractor;

    @Override
    public Optional<Cart> create(Cart entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cart(id) VALUE (?)", Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            preparedStatement.setLong(index, entity.getId());
            return preparedStatement;
        }, keyHolder);
        return read(keyHolder.getKey().longValue());
    }

    @Override
    public Optional<Cart> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(new PrepareStatementCreatorWithScrolledResultSet("SELECT cart.id AS 'cart_id', " +
                                                                                                       "cart_item.quantity AS 'cart_item_quantity'," +
                                                                                                       "book.id AS 'book_id', title AS 'book_title', price AS 'book_price', article AS 'book_article',  " +
                                                                                                       "book_file.file_id AS 'file_id' " +
                                                                                                       "FROM cart " +
                                                                                                       "JOIN cart_item ON cart.id = cart_item.cart_id " +
                                                                                                       "JOIN book ON book.id = cart_item.book_id " +
                                                                                                       "LEFT JOIN book_file ON book.id = book_file.book_id " +
                                                                                                       "WHERE cart.id = ? " +
                                                                                                       "ORDER BY cart_item.book_id"),
                preparedStatement -> preparedStatement.setLong(1, id),
                cartExtractor)).or(() -> Optional.of(Cart.builder().id(id).build()));

    }

    @Override
    public Optional<Cart> update(Long id, Cart entity) {
        jdbcTemplate.update("DELETE FROM cart_item WHERE cart_id=?", entity.getId());
        jdbcTemplate.batchUpdate("INSERT INTO cart_item(cart_id, book_id,quantity) VALUE(?,?,?)", new BatchPreparedStatementSetterWithBatchSize(entity.getCartItems().size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++, id);
                preparedStatement.setLong(index++, entity.getCartItems().get(i).getBook().getId());
                preparedStatement.setInt(index, entity.getCartItems().get(i).getQuantity());
            }
        });
        return read(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM cart_item WHERE cart_id=?", id);
    }

    @Override
    public List<Cart> readAll() {
        throw new UnsupportedOperationException();
    }

    public List<CartItem> readLastData(List<CartItem> cartItems) {
        List<CartItem> updatedItems = cartItems;
        for (int i = 0; i < updatedItems.size(); i++) {
            CartItem currentCartItem = updatedItems.get(i);
            Long bookId = currentCartItem.getBook().getId();
            CartItem updatedCartItem = jdbcTemplate.query("SELECT book.id AS 'book_id', title AS 'book_title', price AS 'book_price', article AS 'book_article', " +
                                                            "book_file.file_id AS 'file_id' " +
                                                            "FROM book " +
                                                            "LEFT JOIN book_file ON book.id = book_file.book_id " +
                                                            "WHERE book.id=? ",ps -> ps.setLong(1,bookId), (resultSet) -> {
                if(!resultSet.next()){
                    return null;
                }
                return CartItem.builder()
                        .book(Book.builder()
                                .id(resultSet.getLong("book_id"))
                                .title(resultSet.getString("book_title"))
                                .price(resultSet.getBigDecimal("book_price"))
                                .article(resultSet.getString("book_article"))
                                .files(List.of(File.builder()
                                                .id(resultSet.getLong("file_id"))
                                        .build()))
                                .build())
                        .build();
            });
            if(updatedCartItem==null){
                updatedItems.remove(i);
                i--;
            }else {
                updatedCartItem.setQuantity(currentCartItem.getQuantity());
                updatedItems.set(i,updatedCartItem);
            }

        }

        return updatedItems;
    }
}
