package org.teamone.onlinestorebuyreadreview.database.statement.setter.book;

import org.teamone.onlinestorebuyreadreview.database.entity.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
public class BookUpdateStatementSetter extends BookGeneralStatementSetter {
    private final Long bookId;
    public BookUpdateStatementSetter(Long bookId, Book book) {
        super(book);
        this.bookId = bookId;
    }


    @Override
    public void setValues(PreparedStatement preparedStatement) throws SQLException {
        super.setValues(preparedStatement);
        preparedStatement.setLong(++super.index, bookId);
    }
}
