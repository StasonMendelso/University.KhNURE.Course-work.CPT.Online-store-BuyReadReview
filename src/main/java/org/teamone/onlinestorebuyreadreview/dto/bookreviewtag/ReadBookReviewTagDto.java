package org.teamone.onlinestorebuyreadreview.dto.bookreviewtag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Anna Nikolaichuk
 */
@Data
@AllArgsConstructor
@Builder
public class ReadBookReviewTagDto {
    private Long id;
    private String name;
    private String description;
}
