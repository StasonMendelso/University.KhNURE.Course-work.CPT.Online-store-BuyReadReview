package org.teamone.onlinestorebuyreadreview.database.mapper.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
@Component
public class UserExtractor implements ResultSetExtractor<User> {
    private final UserRowMapper userRowMapper;
    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(!resultSet.next()){
            return null;
        }
        return userRowMapper.mapRow(resultSet,resultSet.getRow());
    }
}
