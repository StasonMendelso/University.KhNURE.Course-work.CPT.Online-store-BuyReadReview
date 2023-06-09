package org.teamone.onlinestorebuyreadreview.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Data
@Builder
@AllArgsConstructor
public class ReadOrderItemDto {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    private Long bookId;
    private Long fileId;
    private String title;
    private String article;
}
