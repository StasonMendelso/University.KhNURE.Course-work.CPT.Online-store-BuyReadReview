package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.BookExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.BookRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.ReadBooksExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.BatchPreparedStatementSetterWithBatchSize;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.book.BookInsertStatementSetter;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.book.BookUpdateStatementSetter;

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
    public Optional<Book> create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book(paper_quantity, title, description, isbn, hidden, price, quantity, article, publisher_id) VALUE (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            new BookInsertStatementSetter(book).setValues(preparedStatement);
            return preparedStatement;
        }, keyHolder);
        Long generatedKey = keyHolder.getKey().longValue();
        insertIntoBookGenre(generatedKey, book.getGenres());
        insertIntoAuthorBook(generatedKey, book.getAuthors());


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
    public Optional<Book> update(Long id, Book book) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book " +
                    "SET paper_quantity = ?, title = ?, description = ?, isbn = ?, hidden = ?, price = ?, quantity = ?, article = ?, publisher_id = ? " +
                    "WHERE book.id = ?");

            new BookUpdateStatementSetter(id, book).setValues(preparedStatement);
            return preparedStatement;
        });
        jdbcTemplate.update("DELETE FROM book_genre WHERE book_id = ? ", id);
        jdbcTemplate.update("DELETE FROM author_book WHERE book_id = ? ", id);
        insertIntoBookGenre(id, book.getGenres());
        insertIntoAuthorBook(id, book.getAuthors());


        return read(id);
    }

    private void insertIntoAuthorBook(Long bookId, List<Author> authors) {
        jdbcTemplate.batchUpdate("INSERT INTO author_book(book_id, author_id) VALUE (?,?)", new BatchPreparedStatementSetterWithBatchSize(authors.size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++, bookId);
                preparedStatement.setLong(index, authors.get(i).getId());
            }
        });
    }

    private void insertIntoBookGenre(Long bookId, List<Genre> genres) {
        jdbcTemplate.batchUpdate("INSERT INTO book_genre(book_id, genre_id) VALUE (?,?)", new BatchPreparedStatementSetterWithBatchSize(genres.size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++, bookId);
                preparedStatement.setLong(index, genres.get(i).getId());
            }

        });
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
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

    public List<String> readAllIsbn() {
        return jdbcTemplate.query("SELECT book.isbn AS 'book_isbn' " +
                "FROM book", (resultSet, rowNum) -> resultSet.getString("book_isbn"));
    }

    public List<String> readAllArticle() {
        return jdbcTemplate.query("SELECT book.article AS 'book_article' " +
                "FROM book", (resultSet, rowNum) -> resultSet.getString("book_article"));
    }

    public Optional<String> readIsbnByBookId(Long bookId) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT book.isbn AS 'book_isbn' " +
                "FROM book " +
                "WHERE book.id = ?", resultSet -> {
            if (resultSet.next()) {
                return resultSet.getString("book_isbn");
            }
            return null;
        }, bookId));
    }

    public Optional<String> readArticleByBookId(Long bookId) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT book.article AS 'book_article' " +
                "FROM book " +
                "WHERE book.id = ?", resultSet -> {
            if (resultSet.next()) {
                return resultSet.getString("book_article");
            }
            return null;
        }, bookId));
    }

    public List<Book> readAllById(List<Long> bookIdList) {
        return jdbcTemplate.query(connection -> {
                    StringBuilder query = new StringBuilder("SELECT book.id AS 'book_id', isbn AS 'book_isbn', paper_quantity AS 'book_paper_quantity', title AS 'book_title', description AS 'book_description', price AS 'book_price', hidden AS 'book_hidden', quantity AS 'book_quantity', article AS 'book_article' FROM book WHERE book.id IN(");
                    for (int i = 0; i < bookIdList.size(); i++) {
                        query.append("?");
                        if (i != bookIdList.size() - 1) {
                            query.append(",");
                        }
                    }
                    query.append(")");
                    PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
                    int index = 1;
                    for (Long id : bookIdList) {
                        preparedStatement.setLong(index++, id);
                    }

                    return preparedStatement;
                }
                , (resultSet, rowNumber) -> Book.builder()
                        .id(resultSet.getLong("book_id"))
                        .title(resultSet.getString("book_title"))
                        .article(resultSet.getString("book_article"))
                        .description(resultSet.getString("book_description"))
                        .hidden(resultSet.getBoolean("book_hidden"))
                        .isbn(resultSet.getString("book_isbn"))
                        .quantity(resultSet.getInt("book_quantity"))
                        .paperQuantity(resultSet.getInt("book_paper_quantity"))
                        .price(resultSet.getBigDecimal("book_price"))
                        .build());
    }
    public Optional<Book> readByTitle(String title) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT id AS 'book_id', title AS 'book_title' " +
                "FROM book " +
                "WHERE title = ?", bookExtractor, title));
    }
}
