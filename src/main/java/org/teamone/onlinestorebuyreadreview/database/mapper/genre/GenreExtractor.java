package org.teamone.onlinestorebuyreadreview.database.mapper.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class GenreExtractor implements ResultSetExtractor<Genre> {
    private final GenreRowMapper genreRowMapper;
    @Override
    public Genre extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(!resultSet.next()){
            return null;
        }
        return  genreRowMapper.mapRow(resultSet,resultSet.getRow());
    }
}
