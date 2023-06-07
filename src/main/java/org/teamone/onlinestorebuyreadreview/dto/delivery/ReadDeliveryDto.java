package org.teamone.onlinestorebuyreadreview.dto.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryStatus;
import org.teamone.onlinestorebuyreadreview.dto.deliveryItem.ReadDeliveryItemDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Starukhina Anastasiia
 */
@Data
@AllArgsConstructor
@Builder
public class ReadDeliveryDto {
    private Long id;
    private Long requestId;
    private Long courierId;
    private LocalDate creationDate;
    private DeliveryStatus deliveryStatus;
    private String descriptionForStatus;
    private String courierTelephoneNumber;
    private List<ReadDeliveryItemDto> deliveryItems;
}
