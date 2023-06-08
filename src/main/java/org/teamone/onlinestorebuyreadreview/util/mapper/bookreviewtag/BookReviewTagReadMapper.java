package org.teamone.onlinestorebuyreadreview.util.mapper.bookreviewtag;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.dto.bookreviewtag.ReadBookReviewTagDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Anna Nikolaichuk
 */
@Component
public class BookReviewTagReadMapper implements Mapper<BookReviewTag, ReadBookReviewTagDto> {
    @Override
    public ReadBookReviewTagDto map(BookReviewTag bookReviewTag){
        return ReadBookReviewTagDto.builder()
                .id(bookReviewTag.getId())
                .name(bookReviewTag.getName())
                .description(bookReviewTag.getDescription())
                .build();
    }

    @Override
    public BookReviewTag unmap(ReadBookReviewTagDto readBookReviewTagDto){
        return BookReviewTag.builder()
                .id(readBookReviewTagDto.getId())
                .name(readBookReviewTagDto.getName())
                .description(readBookReviewTagDto.getDescription())
                .build();
    }
}
