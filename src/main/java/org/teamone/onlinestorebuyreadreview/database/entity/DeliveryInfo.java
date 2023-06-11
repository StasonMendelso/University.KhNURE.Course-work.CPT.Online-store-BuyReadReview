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
public class DeliveryInfo {
    private DeliveryMethod deliveryMethod;
    private DeliveryType deliveryType;
}
