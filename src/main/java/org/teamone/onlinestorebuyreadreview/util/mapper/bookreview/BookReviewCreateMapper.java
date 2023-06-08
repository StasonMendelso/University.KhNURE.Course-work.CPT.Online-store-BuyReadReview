package org.teamone.onlinestorebuyreadreview.util.mapper.bookreview;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.CreateBookReviewDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Anna Nikolaichuk
 */
@Component
public class BookReviewCreateMapper implements Mapper<BookReview, CreateBookReviewDto> {
    @Override
    public CreateBookReviewDto map(BookReview object) {throw new UnsupportedOperationException();}

    @Override
    public BookReview unmap(CreateBookReviewDto createBookReviewDto){
        return BookReview.builder()
                .clientId(Long.valueOf(Long.parseLong(createBookReviewDto.getClientId())))
                .clientPseudonym(createBookReviewDto.getClientPseudonym())
                .content(createBookReviewDto.getContent())
                .book(Book.builder()
                        .title(createBookReviewDto.getBookTitle())
                        .build())
                .tags(createBookReviewDto.getTagNames()
                        .stream()
                        .map(tag -> BookReviewTag.builder().name(tag).build())
                        .toList())
                .build();
    }
}
