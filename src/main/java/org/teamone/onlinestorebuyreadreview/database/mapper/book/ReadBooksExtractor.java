package org.teamone.onlinestorebuyreadreview.database.mapper.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class ReadBooksExtractor implements ResultSetExtractor<List<Book>> {
    private final BookExtractor bookExtractor;

    @Override
    public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.isBeforeFirst()) {
            return Collections.emptyList();
        }
        List<Book> books = new ArrayList<>();
        do {
            books.add(bookExtractor.extractData(resultSet));
        } while (!resultSet.isAfterLast());
        return books;
    }
}
