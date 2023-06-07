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
public class Contact {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String telephoneNumber;
}
