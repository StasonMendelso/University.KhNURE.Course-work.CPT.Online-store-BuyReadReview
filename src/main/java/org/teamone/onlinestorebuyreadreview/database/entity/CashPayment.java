package org.teamone.onlinestorebuyreadreview.database.entity;

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
public class CashPayment extends PaymentType{
    private BigDecimal change;
    private BigDecimal amountReceived;
}
