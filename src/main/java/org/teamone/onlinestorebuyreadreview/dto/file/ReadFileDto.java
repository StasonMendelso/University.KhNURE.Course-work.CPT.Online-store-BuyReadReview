package org.teamone.onlinestorebuyreadreview.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadFileDto {
    private Long id;
    private String name;
    private String extension;
    private String relativePath;
}
