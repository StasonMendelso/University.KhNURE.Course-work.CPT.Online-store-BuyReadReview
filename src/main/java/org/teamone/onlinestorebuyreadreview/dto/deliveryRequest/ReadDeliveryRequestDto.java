package org.teamone.onlinestorebuyreadreview.dto.deliveryRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequestStatus;

import java.time.LocalDate;

/**
 * @author Anastasiia Starukhina
 */
@Data
@AllArgsConstructor
@Builder
public class ReadDeliveryRequestDto {
    private Long id;
    private Long managerId;
    private String manager_first_name;
    private String manager_last_name;
    private Long courierId;
    private Long orderId;
    private String clientWishDescription;
    private LocalDate creationDate;
    private DeliveryRequestStatus deliveryRequestStatus;
    private String descriptionForStatus;
}
