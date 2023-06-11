package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.mapper.genre.GenreExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.genre.GenreRowMapper;

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
public class GenreRepository implements CrudRepository<Long, Genre> {

    private final JdbcTemplate jdbcTemplate;
    private final GenreExtractor genreExtractor;
    private final GenreRowMapper genreRowMapper;
    @Override
    public Optional<Genre> create(Genre entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Genre> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Genre> update(Long id, Genre entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Genre> readAll() {
        return jdbcTemplate.query("SELECT id AS 'genre_id', name AS 'genre_name' " +
                                  "FROM genre " +
                                  "ORDER BY name", genreRowMapper);
    }

    public List<Genre> readAllByName(List<String> genreNames) {
        return jdbcTemplate.query(connection -> {
            StringBuilder statement = new StringBuilder("SELECT id AS 'genre_id', name AS 'genre_name' " +
                                                        "FROM genre ");
            statement.append("WHERE ");
            for (int i = 0; i < genreNames.size(); i++) {
                String condition = "genre.name=?";
                statement.append(condition);
                if (i != genreNames.size() - 1) {
                    statement.append(" OR ");
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(statement.toString());
            int index = 1;
            for (String genreName : genreNames) {
                preparedStatement.setString(index++, genreName);
            }
            return preparedStatement;
        }, genreRowMapper);
    }

    public List<Genre> create(List<Genre> genresForAdding) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    StringBuilder statement = new StringBuilder("INSERT INTO genre(name) VALUES");
                    for (int i = 0; i < genresForAdding.size(); i++) {
                        statement.append("(?)");
                        if (i != genresForAdding.size() - 1) {
                            statement.append(",");
                        }
                    }
                    PreparedStatement preparedStatement = connection.prepareStatement(statement.toString(), Statement.RETURN_GENERATED_KEYS);
                    int index = 1;
                    for (Genre genre : genresForAdding) {
                        preparedStatement.setString(index++, genre.getName());
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
    public List<Genre> readAllById(List<Long> idList) {
        return jdbcTemplate.query(connection -> {
            StringBuilder statement = new StringBuilder("SELECT id AS 'genre_id', name AS 'genre_name' " +
                                                        "FROM genre ");
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
        }, genreRowMapper);
    }
}
