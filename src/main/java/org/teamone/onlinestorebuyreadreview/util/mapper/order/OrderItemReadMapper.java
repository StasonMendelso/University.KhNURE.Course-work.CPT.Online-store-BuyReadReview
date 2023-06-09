package org.teamone.onlinestorebuyreadreview.util.mapper.order;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderItem;
import org.teamone.onlinestorebuyreadreview.dto.order.ReadOrderItemDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Component
public class OrderItemReadMapper implements Mapper<OrderItem, ReadOrderItemDto> {
    @Override
    public ReadOrderItemDto map(OrderItem orderItem) {
        return ReadOrderItemDto.builder()
                .article(orderItem.getBook().getArticle())
                .bookId(orderItem.getBook().getId())
                .fileId(orderItem.getBook().getFiles().stream().findFirst().get().getId())
                .title(orderItem.getBook().getTitle())
                .price(orderItem.getPrice())
                .subtotal(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .quantity(orderItem.getQuantity())
                .build();
    }
}
