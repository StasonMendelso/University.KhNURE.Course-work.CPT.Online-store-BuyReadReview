package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class Publisher {
    private Long id;
    private String name;
}
