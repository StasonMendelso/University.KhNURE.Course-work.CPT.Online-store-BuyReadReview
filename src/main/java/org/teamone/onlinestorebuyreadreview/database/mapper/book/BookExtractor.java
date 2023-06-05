package org.teamone.onlinestorebuyreadreview.database.mapper.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.mapper.author.AuthorRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.file.FileRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.genre.GenreRowMapper;
import org.teamone.onlinestorebuyreadreview.database.mapper.publisher.PublisherRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class BookExtractor implements ResultSetExtractor<Book> {
    private final PublisherRowMapper publisherRowMapper;
    private final AuthorRowMapper authorRowMapper;
    private final GenreRowMapper genreRowMapper;
    private final FileRowMapper fileRowMapper;

    @Override
    public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }

        Book book = Book.builder()
                .id(resultSet.getLong("book_id"))
                .title(resultSet.getString("book_title"))
                .article(resultSet.getString("book_article"))
                .description(resultSet.getString("book_description"))
                .hidden(resultSet.getBoolean("book_hidden"))
                .isbn(resultSet.getString("book_isbn"))
                .quantity(resultSet.getInt("book_quantity"))
                .paperQuantity(resultSet.getInt("book_paper_quantity"))
                .price(resultSet.getBigDecimal("book_price"))
                .publisher(publisherRowMapper.mapRow(resultSet, 1))
                .build();

        Set<Author> authors = new HashSet<>();
        Set<Genre> genres = new HashSet<>();
        Set<File> files = new HashSet<>();
        do {
            if(!book.getId().equals(resultSet.getLong("book_id"))){
                resultSet.previous();
                break;
            }
            Author author = authorRowMapper.mapRow(resultSet, resultSet.getRow());
            Genre genre = genreRowMapper.mapRow(resultSet, resultSet.getRow());
            File file = fileRowMapper.mapRow(resultSet,resultSet.getRow());
            authors.add(author);
            genres.add(genre);
            files.add(file);
        } while (resultSet.next());

        book.setAuthors(authors.stream().toList());
        book.setGenres(genres.stream().toList());
        book.setFiles(files.stream().sorted(Comparator.comparing(File::getName)).toList());
        return book;

    }
}
