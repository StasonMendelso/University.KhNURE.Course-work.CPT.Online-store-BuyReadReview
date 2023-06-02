package org.teamone.onlinestorebuyreadreview.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadAuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudonym;
}
