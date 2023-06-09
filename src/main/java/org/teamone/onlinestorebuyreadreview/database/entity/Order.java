package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private Client client;
    private User Manager;
    private String description;
    private Integer usedBonuses;
    private Contact receiverContact;
    private Contact buyerContact;
    private DeliveryInfo deliveryInfo;
    private PaymentInfo paymentInfo;
    private List<OrderItem> orderItems;

    public BigDecimal totalSum() {
        return orderItems
                .stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal::add)
                .get()
                .subtract(BigDecimal.valueOf(usedBonuses));
    }
}
