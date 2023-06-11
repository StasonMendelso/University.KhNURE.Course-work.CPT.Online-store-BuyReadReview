package org.teamone.onlinestorebuyreadreview.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadCartItemDto {
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    private Long bookId;
    private Long fileId;
    private String name;
    private String article;
}
