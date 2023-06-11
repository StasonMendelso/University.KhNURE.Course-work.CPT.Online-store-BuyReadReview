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
public class PaymentInfo {
    private BigDecimal totalAmount;
    private String description;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private PaymentType paymentType;
}