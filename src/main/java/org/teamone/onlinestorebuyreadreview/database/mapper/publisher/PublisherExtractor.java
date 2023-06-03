package org.teamone.onlinestorebuyreadreview.database.mapper.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class PublisherExtractor implements ResultSetExtractor<Publisher> {
    private final PublisherRowMapper publisherRowMapper;

    @Override
    public Publisher extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(!resultSet.next()){
            return null;
        }
        return  publisherRowMapper.mapRow(resultSet,resultSet.getRow());
    }
}
