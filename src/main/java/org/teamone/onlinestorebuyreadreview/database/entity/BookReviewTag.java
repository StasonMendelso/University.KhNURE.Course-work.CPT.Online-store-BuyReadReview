package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Anna Nikolaichuk
 */
@Data
@AllArgsConstructor
@Builder
public class BookReviewTag {
    private Long id;
    private String name;
    private String description;

    public String getNameDescr() {
        return String.format("%s - %s", name, description);
    }
}
