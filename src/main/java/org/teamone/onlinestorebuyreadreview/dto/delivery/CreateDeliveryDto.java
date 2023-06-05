package org.teamone.onlinestorebuyreadreview.dto.delivery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


/**
 * @author Starukhina Anastasiia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateDeliveryDto {
    private String id;
    private String courierId;
    @NotBlank(message = "Статус має бути обов'язково заповненим.")
    private String creationDate;
    @NotBlank(message = "Статус має бути обов'язково заповненим.")
    private String deliveryStatus;
    @NotBlank(message = "Статус має обов'язково мати опис.")
    private String descriptionForStatus;
    @Pattern(regexp = "^\\\\+380 \\\\(\\\\d{2}\\\\) \\\\d{3}-\\\\d{2}-\\\\d{2}$", message = "Номер телефону повинен відповідати формату \"+380 (12) 345-67-89\".")
    private String courierTelephoneNumber;

}
