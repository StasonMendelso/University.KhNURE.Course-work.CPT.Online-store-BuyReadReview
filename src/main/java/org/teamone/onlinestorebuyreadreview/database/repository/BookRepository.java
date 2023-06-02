package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.ReadBookExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.ReadBooksExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class BookRepository implements CrudRepository<Long, Book> {
    private final JdbcTemplate jdbcTemplate;

    private final ReadBookExtractor readBookExtractor;
    private final ReadBooksExtractor readBooksExtractor;

    @Override
    public Optional<Book> create(Book entity) {
        return Optional.empty();
    }

    public Optional<Book> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT book.id AS 'book_id', isbn, paper_quantity, title, description, price,hidden, quantity, article, " +
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
                readBookExtractor));
    }

    @Override
    public Optional<Book> update(Long id, Book entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    public List<Book> readAll() {
        return jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT book.id AS 'book_id', isbn, paper_quantity, title, description, price,hidden, quantity, article, " +
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
