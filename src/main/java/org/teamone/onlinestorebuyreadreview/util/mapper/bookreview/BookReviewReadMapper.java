package org.teamone.onlinestorebuyreadreview.util.mapper.bookreview;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.ReadBookReviewDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookReadMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreviewtag.BookReviewTagReadMapper;

/**
 * @author Anna Nikolaichuk
 */
@RequiredArgsConstructor
@Component
public class BookReviewReadMapper implements Mapper<BookReview, ReadBookReviewDto>  {
    private final BookReviewTagReadMapper bookReviewTagReadMapper;
    private final BookReadMapper bookReadMapper;

    @Override
    public ReadBookReviewDto map(BookReview bookReview){
        return ReadBookReviewDto.builder()
                .id(bookReview.getId())
                .rating(bookReview.getRating())
                .clientPseudonym(bookReview.getClientPseudonym())
                .content(bookReview.getContent())
                .requestForPublicationId(bookReview.getRequestForPublicationId())
                .book(bookReadMapper.map(bookReview.getBook()))
                .tags(bookReview.getTags().stream().map(bookReviewTagReadMapper::map).toList())
                .clientId(bookReview.getClientId())
        .build();
    }
}
