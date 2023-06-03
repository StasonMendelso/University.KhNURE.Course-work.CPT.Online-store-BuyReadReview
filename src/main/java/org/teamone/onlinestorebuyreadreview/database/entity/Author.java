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
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private String pseudonym;

    public String getFullName() {
        return String.format("%s %s", lastName, firstName);
    }
    public static class AuthorBuilder {
        public AuthorBuilder fullName(String fullName) {
            this.firstName = fullName.split(" ")[0];
            this.lastName = fullName.split(" ")[1];
            return this;
        }
    }
}
