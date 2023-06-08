package org.teamone.onlinestorebuyreadreview.util.mapper.order;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderItem;
import org.teamone.onlinestorebuyreadreview.dto.order.ReadOrderItemDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class OrderItemFromReadMapper implements Mapper<ReadOrderItemDto, OrderItem> {
    @Override
    public OrderItem map(ReadOrderItemDto orderItemDto) {
        return OrderItem.builder()
                .price(orderItemDto.getPrice())
                .quantity(orderItemDto.getQuantity())
                .book(Book.builder()
                        .id(orderItemDto.getBookId())
                        .title(orderItemDto.getTitle())
                        .build())
                .build();
    }
}
