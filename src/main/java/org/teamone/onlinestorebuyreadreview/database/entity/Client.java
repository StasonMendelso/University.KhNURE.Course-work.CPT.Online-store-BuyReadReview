package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Client extends User{
    private Integer bonuses;
}
