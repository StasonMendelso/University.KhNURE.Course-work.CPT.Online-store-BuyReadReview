package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentMethod;
import org.teamone.onlinestorebuyreadreview.dto.order.CreateOrderDto;
import org.teamone.onlinestorebuyreadreview.dto.order.OrderCheckoutDto;
import org.teamone.onlinestorebuyreadreview.util.constant.SessionConstant;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderCheckoutMapper;

import java.util.Arrays;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/order/checkout")
@SessionAttributes({SessionConstant.CART, SessionConstant.ORDER_CHECKOUT_DTO})
@AllArgsConstructor
public class OrderCheckoutController {
    private final OrderCheckoutMapper orderCheckoutMapper;

    @GetMapping
    public String getCheckoutPage(@ModelAttribute("createOrderDto") CreateOrderDto createOrderDto,
                                  @ModelAttribute(SessionConstant.ORDER_CHECKOUT_DTO) OrderCheckoutDto orderCheckoutDto,
                                  Cart cart,
                                  Model model
    ) {

        if (cart == null || cart.isEmpty()) {
            return "order/checkout";
        }
        //TODO: 08.06.2023 updates all data about cartItem from database

        orderCheckoutDto = orderCheckoutMapper.map(cart);

        model.addAttribute("orderCheckoutDto", orderCheckoutDto);
        model.addAttribute("createOrderDto", createOrderDto);
        model.addAttribute("paymentMethodList", Arrays.stream(PaymentMethod.values()).map(PaymentMethod::getName).toList());
        model.addAttribute("deliveryMethodList", Arrays.stream(DeliveryMethod.values()).map(DeliveryMethod::getName).toList());
        return "order/checkout";
    }

    @ModelAttribute(SessionConstant.ORDER_CHECKOUT_DTO)
    public OrderCheckoutDto orderCheckoutDto() {
        return new OrderCheckoutDto();
    }
}
