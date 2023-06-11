package org.teamone.onlinestorebuyreadreview.util.mapper.order;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.CartItem;
import org.teamone.onlinestorebuyreadreview.dto.order.ReadOrderItemDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class OrderItemMapper implements Mapper<CartItem, ReadOrderItemDto> {
    @Override
    public ReadOrderItemDto map(CartItem cartItem) {
        return ReadOrderItemDto.builder()
                .bookId(cartItem.getBook().getId())
                .price(cartItem.getBook().getPrice())
                .fileId(cartItem.getBook().getFiles().stream().findFirst().get().getId())
                .article(cartItem.getBook().getArticle())
                .title(cartItem.getBook().getTitle())
                .quantity(cartItem.getQuantity())
                .subtotal(cartItem.getTotal())
                .build();
    }
}
