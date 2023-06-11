package org.teamone.onlinestorebuyreadreview.database.mapper.publisher;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class PublisherRowMapper implements RowMapper<Publisher> {
    @Override
    public Publisher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Publisher.builder()
                .id(resultSet.getLong("publisher_id"))
                .name(resultSet.getString("publisher_name"))
                .build();
    }
}
