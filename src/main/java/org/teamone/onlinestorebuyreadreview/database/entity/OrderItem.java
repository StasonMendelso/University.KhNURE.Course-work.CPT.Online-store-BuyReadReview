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
public class OrderItem {
    private Long orderId;
    private Book book;
    private Integer quantity;
    private BigDecimal price;
}
