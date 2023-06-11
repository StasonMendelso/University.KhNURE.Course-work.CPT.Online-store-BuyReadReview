package org.teamone.onlinestorebuyreadreview.dto.bookreview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreviewtag.ReadBookReviewTagDto;

import java.util.List;

/**
 * @author Anna Nikolaichuk
 */
@Data
@AllArgsConstructor
@Builder
public class ReadBookReviewDto {
    private Long id;
    private ReadBookDto book;
    private Long requestForPublicationId;
    private Long clientId;
    private String clientPseudonym;
    private String content;
    private List<ReadBookReviewTagDto> tags;
    private Float rating;
}
