package org.teamone.onlinestorebuyreadreview.database.mapper.user;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Client;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;
import org.teamone.onlinestorebuyreadreview.database.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Role role = Role.getInstance(resultSet.getString("role_name"));
        if(role.equals(Role.CLIENT)){
            return Client.builder()
                    .id(resultSet.getLong("user_id"))
                    .username(resultSet.getString("user_username"))
                    .firstName(resultSet.getString("user_first_name"))
                    .middleName(resultSet.getString("user_middle_name"))
                    .lastName(resultSet.getString("user_last_name"))
                    .role(role)
                    .password(resultSet.getString("user_password"))
                    .invalid(resultSet.getBoolean("user_invalid"))
                    .blocked(resultSet.getBoolean("user_blocked"))
                    .createdAt(resultSet.getTimestamp("user_created_at").toLocalDateTime())
                    .telephoneNumber(resultSet.getString("user_telephone_number"))
                    .bonuses(resultSet.getInt("client_bonuses"))
                    .build();
        }else {
            return User.builder()
                    .id(resultSet.getLong("user_id"))
                    .username(resultSet.getString("user_username"))
                    .firstName(resultSet.getString("user_first_name"))
                    .middleName(resultSet.getString("user_middle_name"))
                    .lastName(resultSet.getString("user_last_name"))
                    .role(role)
                    .password(resultSet.getString("user_password"))
                    .invalid(resultSet.getBoolean("user_invalid"))
                    .blocked(resultSet.getBoolean("user_blocked"))
                    .createdAt(resultSet.getTimestamp("user_created_at").toLocalDateTime())
                    .telephoneNumber(resultSet.getString("user_telephone_number"))
                    .build();
        }

    }
}
