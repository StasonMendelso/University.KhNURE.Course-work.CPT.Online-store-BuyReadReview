package org.teamone.onlinestorebuyreadreview.dto.delivery;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;
import lombok.*;


/**
 * @author Starukhina Anastasiia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateDeliveryDto {
    @NonNull
    @NotBlank(message = "Кур'єрська доставка обов'язково має містити номер кур'єра.")
    private String courierId;
    @NonNull
    @NotBlank(message = "Статус має бути обов'язково заповненим.")
    private String deliveryStatus;
    @NonNull
    @NotBlank(message = "Статус має обов'язково мати опис.")
    private String descriptionForStatus;
    @Pattern(regexp = "^\\\\+380 \\\\(\\\\d{2}\\\\) \\\\d{3}-\\\\d{2}-\\\\d{2}$", message = "Номер телефону повинен відповідати формату \"+380 (12) 345-67-89\".")
    private String courierTelephoneNumber;
}
