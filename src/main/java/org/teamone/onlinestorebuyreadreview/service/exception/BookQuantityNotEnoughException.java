package org.teamone.onlinestorebuyreadreview.service.exception;

import lombok.Data;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;

/**
 * @author Stanislav Hlova
 */
@Data
public class BookQuantityNotEnoughException extends RuntimeException {
    private final Book book;
    public BookQuantityNotEnoughException(Book book) {
        super("Not enough quantity of book");
        this.book = book;
    }

}
