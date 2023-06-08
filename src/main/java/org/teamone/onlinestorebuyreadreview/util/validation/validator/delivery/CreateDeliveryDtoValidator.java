package org.teamone.onlinestorebuyreadreview.util.validation.validator.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;
import org.teamone.onlinestorebuyreadreview.dto.delivery.CreateDeliveryDto;
import org.teamone.onlinestorebuyreadreview.service.DeliveryService;

/**
 * @author Anastasiia Starukhina
 */
@Component
@RequiredArgsConstructor
public class CreateDeliveryDtoValidator implements Validator {
    private final DeliveryService deliveryService;
    //private final DeliveryRequestService deliveryRequestService;
    @Override
    public boolean supports(Class<?> clazz) {
        return CreateDeliveryDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateDeliveryDto createDeliveryDto = (CreateDeliveryDto) target;

        //if()
/*        if(deliveryService.getAllIsbn().contains(createBookDto.getIsbn())){
            errors.rejectValue("isbn","","Книга з таким ISBN вже є.");
        }*/
    }
}
