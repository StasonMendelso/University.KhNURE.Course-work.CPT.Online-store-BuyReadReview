package org.teamone.onlinestorebuyreadreview.dto.payment_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadPaymentInfoDto {
    private String paymentMethod;
    private String paymentStatus;
    private String totalToPay;
    private ReadPaymentTypeDto paymentType;
}
