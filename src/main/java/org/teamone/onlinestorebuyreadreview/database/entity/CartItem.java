package org.teamone.onlinestorebuyreadreview.database.entity;

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
public class CartItem {
    private Long cartId;
    private Book book;
    private Integer quantity;

    public BigDecimal getTotal() {
        return book.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
    public void incrementeQuantity(){
        this.quantity++;
    }
    public void decrementQuantity(){
        this.quantity--;
    }
}
