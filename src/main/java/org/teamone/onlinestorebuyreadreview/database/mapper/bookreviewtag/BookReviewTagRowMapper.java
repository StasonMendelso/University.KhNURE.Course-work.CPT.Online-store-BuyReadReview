package org.teamone.onlinestorebuyreadreview.database.mapper.bookreviewtag;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class BookReviewTagRowMapper implements RowMapper<BookReviewTag> {

    @Override
    public BookReviewTag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return BookReviewTag.builder()
                .id(resultSet.getLong("bookReviewTag_id"))
                .name(resultSet.getString("bookReviewTag_name"))
                .description(resultSet.getString("bookReviewTag_description"))
                .build();
    }
}
