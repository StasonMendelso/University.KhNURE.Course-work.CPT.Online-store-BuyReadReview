package org.teamone.onlinestorebuyreadreview.dto.deliveryrequest;

import jakarta.validation.constraints.Pattern;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequestStatus;

import java.time.LocalDate;

/**
 * @author Starukhina Anastasiia
 */
public class CreateDeliveryRequestDto {
    private Long id;
    private Long managerId;
    private Long courierId;
    private Long orderId;
    private String clientWishDescription;
    private LocalDate creationDate;
    private DeliveryRequestStatus deliveryRequestStatus;
    private String descriptionForStatus;
}
