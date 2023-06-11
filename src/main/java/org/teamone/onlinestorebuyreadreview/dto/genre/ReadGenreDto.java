package org.teamone.onlinestorebuyreadreview.dto.genre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadGenreDto {
    private Long id;
    private String name;
}
