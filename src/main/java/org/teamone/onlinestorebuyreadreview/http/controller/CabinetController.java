package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.teamone.onlinestorebuyreadreview.security.details.AuthUserDetails;
import org.teamone.onlinestorebuyreadreview.service.OrderService;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderReadMapper;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/cabinet")
@RequiredArgsConstructor
public class CabinetController {
    private final OrderService orderService;
    private final OrderReadMapper orderReadMapper;

    @GetMapping("/orders")
    public String getOrders(Model model, Authentication authentication) {

        AuthUserDetails authUserDetails = (AuthUserDetails) authentication.getPrincipal();
        model.addAttribute("orderList", orderService.getAllOrdersByClientId(authUserDetails.getUser().getId())
                .stream()
                .map(orderReadMapper::map)
                .toList());
        return "cabinet/orders";
    }

    @GetMapping("/orders/{id}")
    public String getOrder(@PathVariable("id") Long orderId, Model model) {
        model.addAttribute("orderDetails",orderService.getOrder(orderId)
                .map(orderReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "cabinet/order";
    }
}
