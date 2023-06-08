package org.teamone.onlinestorebuyreadreview.database.statement.setter.bookreview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Anna Nikolaichuk
 */
@AllArgsConstructor
@Getter
public class BookReviewGeneralStatementSetter implements PreparedStatementSetter {
    int index;
    final BookReview bookReview;

    public BookReviewGeneralStatementSetter(BookReview bookReview) {
        this.index = 1;
        this.bookReview = bookReview;
    }

    @Override
    public void setValues(PreparedStatement preparedStatement) throws SQLException{
        preparedStatement.setString(index++, bookReview.getContent());
        preparedStatement.setString(index++, bookReview.getClientPseudonym());
        preparedStatement.setLong(index++, bookReview.getBook().getId());
        preparedStatement.setLong(index++, bookReview.getRequestForPublicationId());
        preparedStatement.setFloat(index++, bookReview.getRating());
        preparedStatement.setLong(index, bookReview.getClientId());
    }
}