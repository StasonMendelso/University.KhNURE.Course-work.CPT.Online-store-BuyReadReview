package org.teamone.onlinestorebuyreadreview.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateContactDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String telephoneNumber;
}
