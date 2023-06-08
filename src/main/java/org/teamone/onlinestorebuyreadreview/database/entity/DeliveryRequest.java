package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Starukhina Anastasiia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryRequest {
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
