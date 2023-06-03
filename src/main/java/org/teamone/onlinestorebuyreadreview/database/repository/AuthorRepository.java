package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.mapper.author.AuthorExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.author.AuthorRowMapper;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class AuthorRepository implements CrudRepository<Long, Author> {

    private final JdbcTemplate jdbcTemplate;
    private final AuthorExtractor authorExtractor;
    private final AuthorRowMapper authorRowMapper;

    @Override
    public Optional<Author> create(Author entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> update(Long id, Author entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Author> readAll() {
        return jdbcTemplate.query("SELECT id AS 'author_id',first_name AS 'author_first_name', last_name AS 'author_last_name', pseudonym AS 'author_pseudonym' " +
                                  "FROM author " +
                                  "ORDER BY last_name", authorRowMapper);
    }

    public List<Author> readAllByFullName(List<String> authorFullNames) {
        return jdbcTemplate.query(connection -> {
            StringBuilder statement = new StringBuilder("SELECT id AS 'author_id',first_name AS 'author_first_name', last_name AS 'author_last_name', pseudonym AS 'author_pseudonym' " +
                                                        "FROM author ");
            statement.append("WHERE ");
            for (int i = 0; i < authorFullNames.size(); i++) {
                String condition = "(author.last_name=? AND author.first_name=?)";
                statement.append(condition);
                if (i != authorFullNames.size() - 1) {
                    statement.append(" OR ");
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(statement.toString());
            int index = 1;
            for (String fullName : authorFullNames) {
                String firstName = fullName.split(" ")[0];
                String lastName = fullName.split(" ")[1];
                preparedStatement.setString(index++, firstName);
                preparedStatement.setString(index++, lastName);
            }
            return preparedStatement;
        }, authorRowMapper);
    }

    public List<Author> create(List<Author> authorsForAdding) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    StringBuilder statement = new StringBuilder("INSERT INTO author(first_name, last_name) VALUES");
                    for (int i = 0; i < authorsForAdding.size(); i++) {
                        statement.append("(?,?)");
                        if (i != authorsForAdding.size() - 1) {
                            statement.append(",");
                        }
                    }
                    PreparedStatement preparedStatement = connection.prepareStatement(statement.toString(), Statement.RETURN_GENERATED_KEYS);
                    int index = 1;
                    for (Author author : authorsForAdding) {
                        preparedStatement.setString(index++, author.getFirstName());
                        preparedStatement.setString(index++, author.getLastName());
                    }
                    return preparedStatement;
                }
                , keyHolder);
        List<Long> generatedKeys = keyHolder.getKeyList()
                .stream()
                .flatMap(stringObjectMap -> stringObjectMap.values().stream())
                .map(object -> ((BigInteger) object).longValue())
                .toList();
        return readAllById(generatedKeys);
    }

    public List<Author> readAllById(List<Long> idList) {
        return jdbcTemplate.query(connection -> {
            StringBuilder statement = new StringBuilder("SELECT id AS 'author_id',first_name AS 'author_first_name', last_name AS 'author_last_name', pseudonym AS 'author_pseudonym' " +
                                                        "FROM author ");
            statement.append("WHERE id IN(");
            for (int i = 0; i < idList.size(); i++) {
                statement.append("?");
                if (i != idList.size() - 1) {
                    statement.append(",");
                }
            }
            statement.append(")");
            PreparedStatement preparedStatement = connection.prepareStatement(statement.toString());

            int index = 1;
            for (Long id : idList) {
                preparedStatement.setLong(index++, id);
            }

            return preparedStatement;
        }, authorRowMapper);
    }
}
