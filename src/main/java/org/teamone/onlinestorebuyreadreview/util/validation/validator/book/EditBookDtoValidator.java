package org.teamone.onlinestorebuyreadreview.util.validation.validator.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.teamone.onlinestorebuyreadreview.dto.book.EditBookDto;
import org.teamone.onlinestorebuyreadreview.service.BookService;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class EditBookDtoValidator implements Validator {
    private final BookService bookService;



    @Override
    public boolean supports(Class<?> clazz) {
        return EditBookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EditBookDto editBookDto = (EditBookDto) target;

        if(bookService.getAllIsbn().contains(editBookDto.getIsbn()) && !bookService.getIsbnByBookId(Long.valueOf(editBookDto.getId())).orElse("").equals(editBookDto.getIsbn())){
            errors.rejectValue("isbn","","Інша книга має вже такий ISBN.");
        }
        if(bookService.getAllArticle().contains(editBookDto.getArticle()) && !bookService.getArticleByBookId(Long.valueOf(editBookDto.getId())).orElse("").equals(editBookDto.getArticle())){
            errors.rejectValue("article","","Інша книга має вже такий артикул.");
        }
    }
}
