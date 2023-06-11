package org.teamone.onlinestorebuyreadreview.util.mapper.cart;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.dto.cart.ReadCartTotalAndQuantityDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.math.RoundingMode;

/**
 * @author Stanislav Hlova
 */
@Component
public class CartTotalAndQuantityMapper implements Mapper<Cart, ReadCartTotalAndQuantityDto> {
    @Override
    public ReadCartTotalAndQuantityDto map(Cart cart) {
        return ReadCartTotalAndQuantityDto.builder()
                .total(String.valueOf(cart.getTotal().setScale(2, RoundingMode.CEILING).toString()))
                .quantity(cart.getCartItemsNumber().toString())
                .build();
    }
}
