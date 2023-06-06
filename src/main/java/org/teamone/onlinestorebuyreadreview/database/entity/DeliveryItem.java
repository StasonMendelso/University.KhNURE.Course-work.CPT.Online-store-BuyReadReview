package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
/**
 * @author Starukhina Anastasiia
 */
@Data
@AllArgsConstructor
@Builder
public class DeliveryItem {
    private Long bookId;
    private Long isbn;
    private Long deliveryId;
    private BigDecimal price;
    private int quantity;
    private String bookTitle;
}
