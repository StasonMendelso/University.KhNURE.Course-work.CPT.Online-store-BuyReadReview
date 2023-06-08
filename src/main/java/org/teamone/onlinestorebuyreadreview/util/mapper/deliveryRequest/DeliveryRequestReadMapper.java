package org.teamone.onlinestorebuyreadreview.util.mapper.deliveryRequest;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;
import org.teamone.onlinestorebuyreadreview.dto.deliveryRequest.ReadDeliveryRequestDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Anastasiia Starukhina
 */
@Component
public class DeliveryRequestReadMapper implements Mapper<DeliveryRequest, ReadDeliveryRequestDto> {

    @Override
    public ReadDeliveryRequestDto map(DeliveryRequest deliveryRequest) {
        return ReadDeliveryRequestDto.builder()
                .id(deliveryRequest.getId())
                .managerId(deliveryRequest.getManagerId())
                .manager_first_name(deliveryRequest.getManager_first_name())
                .manager_last_name(deliveryRequest.getManager_last_name())
                .courierId(deliveryRequest.getCourierId())
                .orderId(deliveryRequest.getOrderId())
                .clientWishDescription(deliveryRequest.getClientWishDescription())
                .creationDate(deliveryRequest.getCreationDate())
                .deliveryRequestStatus(deliveryRequest.getDeliveryRequestStatus())
                .descriptionForStatus(deliveryRequest.getDescriptionForStatus())
                .build();
    }
}
