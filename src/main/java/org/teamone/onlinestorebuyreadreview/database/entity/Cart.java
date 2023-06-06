package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private List<CartItem> cartItems = new ArrayList<>();

    public Integer getCartItemsNumber() {
        if (isCartItemsNull()) {
            return 0;
        }
        return cartItems
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public BigDecimal getTotal() {
        if (isCartItemsNull()) {
            return BigDecimal.ZERO;
        }
        return cartItems
                .stream()
                .map(cartItem -> cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItem(CartItem cartItem) {
        if (isCartItemsNull()) {
            cartItems = new ArrayList<>();
        }
        Optional<CartItem> optional = cartItems
                .stream()
                .filter(cartItem1 -> cartItem1.getBook().getId().equals(cartItem.getBook().getId()))
                .findFirst();
        if (optional.isPresent()) {
            optional.get().incrementeQuantity();
        } else {
            cartItem.setQuantity(1);
            cartItems.add(cartItem);
        }
    }

    public void removeItem(CartItem cartItem) {
        if (isCartItemsNull()) {
            return;
        }
        cartItems.remove(cartItem);
    }

    private boolean isCartItemsNull() {
        return cartItems == null;
    }

    public boolean isEmpty() {
        return isCartItemsNull() || cartItems.size() == 0;
    }

    public void removeItemByBookId(Long bookId) {
        if (isCartItemsNull()) {
            return;
        }
        cartItems.removeIf(cartItem -> cartItem.getBook().getId().equals(bookId));
    }

    public void updateItemQuantityByBookId(Long bookId, Integer quantity) {
        if (isCartItemsNull()) {
            return;
        }
        cartItems
                .stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(bookId))
                .findFirst()
                .ifPresent(cartItem -> cartItem.setQuantity(quantity));
    }
}
