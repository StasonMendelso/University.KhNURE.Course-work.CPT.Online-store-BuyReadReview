package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class CourierDelivery extends DeliveryType{
    private String address;
    private BigDecimal price;
}
