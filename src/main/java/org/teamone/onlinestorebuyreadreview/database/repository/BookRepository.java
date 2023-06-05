package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.BookExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.ReadBooksExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.BatchPreparedStatementSetterWithBatchSize;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.BookInsertStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class BookRepository implements CrudRepository<Long, Book> {
    private final JdbcTemplate jdbcTemplate;

    private final BookExtractor bookExtractor;
    private final ReadBooksExtractor readBooksExtractor;

    @Override
    public Optional<Book> create(Book entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book(paper_quantity, title, description, isbn, hidden, price, quantity, article, publisher_id) VALUE (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            new BookInsertStatementSetter(entity).setValues(preparedStatement);
            return preparedStatement;
        }, keyHolder);
        Long generatedKey = keyHolder.getKey().longValue();
        jdbcTemplate.batchUpdate("INSERT INTO book_genre(book_id, genre_id) VALUE (?,?)", new BatchPreparedStatementSetterWithBatchSize(entity.getGenres().size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++,generatedKey);
                preparedStatement.setLong(index,entity.getGenres().get(i).getId());
            }

        });
        jdbcTemplate.batchUpdate("INSERT INTO author_book(book_id, author_id) VALUE (?,?)", new BatchPreparedStatementSetterWithBatchSize(entity.getAuthors().size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++,generatedKey);
                preparedStatement.setLong(index,entity.getAuthors().get(i).getId());
            }
        });


        return read(generatedKey);
    }

    public Optional<Book> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT book.id AS 'book_id', isbn AS 'book_isbn', paper_quantity AS 'book_paper_quantity', title AS 'book_title', description AS 'book_description', price AS 'book_price', hidden AS 'book_hidden', quantity AS 'book_quantity', article AS 'book_article', " +
                                                                 "publisher.id AS 'publisher_id', publisher.name AS 'publisher_name', " +
                                                                 "genre.id AS 'genre_id', genre.`name` AS 'genre_name'," +
                                                                 "author.id AS 'author_id', author.first_name AS 'author_first_name', author.last_name AS 'author_last_name', author.pseudonym AS 'author_pseudonym', " +
                                                                 "file.id AS 'file_id', file.name AS 'file_name', file.extension AS 'file_extension', file.relative_path AS 'file_relative_path' " +
                                                                 "FROM book " +
                                                                 "LEFT JOIN publisher ON book.publisher_id = publisher.id " +
                                                                 "LEFT JOIN book_genre ON book.id = book_genre.book_id " +
                                                                 "LEFT JOIN genre ON book_genre.genre_id = genre.id " +
                                                                 "LEFT JOIN author_book ON book.id = author_book.book_id " +
                                                                 "LEFT JOIN author ON author_book.author_id = author.id " +
                                                                 "LEFT JOIN book_file ON book.id = book_file.book_id " +
                                                                 "LEFT JOIN file ON book_file.file_id = file.id " +
                                                                 "WHERE book.id = ?"),
                preparedStatement -> preparedStatement.setLong(1, id),
                bookExtractor));
    }

    @Override
    public Optional<Book> update(Long id, Book entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Book> readAll() {
        return jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT book.id AS 'book_id', isbn AS 'book_isbn', paper_quantity AS 'book_paper_quantity', title AS 'book_title', description AS 'book_description', price AS 'book_price', hidden AS 'book_hidden', quantity AS 'book_quantity', article AS 'book_article', " +
                                                                 "publisher.id AS 'publisher_id', publisher.name AS 'publisher_name', " +
                                                                 "genre.id AS 'genre_id', genre.`name` AS 'genre_name'," +
                                                                 "author.id AS 'author_id', author.first_name AS 'author_first_name', author.last_name AS 'author_last_name', author.pseudonym AS 'author_pseudonym', " +
                                                                 "file.id AS 'file_id', file.name AS 'file_name', file.extension AS 'file_extension', file.relative_path AS 'file_relative_path' " +
                                                                 "FROM book " +
                                                                 "LEFT JOIN publisher ON book.publisher_id = publisher.id " +
                                                                 "LEFT JOIN book_genre ON book.id = book_genre.book_id " +
                                                                 "LEFT JOIN genre ON book_genre.genre_id = genre.id " +
                                                                 "LEFT JOIN author_book ON book.id = author_book.book_id " +
                                                                 "LEFT JOIN author ON author_book.author_id = author.id " +
                                                                 "LEFT JOIN book_file ON book.id = book_file.book_id " +
                                                                 "LEFT JOIN file ON book_file.file_id = file.id"),
                readBooksExtractor);

    }
}
