package org.teamone.onlinestorebuyreadreview.dto.deliveryItem;

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
public class ReadDeliveryItemDto {
    private Long bookId;
    private Long deliveryId;
    private BigDecimal price;
    private int quantity;
    private String bookTitle;
}
