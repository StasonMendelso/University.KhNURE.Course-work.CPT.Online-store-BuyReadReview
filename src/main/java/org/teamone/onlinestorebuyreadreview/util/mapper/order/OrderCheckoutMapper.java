package org.teamone.onlinestorebuyreadreview.util.mapper.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.dto.order.OrderCheckoutDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Component
@Data
@AllArgsConstructor
public class OrderCheckoutMapper implements Mapper<Cart, OrderCheckoutDto> {
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderCheckoutDto map(Cart cart) {
        return OrderCheckoutDto.builder()
                .courierDeliveryPrice(BigDecimal.valueOf(50))
                .total(cart.getTotal())
                .orderItemList(cart.getCartItems().stream().map(orderItemMapper::map).toList())
                .build();
    }
}
