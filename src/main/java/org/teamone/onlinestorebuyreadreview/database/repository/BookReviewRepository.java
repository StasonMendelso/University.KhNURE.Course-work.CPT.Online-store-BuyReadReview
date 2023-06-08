package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.database.mapper.bookreview.BookReviewExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.bookreview.ReadBookReviewsExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.BatchPreparedStatementSetterWithBatchSize;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.bookreview.BookReviewInsertStatementSetter;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.bookreview.BookReviewUpdateStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Anna Nikolaichuk
 */
@Repository
@RequiredArgsConstructor
public class BookReviewRepository implements CrudRepository<Long, BookReview> {
    private final JdbcTemplate jdbcTemplate;
    private final BookReviewExtractor bookReviewExtractor;
    private final ReadBookReviewsExtractor readBookReviewsExtractor;

    @Override
    public Optional<BookReview> create(BookReview bookReview) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book_review (content, client_pseudonym, book_id, request_for_publication_id, book_review_rating, client_id)\n" +
                    "VALUES (?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            new BookReviewInsertStatementSetter(bookReview).setValues(preparedStatement);
            return preparedStatement;
        }, keyHolder);
        Long generatedKey = keyHolder.getKey().longValue();
        insertIntoBookReviewWithTag(generatedKey, bookReview.getTags());

        return read(generatedKey);
    }

    private void insertIntoBookReviewWithTag(Long bookReviewId, List<BookReviewTag> tags) {
        jdbcTemplate.batchUpdate("INSERT INTO book_review_with_tag (book_review_id, book_review_tag_id) VALUES (?, ?);", new BatchPreparedStatementSetterWithBatchSize(tags.size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++, bookReviewId);
                preparedStatement.setLong(index, tags.get(i).getId());
            }
        });
    }

    public Optional<BookReview> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT book_review.id as 'bookReview_id', content as 'bookReview_content', client_pseudonym as 'bookReview_client_pseudonym', " +
                        "book_id as 'bookReview_book_id', " +
                        "request_for_publication_id as 'bookReview_request_for_publication_id', " +
                        "book_review_rating as 'bookReview_rating', " +
                        "client_id as 'bookReview_client_id' " +
                        "FROM book_review " +
                        "LEFT JOIN book ON book_review.book_id = book.id " +
                        "LEFT JOIN book_review_request_for_publication ON book_review.request_for_publication_id = book_review_request_for_publication.id " +
                        "LEFT JOIN `client` ON book_review.client_id = `client`.id " +
                        "LEFT JOIN book_review_with_tag ON book_review.id = book_review_with_tag.book_review_id " +
                        "LEFT JOIN book_review_tag ON book_review_with_tag.book_review_tag_id = book_review_tag.id" +
                        "WHERE book_review.id = ?"),
                preparedStatement -> preparedStatement.setLong(1, id),
                bookReviewExtractor));
    }

    public Optional<BookReview> update(Long id, BookReview bookReview) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book_review " +
                    "SET (content = ?, client_pseudonym = ?, book_id = ?, request_for_publication_id = ?, book_review_rating = ?, client_id = ?)");

            new BookReviewUpdateStatementSetter(bookReview, id).setValues(preparedStatement);
            return preparedStatement;
        });
        jdbcTemplate.update("DELETE FROM book_review_with_tag WHERE book_review_id = ? ", id);
        insertIntoBookReviewWithTag(id, bookReview.getTags());

        return read(id);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<BookReview> readAll() {
        return jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT book_review.id as 'bookReview_id', content as 'bookReview_content', client_pseudonym as 'bookReview_client_pseudonym', " +
                        "book_id as 'bookReview_book_id', " +
                        "request_for_publication_id as 'bookReview_request_for_publication_id', " +
                        "book_review_rating as 'bookReview_rating', " +
                        "client_id as 'bookReview_client_id' " +
                        "FROM book_review " +
                        "LEFT JOIN book ON book_review.book_id = book.id " +
                        "LEFT JOIN book_review_request_for_publication ON book_review.request_for_publication_id = book_review_request_for_publication.id " +
                        "LEFT JOIN `client` ON book_review.client_id = `client`.id " +
                        "LEFT JOIN book_review_with_tag ON book_review.id = book_review_with_tag.book_review_id " +
                        "LEFT JOIN book_review_tag ON book_review_with_tag.book_review_tag_id = book_review_tag.id"),
                readBookReviewsExtractor);
    }

    public List<Long> readAllRequestForPublicationId() {
        return jdbcTemplate.query("SELECT book_review.request_for_publication_id AS 'bookReview_request_for_publication_id' " +
                "FROM book_review", (resultSet, rowNum) -> resultSet.getLong("bookReview_request_for_publication_id"));
    }

    public Optional<Long> readRequestForPublicationIdByBookReviewId(Long bookReviewId) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT book_review.request_for_publication_id AS 'bookReview_request_for_publication_id' " +
                "FROM book_review " +
                "WHERE book_review.id = ?", resultSet -> {
            if (resultSet.next()) {
                return resultSet.getLong("bookReview_request_for_publication_id");
            }
            return null;
        }, bookReviewId));
    }
}
