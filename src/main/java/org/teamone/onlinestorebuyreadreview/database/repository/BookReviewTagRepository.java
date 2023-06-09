package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.database.mapper.bookreviewtag.BookReviewTagExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.bookreviewtag.BookReviewTagRowMapper;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * @author Anna Nikolaichuk
 */
@Repository
@RequiredArgsConstructor
public class BookReviewTagRepository implements CrudRepository<Long, BookReviewTag> {
    private final JdbcTemplate jdbcTemplate;
    private final BookReviewTagExtractor bookReviewTagExtractor;
    private final BookReviewTagRowMapper bookReviewTagRowMapper;

    @Override
    public Optional<BookReviewTag> create(BookReviewTag entity) {
        return Optional.empty();
    }

    @Override
    public Optional<BookReviewTag> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<BookReviewTag> update(Long id, BookReviewTag entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<BookReviewTag> readAll() {
        return jdbcTemplate.query("SELECT id AS 'bookReviewTag_id', `name` AS 'bookReviewTag_name', `description` AS 'bookReviewTag_description' " +
                "FROM book_review_tag " +
                "ORDER BY `name`", bookReviewTagRowMapper);
    }

    public List<BookReviewTag> readAllByName(List<String> tagNames) {
        return jdbcTemplate.query(connection -> {
            StringBuilder statement = new StringBuilder("SELECT id AS 'bookReviewTag_id', `name` AS 'bookReviewTag_name', `description` AS 'bookReviewTag_description' " +
                    "FROM book_review_tag");
            statement.append("WHERE ");
            for (int i = 0; i < tagNames.size(); i++) {
                String condition = "book_review_tag.name=?";
                statement.append(condition);
                if (i != tagNames.size() - 1) {
                    statement.append(" OR ");
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(statement.toString());
            int index = 1;
            for (String tagName : tagNames) {
                preparedStatement.setString(index++, tagName);
            }
            return preparedStatement;
        }, bookReviewTagRowMapper);
    }

    public List<BookReviewTag> create(List<BookReviewTag> tagsForAdding) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                    StringBuilder statement = new StringBuilder("INSERT INTO book_review_tag(name, description) VALUES");
                    for (int i = 0; i < tagsForAdding.size(); i++) {
                        statement.append("(?,?)");
                        if (i != tagsForAdding.size() - 1) {
                            statement.append(" , ");
                        }
                    }
                    PreparedStatement preparedStatement = connection.prepareStatement(statement.toString(), Statement.RETURN_GENERATED_KEYS);
                    int index = 1;
                    for (BookReviewTag bookReviewTag : tagsForAdding) {
                        preparedStatement.setString(index++, bookReviewTag.getName());
                        preparedStatement.setString(index++, bookReviewTag.getDescription());
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
    public List<BookReviewTag> readAllById(List<Long> idList) {
        return jdbcTemplate.query(connection -> {
            StringBuilder statement = new StringBuilder("SELECT id AS 'bookReviewTag_id', name AS 'bookReviewTag_name', description AS 'bookReviewTag_description' " +
                    "FROM book_review_tag ");
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
        }, bookReviewTagRowMapper);
    }

}
