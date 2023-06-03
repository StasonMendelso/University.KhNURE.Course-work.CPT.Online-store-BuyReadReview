package org.teamone.onlinestorebuyreadreview.database.mapper.author;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Author.builder()
                .id(resultSet.getLong("author_id"))
                .firstName(resultSet.getString("author_first_name"))
                .lastName(resultSet.getString("author_last_name"))
                .pseudonym(resultSet.getString("author_pseudonym"))
                .build();
    }
}
