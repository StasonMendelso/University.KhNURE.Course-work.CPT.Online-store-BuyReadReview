package org.teamone.onlinestorebuyreadreview.database.mapper.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.mapper.author.ReadAuthorRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.file.ReadFileRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.genre.ReadGenreRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.publisher.ReadPublisherRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class ReadBookExtractor implements ResultSetExtractor<Book> {
    private final ReadPublisherRowMapper readPublisherRowMapper;
    private final ReadAuthorRowMapper readAuthorRowMapper;
    private final ReadGenreRowMapper readGenreRowMapper;
    private final ReadFileRowMapper readFileRowMapper;

    @Override
    public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }

        Book book = Book.builder()
                .id(resultSet.getLong("book_id"))
                .title(resultSet.getString("title"))
                .article(resultSet.getString("article"))
                .description(resultSet.getString("description"))
                .hidden(resultSet.getBoolean("hidden"))
                .isbn(resultSet.getString("isbn"))
                .quantity(resultSet.getInt("quantity"))
                .paperQuantity(resultSet.getInt("paper_quantity"))
                .price(resultSet.getBigDecimal("price"))
                .publisher(readPublisherRowMapper.mapRow(resultSet, 1))
                .build();

        Set<Author> authors = new HashSet<>();
        Set<Genre> genres = new HashSet<>();
        Set<File> files = new HashSet<>();
        do {
            if(!book.getId().equals(resultSet.getLong("book_id"))){
                resultSet.previous();
                break;
            }
            Author author = readAuthorRowMapper.mapRow(resultSet, resultSet.getRow());
            Genre genre = readGenreRowMapper.mapRow(resultSet, resultSet.getRow());
            File file = readFileRowMapper.mapRow(resultSet,resultSet.getRow());
            authors.add(author);
            genres.add(genre);
            files.add(file);
        } while (resultSet.next());

        book.setAuthors(authors.stream().toList());
        book.setGenres(genres.stream().toList());
        book.setFiles(files.stream().toList());
        return book;

    }
}
