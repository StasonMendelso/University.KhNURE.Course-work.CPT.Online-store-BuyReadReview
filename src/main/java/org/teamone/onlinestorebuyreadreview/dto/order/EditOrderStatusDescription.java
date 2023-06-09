package org.teamone.onlinestorebuyreadreview.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
public class EditOrderStatusDescription {
    private String description;
    private Long orderId;
}
