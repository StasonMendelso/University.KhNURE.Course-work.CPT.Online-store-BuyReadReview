package org.teamone.onlinestorebuyreadreview.dto.payment_info;

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
public class ReadCashPaymentDto extends ReadPaymentTypeDto{
    private BigDecimal change;
    private BigDecimal amountReceived;
}
