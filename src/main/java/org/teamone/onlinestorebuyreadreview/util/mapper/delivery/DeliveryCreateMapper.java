package org.teamone.onlinestorebuyreadreview.util.mapper.delivery;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryStatus;
import org.teamone.onlinestorebuyreadreview.dto.delivery.CreateDeliveryDto;
import org.teamone.onlinestorebuyreadreview.dto.deliveryItem.ReadDeliveryItemDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.util.List;

/**
 * @author Anastasiia Starukhina
 */
@Component
public class DeliveryCreateMapper implements Mapper<Delivery, CreateDeliveryDto> {

    @Override
    public CreateDeliveryDto map(Delivery object) {
        throw new UnsupportedOperationException();
    }
/*    private Long id;
    private Long requestId;
    private Long courierId;
    private LocalDate creationDate;
    private DeliveryStatus deliveryStatus;
    private String descriptionForStatus;
    private String courierTelephoneNumber;
    private List<DeliveryItem> deliveryItems;*/
    @Override
    public Delivery unmap(CreateDeliveryDto createDeliveryDto) {
        return Delivery.builder()
                .requestId(Long.valueOf(createDeliveryDto.getCourierId()))
                .courierId(Long.valueOf(createDeliveryDto.getCourierId()))
                .deliveryStatus(DeliveryStatus.getInstance(createDeliveryDto.getDeliveryStatus()))
                .descriptionForStatus(createDeliveryDto.getDescriptionForStatus())
                .courierTelephoneNumber(createDeliveryDto.getCourierTelephoneNumber())
                .deliveryItems(createDeliveryDto.getDeliveryItems())
                .build();
    }
}
