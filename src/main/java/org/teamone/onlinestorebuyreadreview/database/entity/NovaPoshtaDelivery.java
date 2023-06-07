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
public class NovaPoshtaDelivery extends DeliveryType {
    private String townAddress;
    private String invoiceNumber;
    private Integer departureNumber;
    private BigDecimal wayBill;
}
