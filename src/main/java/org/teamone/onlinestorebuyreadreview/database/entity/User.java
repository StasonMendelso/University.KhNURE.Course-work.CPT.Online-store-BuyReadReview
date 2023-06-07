package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String telephoneNumber;
    private Role role;
    private Boolean invalid;
    private Boolean blocked;
    private LocalDateTime createdAt;
}
