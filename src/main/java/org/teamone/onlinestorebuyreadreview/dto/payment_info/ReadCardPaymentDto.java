package org.teamone.onlinestorebuyreadreview.dto.payment_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@Builder
@AllArgsConstructor
public class ReadCardPaymentDto extends ReadPaymentTypeDto{
    private String senderAccountNumber;
    private String receiverAccountNumber;
}
