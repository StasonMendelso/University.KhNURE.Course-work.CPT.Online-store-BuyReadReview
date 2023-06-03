package org.teamone.onlinestorebuyreadreview.database.statement.setter;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
public class BookInsertStatementSetter implements PreparedStatementSetter {
    private final Book book;
    @Override
    public void setValues(PreparedStatement preparedStatement) throws SQLException {
        int index = 1;
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
