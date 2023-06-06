package org.teamone.onlinestorebuyreadreview.util.mapper.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.dto.delivery.ReadDeliveryDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.deliveryItem.DeliveryItemReadMapper;

/**
 * @author Starukhina Anastasiia
 */
@RequiredArgsConstructor
@Component
public class DeliveryReadMapper implements Mapper<Delivery, ReadDeliveryDto> {
    private final DeliveryItemReadMapper deliveryItemReadMapper;
    @Override
    public ReadDeliveryDto map(Delivery delivery) {
        return ReadDeliveryDto.builder()
                .id(delivery.getId())
                .requestId(delivery.getRequestId())
                .courierId(delivery.getCourierId())
                .creationDate(delivery.getCreationDate())
                .deliveryStatus(delivery.getDeliveryStatus())
                .descriptionForStatus(delivery.getDescriptionForStatus())
                .courierTelephoneNumber(delivery.getCourierTelephoneNumber())
                .deliveryItems(delivery.getDeliveryItems().stream().map(deliveryItemReadMapper::map).toList())
                .build();
    }
}
