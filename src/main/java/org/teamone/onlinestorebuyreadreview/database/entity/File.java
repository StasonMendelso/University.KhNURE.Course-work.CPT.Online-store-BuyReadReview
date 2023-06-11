package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class File {
    private Long id;
    private String name;
    private String extension;
    private String relativePath;

}
