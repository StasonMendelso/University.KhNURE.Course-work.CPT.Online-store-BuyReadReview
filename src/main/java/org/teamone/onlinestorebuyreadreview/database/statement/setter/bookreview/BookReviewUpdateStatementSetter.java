package org.teamone.onlinestorebuyreadreview.database.statement.setter.bookreview;

import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Anna Nikolaichuk
 */
public class BookReviewUpdateStatementSetter extends BookReviewGeneralStatementSetter {
    private final Long bookReviewId;

    public BookReviewUpdateStatementSetter(BookReview bookReview, Long bookReviewId) {
        super(bookReview);
        this.bookReviewId = bookReviewId;
    }

    @Override
    public void setValues(PreparedStatement preparedStatement) throws SQLException {
        super.setValues(preparedStatement);
        preparedStatement.setLong(++super.index, bookReviewId);
    }
}
