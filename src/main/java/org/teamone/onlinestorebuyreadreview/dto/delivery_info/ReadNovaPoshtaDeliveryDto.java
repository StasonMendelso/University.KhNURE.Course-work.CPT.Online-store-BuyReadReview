package org.teamone.onlinestorebuyreadreview.dto.delivery_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Data
@Builder
@AllArgsConstructor
public class ReadNovaPoshtaDeliveryDto extends ReadDeliveryTypeDto{
    private String townAddress;
    private String invoiceNumber;
    private Integer departureNumber;
    private BigDecimal wayBill;
}
