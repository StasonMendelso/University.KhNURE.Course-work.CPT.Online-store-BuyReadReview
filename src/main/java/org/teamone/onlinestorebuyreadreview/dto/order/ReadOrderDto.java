package org.teamone.onlinestorebuyreadreview.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.teamone.onlinestorebuyreadreview.dto.contact.ReadContactDto;
import org.teamone.onlinestorebuyreadreview.dto.delivery_info.ReadDeliveryTypeDto;
import org.teamone.onlinestorebuyreadreview.dto.payment_info.ReadPaymentInfoDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadOrderDto {
    private Long id;
    private List<ReadOrderItemDto> orderItemsList;
    private ReadContactDto buyerContact;
    private ReadContactDto receiverContact;
    private String description;
    private String orderStatus;
    private LocalDateTime createAt;
    private Long clientId;
    private Long managerId;
    private Integer usedBonuses;
    private ReadPaymentInfoDto paymentInfo;
    private ReadDeliveryTypeDto deliveryInfo;
    private String deliveryMethod;

    private BigDecimal total;
}
