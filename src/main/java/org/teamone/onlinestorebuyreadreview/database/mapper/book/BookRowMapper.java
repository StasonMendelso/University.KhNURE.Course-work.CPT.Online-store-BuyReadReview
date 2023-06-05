package org.teamone.onlinestorebuyreadreview.database.mapper.book;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.mapper.publisher.PublisherRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class BookRowMapper implements RowMapper<Book> {
    private final PublisherRowMapper publisherRowMapper;

    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Book.builder()
                .id(resultSet.getLong("book_id"))
                .title(resultSet.getString("book_title"))
                .article(resultSet.getString("book_article"))
                .description(resultSet.getString("book_description"))
                .hidden(resultSet.getBoolean("book_hidden"))
                .isbn(resultSet.getString("book_isbn"))
                .quantity(resultSet.getInt("book_quantity"))
                .paperQuantity(resultSet.getInt("book_paper_quantity"))
                .price(resultSet.getBigDecimal("book_price"))
                .publisher(publisherRowMapper.mapRow(resultSet, 1))
                .build();
    }
}
