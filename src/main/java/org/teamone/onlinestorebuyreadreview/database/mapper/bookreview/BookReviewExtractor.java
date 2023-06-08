package org.teamone.onlinestorebuyreadreview.database.mapper.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.database.mapper.bookreviewtag.BookReviewTagRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class BookReviewExtractor implements ResultSetExtractor<BookReview> {
    private final BookReviewTagRowMapper bookReviewTagRowMapper;
    private final BookReviewRowMapper bookReviewRowMapper;

    @Override
    public BookReview extractData(ResultSet resultSet) throws SQLException, DataAccessException{
        if(!resultSet.next()){
            return null;
        }

        BookReview bookReview = bookReviewRowMapper.mapRow(resultSet, resultSet.getRow());
        Set<BookReviewTag> bookReviewTags = new HashSet<>();
        do {
            if(!bookReview.getId().equals(resultSet.getLong("bookReview_id"))){
                resultSet.previous();
                break;
            }
            BookReviewTag bookReviewTag = bookReviewTagRowMapper.mapRow(resultSet, resultSet.getRow());
            bookReviewTags.add(bookReviewTag);
        }while (resultSet.next());

        bookReview.setTags(bookReviewTags.stream().toList());
        return bookReview;
    }
}
