package org.teamone.onlinestorebuyreadreview.util.mapper.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.CourierDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryInfo;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryType;
import org.teamone.onlinestorebuyreadreview.database.entity.NovaPoshtaDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentInfo;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.ShopDelivery;
import org.teamone.onlinestorebuyreadreview.dto.order.CreateOrderDeliveryDto;
import org.teamone.onlinestorebuyreadreview.dto.order.CreateOrderDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.contact.ContactCreateMapper;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Component
@AllArgsConstructor
public class OrderCreateMapper implements Mapper<CreateOrderDto, Order> {
    private final ContactCreateMapper contactCreateMapper;

    @Override
    public Order map(CreateOrderDto createOrderDto) {
        return Order.builder()
                .usedBonuses(0)
                .buyerContact(contactCreateMapper.map(createOrderDto.getBuyerContact()))
                .receiverContact(contactCreateMapper.map(createOrderDto.getReceiverContact()))
                .deliveryInfo(getDeliveryInfo(createOrderDto))
                .paymentInfo(PaymentInfo.builder()
                        .paymentMethod(PaymentMethod.getInstance(createOrderDto.getPaymentMethod()))
                        .build())
                .build();

    }

    public DeliveryInfo getDeliveryInfo(CreateOrderDto createOrderDto) {
        CreateOrderDeliveryDto createOrderDtoDelivery = createOrderDto.getDelivery();
        DeliveryMethod deliveryMethod = DeliveryMethod.getInstance(createOrderDto.getDeliveryMethod());
        DeliveryType deliveryType = null;
        if (deliveryMethod.equals(DeliveryMethod.BY_SELF_PICKUP)) {
            deliveryType = ShopDelivery.builder()
                    .address(createOrderDtoDelivery.getField1())
                    .build();
        }
        if (deliveryMethod.equals(DeliveryMethod.BY_COURIER)) {
            deliveryType = CourierDelivery.builder()
                    .address(createOrderDtoDelivery.getField1())
                    .price(BigDecimal.valueOf(50))
                    .build();
        }
        if (deliveryMethod.equals(DeliveryMethod.BY_NOVA_POSHTA)) {
            deliveryType = NovaPoshtaDelivery.builder()
                    .townAddress(createOrderDtoDelivery.getField1())
                    .departureNumber(Integer.valueOf(createOrderDtoDelivery.getField2()))
                    .build();
        }

        return DeliveryInfo.builder()
                .deliveryType(deliveryType)
                .deliveryMethod(deliveryMethod)
                .build();
    }
}
