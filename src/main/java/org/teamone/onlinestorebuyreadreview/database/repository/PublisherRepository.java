package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;
import org.teamone.onlinestorebuyreadreview.database.mapper.publisher.PublisherExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.publisher.PublisherRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class PublisherRepository implements CrudRepository<Long, Publisher> {
    private final JdbcTemplate jdbcTemplate;
    private final PublisherExtractor publisherExtractor;
    private final PublisherRowMapper publisherRowMapper;

    @Override
    public Optional<Publisher> create(Publisher entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  publisher(name) VALUE (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            return preparedStatement;
        }, keyHolder);

        return read(keyHolder.getKey().longValue());
    }

    @Override
    public Optional<Publisher> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT id AS 'publisher_id', name AS 'publisher_name' " +
                                                      "FROM publisher " +
                                                      "WHERE id = ?", publisherExtractor, id));
    }

    @Override
    public Optional<Publisher> update(Long id, Publisher entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Publisher> readAll() {
        return jdbcTemplate.query("SELECT id AS 'publisher_id', name AS 'publisher_name' " +
                                  "FROM publisher " +
                                  "ORDER BY name", publisherRowMapper);
    }

    public Optional<Publisher> readByName(String name) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT id AS 'publisher_id', name AS 'publisher_name' " +
                                                      "FROM publisher " +
                                                      "WHERE name = ?", publisherExtractor, name));
    }
}
