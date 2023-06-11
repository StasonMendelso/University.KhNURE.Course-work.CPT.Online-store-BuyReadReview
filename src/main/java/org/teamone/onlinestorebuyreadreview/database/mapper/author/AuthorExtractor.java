package org.teamone.onlinestorebuyreadreview.database.mapper.author;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class AuthorExtractor implements ResultSetExtractor<Author> {
    private final AuthorRowMapper authorRowMapper;
    @Override
    public Author extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(!resultSet.next()){
            return null;
        }
        return authorRowMapper.mapRow(resultSet, resultSet.getRow());
    }
}
