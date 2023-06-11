package org.teamone.onlinestorebuyreadreview.database.mapper.cart;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.CartItem;
import org.teamone.onlinestorebuyreadreview.database.entity.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Stanislav Hlova
 */
@Component
public class CartExtractor implements ResultSetExtractor<Cart> {

    @Override
    public Cart extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }
        Cart cart = Cart.builder()
                .id(resultSet.getLong("cart_id"))
                .build();
        Set<CartItem> cartItemSet = new HashSet<>();

        do {
            List<File> files = new ArrayList<>();
            Book book = Book.builder()
                    .id(resultSet.getLong("book_id"))
                    .title(resultSet.getString("book_title"))
                    .price(resultSet.getBigDecimal("book_price"))
                    .article(resultSet.getString("book_article"))
                    .build();
            do {
                files.add(File.builder().id(resultSet.getLong("file_id")).build());
                if (!resultSet.next()) {
                    break;
                }
            } while (book.getId().equals(resultSet.getLong("book_id")));
            resultSet.previous();
            book.setFiles(files);
            cartItemSet.add(CartItem.builder()
                    .book(book)
                    .cartId(resultSet.getLong("cart_id"))
                    .quantity(resultSet.getInt("cart_item_quantity"))
                    .build());

        } while (resultSet.next());

        cart.setCartItems(cartItemSet
                .stream()
                .sorted(Comparator.comparing(o -> o.getBook().getId()))
                .collect(Collectors.toCollection(ArrayList::new)));

        return cart;
    }
}
