package org.teamone.onlinestorebuyreadreview.service.exception;

/**
 * @author Stanislav Hlova
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long bookId) {
        super("Can't find a book with id =  " + bookId);
    }
}
