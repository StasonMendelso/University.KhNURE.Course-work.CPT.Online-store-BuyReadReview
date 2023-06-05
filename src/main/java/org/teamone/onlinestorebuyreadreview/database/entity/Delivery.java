package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Starukhina Anastasiia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    private Long id;
    private Long requestId;
    private Long courierId;
    private LocalDate creationDate;
    private DeliveryStatus deliveryStatus;
    private String descriptionForStatus;
    private String courierTelephoneNumber;
    private List<DeliveryItem> deliveryItems;
}
