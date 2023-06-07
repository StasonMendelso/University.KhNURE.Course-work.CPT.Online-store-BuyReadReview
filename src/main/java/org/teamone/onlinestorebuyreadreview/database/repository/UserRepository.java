package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.User;
import org.teamone.onlinestorebuyreadreview.database.mapper.user.UserExtractor;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class UserRepository implements CrudRepository<Long, User> {

    private final JdbcTemplate jdbcTemplate;
    private final UserExtractor userExtractor;

    @Override
    public Optional<User> create(User entity) {
        return Optional.empty();
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long id, User entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT user.id AS 'user_id', user.username AS 'user_username', user.password AS 'user_password', user.first_name AS 'user_first_name', user.middle_name AS 'user_middle_name', user.last_name AS 'user_last_name', user.telephone_number AS 'user_telephone_number', user.invalid AS 'user_invalid', user.blocked AS 'user_blocked',user.created_at AS 'user_created_at'," +
                                                      "role.name AS 'role_name'," +
                                                      "client.bonuses AS 'client_bonuses' " +
                                                      "FROM user " +
                                                      "LEFT JOIN role ON role.id = user.role_id " +
                                                      "LEFT JOIN client ON user.id = client.id " +
                                                      "WHERE user.username = ?", preparedStatement -> {
            int index = 1;
            preparedStatement.setString(index, username);
        }, userExtractor));
    }
}
