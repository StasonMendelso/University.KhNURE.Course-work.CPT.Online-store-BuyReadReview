package org.teamone.onlinestorebuyreadreview.database.statement.setter.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@AllArgsConstructor
@Getter
public abstract class BookGeneralStatementSetter implements PreparedStatementSetter {
    int index;
    final Book book;

    public BookGeneralStatementSetter(Book book) {
        this.index = 1;
        this.book = book;
    }

    @Override
    public void setValues(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(index++,book.getPaperQuantity());
        preparedStatement.setString(index++,book.getTitle());
        preparedStatement.setString(index++,book.getDescription());
        preparedStatement.setString(index++,book.getIsbn());
        preparedStatement.setBoolean(index++,book.getHidden());
        preparedStatement.setBigDecimal(index++,book.getPrice());
        preparedStatement.setInt(index++,book.getQuantity());
        preparedStatement.setString(index++,book.getArticle());
        preparedStatement.setLong(index,book.getPublisher().getId());
    }
}
