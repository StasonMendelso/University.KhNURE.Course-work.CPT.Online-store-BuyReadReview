package org.teamone.onlinestorebuyreadreview.database.mapper.bookreviewtag;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class BookReviewTagExtractor implements ResultSetExtractor<BookReviewTag>{
    private final BookReviewTagRowMapper bookReviewTagRowMapper;

    @Override
    public BookReviewTag extractData(ResultSet resultSet) throws SQLException, DataAccessException{
        if(!resultSet.next()){
            return null;
        }
        return bookReviewTagRowMapper.mapRow(resultSet,resultSet.getRow());
    }
}