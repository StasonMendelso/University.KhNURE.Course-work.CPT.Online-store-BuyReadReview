package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.File;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class FileRepository implements CrudRepository<Long, File>{
    private final JdbcTemplate jdbcTemplate;


    public Optional<String> readFileRelativePathById(Long id) {
        return jdbcTemplate.query("SELECT relative_path " +
                                  "FROM file " +
                                  "WHERE id = ?",
                new Object[]{id}, resultSet -> {
                    if (resultSet.next()) {
                        return Optional.ofNullable(resultSet.getString("relative_path"));
                    } else {
                        return Optional.empty();
                    }
                });
    }

    @Override
    public Optional<File> create(File entity) {
        return Optional.empty();
    }

    @Override
    public Optional<File> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<File> update(Long id, File entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
