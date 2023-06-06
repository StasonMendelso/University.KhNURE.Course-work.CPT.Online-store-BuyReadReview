package org.teamone.onlinestorebuyreadreview.util.mapper.deliveryItem;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.dto.deliveryItem.ReadDeliveryItemDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Starukhina Anastasiia
 */
@Component
public class DeliveryItemReadMapper implements Mapper<DeliveryItem, ReadDeliveryItemDto> {
    @Override
    public ReadDeliveryItemDto map(DeliveryItem deliveryItem) {
        return ReadDeliveryItemDto.builder()
                .bookId(deliveryItem.getBookId())
                .deliveryId(deliveryItem.getDeliveryId())
                .price(deliveryItem.getPrice())
                .quantity(deliveryItem.getQuantity())
                .bookTitle(deliveryItem.getBookTitle())
                .build();
    }
}
