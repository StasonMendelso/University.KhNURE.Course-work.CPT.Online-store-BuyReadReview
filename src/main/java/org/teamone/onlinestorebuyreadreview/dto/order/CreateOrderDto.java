package org.teamone.onlinestorebuyreadreview.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDto {
    private CreateContactDto buyerContact;
    private CreateContactDto receiverContact;
    private String paymentMethod;
    private String deliveryMethod;
    private CreateOrderDeliveryDto delivery;
}
