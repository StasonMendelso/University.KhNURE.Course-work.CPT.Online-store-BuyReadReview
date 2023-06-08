package org.teamone.onlinestorebuyreadreview.database.mapper.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class ReadBookReviewsExtractor implements ResultSetExtractor<List<BookReview>>  {
    private final BookReviewExtractor bookReviewExtractor;

    @Override
    public List<BookReview> extractData(ResultSet resultSet) throws SQLException, DataAccessException{
        if (!resultSet.isBeforeFirst()){
            return Collections.emptyList();
        }
        List<BookReview> bookReviews = new ArrayList<>();
        do {
            bookReviews.add(bookReviewExtractor.extractData(resultSet));
        } while (!resultSet.isAfterLast());
        return bookReviews;
    }
}
