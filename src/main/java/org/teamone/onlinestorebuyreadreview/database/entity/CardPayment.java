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
public class CardPayment extends PaymentType{
    private String senderAccountNumber;
    private String receiverAccountNumber;
}
