package org.teamone.onlinestorebuyreadreview.util.mapper.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.CardPayment;
import org.teamone.onlinestorebuyreadreview.database.entity.CashOnDeliveryPayment;
import org.teamone.onlinestorebuyreadreview.database.entity.CashPayment;
import org.teamone.onlinestorebuyreadreview.database.entity.CourierDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryType;
import org.teamone.onlinestorebuyreadreview.database.entity.NovaPoshtaDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentType;
import org.teamone.onlinestorebuyreadreview.database.entity.ShopDelivery;
import org.teamone.onlinestorebuyreadreview.dto.delivery_info.ReadCourierDeliveryDto;
import org.teamone.onlinestorebuyreadreview.dto.delivery_info.ReadDeliveryTypeDto;
import org.teamone.onlinestorebuyreadreview.dto.delivery_info.ReadNovaPoshtaDeliveryDto;
import org.teamone.onlinestorebuyreadreview.dto.delivery_info.ReadShopDeliveryDto;
import org.teamone.onlinestorebuyreadreview.dto.order.ReadOrderDto;
import org.teamone.onlinestorebuyreadreview.dto.payment_info.ReadCardPaymentDto;
import org.teamone.onlinestorebuyreadreview.dto.payment_info.ReadCashOnDeliveryPaymentDto;
import org.teamone.onlinestorebuyreadreview.dto.payment_info.ReadCashPaymentDto;
import org.teamone.onlinestorebuyreadreview.dto.payment_info.ReadPaymentInfoDto;
import org.teamone.onlinestorebuyreadreview.dto.payment_info.ReadPaymentTypeDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.contact.ContactReadMapper;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Component
@AllArgsConstructor
public class OrderReadMapper implements Mapper<Order, ReadOrderDto> {
    private final ContactReadMapper contactReadMapper;
    private final OrderItemReadMapper orderItemReadMapper;

    @Override
    public ReadOrderDto map(Order order) {
        return ReadOrderDto.builder()
                .orderStatus(order.getOrderStatus().getName())
                .buyerContact(contactReadMapper.map(order.getBuyerContact()))
                .receiverContact(contactReadMapper.map(order.getReceiverContact()))
                .description(order.getDescription())
                .id(order.getId())
                .orderItemsList(order.getOrderItems()
                        .stream()
                        .map(orderItemReadMapper::map)
                        .toList())
                .total(order.totalSum().add(BigDecimal.valueOf(order.getUsedBonuses())))
                .usedBonuses(order.getUsedBonuses())
                .paymentInfo(ReadPaymentInfoDto.builder()
                        .paymentMethod(order.getPaymentInfo().getPaymentMethod().getName())
                        .paymentStatus(order.getPaymentInfo().getPaymentStatus().getName())
                        .totalToPay(order.getPaymentInfo().getTotalAmount().setScale(2).toString())
                        .paymentType(getPaymentType(order))
                        .build())
                .deliveryInfo(getDeliveryInfo(order))
                .deliveryMethod(order.getDeliveryInfo().getDeliveryMethod().getName())
                .build();
    }

    private ReadPaymentTypeDto getPaymentType(Order order) {
        PaymentType paymentType = order.getPaymentInfo().getPaymentType();
        if(paymentType.getClass().equals(CardPayment.class)){
            CardPayment cardPayment = (CardPayment) paymentType;
            return ReadCardPaymentDto.builder()
                    .receiverAccountNumber(cardPayment.getReceiverAccountNumber())
                    .senderAccountNumber(cardPayment.getSenderAccountNumber())
                    .build();
        }
        if(paymentType.getClass().equals(CashPayment.class)){
            CashPayment cashPayment = (CashPayment) paymentType;
            return ReadCashPaymentDto.builder()
                    .amountReceived(cashPayment.getAmountReceived())
                    .change(cashPayment.getChange())
                    .build();
        }
        if(paymentType.getClass().equals(CashOnDeliveryPayment.class)){
            CashOnDeliveryPayment cashOnDeliveryPayment = (CashOnDeliveryPayment) paymentType;
            return ReadCashOnDeliveryPaymentDto.builder()
                    .amountReceived(cashOnDeliveryPayment.getAmountReceived())
                    .change(cashOnDeliveryPayment.getChange())
                    .build();
        }
        throw new IllegalArgumentException("Can't parse undefined type: " + paymentType.getClass());
    }

    private ReadDeliveryTypeDto getDeliveryInfo(Order order) {
        DeliveryType deliveryType = order.getDeliveryInfo().getDeliveryType();
        if (deliveryType.getClass().equals(CourierDelivery.class)) {
            CourierDelivery courierDelivery = (CourierDelivery) deliveryType;
            return ReadCourierDeliveryDto.builder()
                    .address(courierDelivery.getAddress())
                    .price(courierDelivery.getPrice().setScale(2).toString())
                    .build();
        }
        if (deliveryType.getClass().equals(ShopDelivery.class)) {
            ShopDelivery shopDelivery = (ShopDelivery) deliveryType;
            return ReadShopDeliveryDto.builder()
                    .address(shopDelivery.getAddress())
                    .status(shopDelivery.getShopDeliveryStatus().getName())
                    .build();
        }
        if (deliveryType.getClass().equals(NovaPoshtaDelivery.class)) {
            NovaPoshtaDelivery novaPoshtaDelivery = (NovaPoshtaDelivery) deliveryType;
            return ReadNovaPoshtaDeliveryDto.builder()
                    .townAddress(novaPoshtaDelivery.getTownAddress())
                    .departureNumber(novaPoshtaDelivery.getDepartureNumber())
                    .invoiceNumber(novaPoshtaDelivery.getInvoiceNumber())
                    .wayBill(novaPoshtaDelivery.getWayBill())
                    .build();
        }
        throw new IllegalArgumentException("Can't parse undefined type: " + deliveryType.getClass());
    }
}
