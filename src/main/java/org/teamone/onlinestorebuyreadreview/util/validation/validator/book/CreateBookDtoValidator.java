package org.teamone.onlinestorebuyreadreview.util.validation.validator.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.teamone.onlinestorebuyreadreview.dto.book.CreateBookDto;
import org.teamone.onlinestorebuyreadreview.service.BookService;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class CreateBookDtoValidator implements Validator {
    private final BookService bookService;



    @Override
    public boolean supports(Class<?> clazz) {
        return CreateBookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateBookDto createBookDto = (CreateBookDto) target;

        if(bookService.getAllIsbn().contains(createBookDto.getIsbn())){
            errors.rejectValue("isbn","","Книга з таким ISBN вже є.");
        }
        if(bookService.getAllArticle().contains(createBookDto.getArticle())){
            errors.rejectValue("article","","Книга з таким артикулем вже є.");
        }
    }
}
