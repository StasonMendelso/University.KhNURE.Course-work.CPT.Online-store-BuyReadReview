package org.teamone.onlinestorebuyreadreview.util.mapper.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.dto.cart.ReadCartDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class CartMapper implements Mapper<Cart, ReadCartDto> {
    private final ReadCartItemMapper readCartItemMapper;
    @Override
    public ReadCartDto map(Cart cart) {
        return ReadCartDto.builder()
                .total(String.valueOf(cart.getTotal().setScale(2)))
                .quantity(String.valueOf(cart.getCartItemsNumber()))
                .cartItems(cart.getCartItems()
                        .stream()
                        .map(readCartItemMapper::map)
                        .toList())
                .build();
    }
}
