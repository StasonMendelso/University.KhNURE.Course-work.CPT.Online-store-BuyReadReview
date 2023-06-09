package org.teamone.onlinestorebuyreadreview.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.teamone.onlinestorebuyreadreview.database.entity.Cart;
import org.teamone.onlinestorebuyreadreview.database.entity.Client;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.Role;
import org.teamone.onlinestorebuyreadreview.dto.order.CreateOrderDto;
import org.teamone.onlinestorebuyreadreview.dto.order.OrderCheckoutDto;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;
import org.teamone.onlinestorebuyreadreview.service.CartService;
import org.teamone.onlinestorebuyreadreview.service.OrderService;
import org.teamone.onlinestorebuyreadreview.service.exception.BookQuantityNotEnoughException;
import org.teamone.onlinestorebuyreadreview.util.constant.SessionConstant;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderCheckoutMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderCreateMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderItemFromReadMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderReadMapper;

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
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderCreateMapper orderCreateMapper;
    private final OrderReadMapper orderReadMapper;
    private final OrderItemFromReadMapper orderItemFromReadMapper;

    @GetMapping
    public String getCheckoutPage(@ModelAttribute("createOrderDto") CreateOrderDto createOrderDto,
                                  @ModelAttribute(SessionConstant.ORDER_CHECKOUT_DTO) OrderCheckoutDto orderCheckoutDto,
                                  Cart cart,
                                  Model model
    ) {
        if (cart == null || cart.isEmpty()) {
            return "order/checkout";
        }
        cart = cartService.getLastDataAboutCartItems(cart);
        orderCheckoutDto = orderCheckoutMapper.map(cart);

        model.addAttribute("orderCheckoutDto", orderCheckoutDto);
        model.addAttribute("createOrderDto", createOrderDto);
        model.addAttribute("paymentMethodList", Arrays.stream(PaymentMethod.values()).map(PaymentMethod::getName).toList());
        model.addAttribute("deliveryMethodList", Arrays.stream(DeliveryMethod.values()).map(DeliveryMethod::getName).toList());
        return "order/checkout";
    }

    @PostMapping
    public String confirmOrder(@ModelAttribute("createOrderDto") CreateOrderDto createOrderDto,
                               HttpServletRequest httpRequest,
                               @ModelAttribute(SessionConstant.ORDER_CHECKOUT_DTO) OrderCheckoutDto orderCheckoutDto,
                               Model model, Authentication authentication, Cart cart) {


        Order order = orderCreateMapper.map(createOrderDto);
        if (authentication.isAuthenticated()) {
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
            order.setClient(Client.builder().id(authUserDetails.getUser().getId()).build());
        }
        //set orderItems from orderCheckout
        order.setOrderItems(orderCheckoutDto.getOrderItemList()
                .stream()
                .map(orderItemFromReadMapper::map)
                .toList());
        order.getPaymentInfo().setTotalAmount(orderService.calculateTotal(order));
        model.addAttribute("orderDetails", orderService.createOrder(order)
                .map(orderReadMapper::map)
                .orElseThrow());
        //clear cart from session and database if client
        cart = cartService.clearCart(cart);
        if (authentication.isAuthenticated()) {
            AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
            if (authUserDetails.getUser().getRole().equals(Role.CLIENT)) {
                cartService.updateCart(cart);
            }
        }
        return "order/confirmed";

    }

    @ExceptionHandler(BookQuantityNotEnoughException.class)
    public ModelAndView handleBookNotEnoughException(BookQuantityNotEnoughException bookQuantityNotEnoughException) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("book", bookQuantityNotEnoughException.getBook());
        modelAndView.setViewName("order/error/notEnoughBookQuantity");
        return modelAndView;
    }


    @ModelAttribute(SessionConstant.ORDER_CHECKOUT_DTO)
    public OrderCheckoutDto orderCheckoutDto() {
        return new OrderCheckoutDto();
    }
}
