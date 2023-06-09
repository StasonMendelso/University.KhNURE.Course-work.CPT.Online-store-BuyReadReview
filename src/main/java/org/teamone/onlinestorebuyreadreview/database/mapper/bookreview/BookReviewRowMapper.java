package org.teamone.onlinestorebuyreadreview.database.mapper.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.BookRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class BookReviewRowMapper implements RowMapper<BookReview> {
    private final BookRowMapper bookRowMapper;

    @Override
    public BookReview mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return BookReview.builder()
                .id(resultSet.getLong("bookReview_id"))
                .clientId(resultSet.getLong("bookReview_client_id"))
                .clientPseudonym(resultSet.getString("bookReview_client_pseudonym"))
                .content(resultSet.getString("bookReview_content"))
                .rating(resultSet.getFloat("bookReview_rating"))
                .book(bookRowMapper.mapRow(resultSet, 1))
                .build();
    }

}