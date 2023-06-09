package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class MockDto {
    //your field from sql here
    private String field1;
}
