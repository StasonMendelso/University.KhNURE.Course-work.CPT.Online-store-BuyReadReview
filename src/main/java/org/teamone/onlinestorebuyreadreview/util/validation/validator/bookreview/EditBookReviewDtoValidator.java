package org.teamone.onlinestorebuyreadreview.util.validation.validator.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.teamone.onlinestorebuyreadreview.dto.book.EditBookDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.EditBookReviewDto;
import org.teamone.onlinestorebuyreadreview.service.BookReviewService;

/**
 * @author Anna Nikolaichuk
 */
@Component
@RequiredArgsConstructor
public class EditBookReviewDtoValidator implements Validator {
    private final BookReviewService bookReviewService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EditBookReviewDto.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        EditBookReviewDto editBookReviewDto = (EditBookReviewDto) target;

        if (bookReviewService.getAllRequestForPublicationId().contains(editBookReviewDto.getRequestForPublicationId()) &&
                !bookReviewService.getRequestForPublicationIdByBookReviewId(Long.valueOf(editBookReviewDto.getId())).orElse(0L).equals(editBookReviewDto.getRequestForPublicationId())) {
            errors.rejectValue("requestForPublicationId", "", "Рецензія з цієї заявки вже є.");
        }
    }
}
