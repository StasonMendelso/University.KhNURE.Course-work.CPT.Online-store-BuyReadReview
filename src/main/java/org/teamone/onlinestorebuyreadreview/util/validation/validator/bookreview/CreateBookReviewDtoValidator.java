package org.teamone.onlinestorebuyreadreview.util.validation.validator.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.CreateBookReviewDto;
import org.teamone.onlinestorebuyreadreview.service.BookReviewService;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class CreateBookReviewDtoValidator implements Validator {
    private final BookReviewService bookReviewService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateBookReviewDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        CreateBookReviewDto createBookReviewDto = (CreateBookReviewDto) target;

        if(bookReviewService.getAllRequestForPublicationId().contains(createBookReviewDto.getRequestForPublicationId())){
            errors.rejectValue("requestForPublicationId","","Рецензія з цієї заявки вже є.");
        }
    }

}
