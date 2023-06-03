package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.mapper.file.FileExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.file.FileRowMapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class FileRepository implements CrudRepository<Long, File> {
    private final JdbcTemplate jdbcTemplate;
    private final FileExtractor fileExtractor;
    private final FileRowMapper fileRowMapper;

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
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO file(name, extension, relative_path) VALUE (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            preparedStatement.setString(index++, entity.getName());
            preparedStatement.setString(index++, entity.getExtension());
            preparedStatement.setString(index, entity.getRelativePath());

            return preparedStatement;
        }, keyHolder);


        Long generatedKey = keyHolder.getKey().longValue();

        return read(generatedKey);
    }

    @Override
    public Optional<File> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT id AS 'file_id', name AS 'file_name', extension AS 'file_extension', relative_path AS 'file_relative_path' " +
                                                      "FROM file " +
                                                      "WHERE id = ?",
                fileExtractor, id));
    }

    @Override
    public Optional<File> update(Long id, File entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<File> readAll() {
        return null;
    }

    public List<File> readAllByBookId(Long bookId) {
        return jdbcTemplate.query("SELECT id AS 'file_id', name AS 'file_name', extension AS 'file_extension', relative_path AS 'file_relative_path' " +
                                  "FROM book_file " +
                                  "JOIN file ON file.id=book_file.file_id " +
                                  "WHERE book_id = ?", fileRowMapper, bookId);
    }

    public void bindFileWithBook(File file, Book book) {
        jdbcTemplate.update("INSERT INTO book_file(book_id, file_id) VALUE (?,?)"
                , book.getId(), file.getId());
    }
}
