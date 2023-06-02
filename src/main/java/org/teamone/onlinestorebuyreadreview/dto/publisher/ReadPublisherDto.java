package org.teamone.onlinestorebuyreadreview.dto.publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadPublisherDto {
    private Long id;
    private String name;
}
