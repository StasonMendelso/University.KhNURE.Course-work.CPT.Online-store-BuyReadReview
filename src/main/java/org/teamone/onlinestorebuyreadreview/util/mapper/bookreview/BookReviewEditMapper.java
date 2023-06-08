package org.teamone.onlinestorebuyreadreview.util.mapper.bookreview;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.EditBookReviewDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Anna Nikolaichuk
 */
@Component
public class BookReviewEditMapper implements Mapper<BookReview, EditBookReviewDto> {
    @Override
    public EditBookReviewDto map(BookReview bookReview){
        return EditBookReviewDto.builder()
                .id(bookReview.getId().toString())
                .content(bookReview.getContent())
                .clientPseudonym(bookReview.getClientPseudonym())
                .bookTitle(bookReview.getBook().getTitle())
                .clientId(bookReview.getClientId().toString())
                .requestForPublicationId(bookReview.getRequestForPublicationId().toString())
                .tagNames(bookReview.getTags()
                        .stream()
                        .map(BookReviewTag::getName)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }

    @Override
    public BookReview unmap(EditBookReviewDto editBookReviewDto){
        return BookReview.builder()
                .id(Long.valueOf(editBookReviewDto.getId()))
                .clientPseudonym(editBookReviewDto.getClientPseudonym())
                .requestForPublicationId(Long.valueOf(editBookReviewDto.getRequestForPublicationId()))
                .content(editBookReviewDto.getContent())
                .clientId(Long.valueOf(editBookReviewDto.getClientId()))
                .book(Book.builder()
                        .title(editBookReviewDto.getBookTitle())
                        .build())
                .tags(editBookReviewDto.getTagNames()
                        .stream()
                        .map(tag -> BookReviewTag.builder().name(tag).build())
                        .toList())
                .build();
    }
}
