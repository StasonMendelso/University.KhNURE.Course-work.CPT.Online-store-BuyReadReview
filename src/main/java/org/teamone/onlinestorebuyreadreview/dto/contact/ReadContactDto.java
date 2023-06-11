package org.teamone.onlinestorebuyreadreview.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadContactDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String telephoneNumber;
}
