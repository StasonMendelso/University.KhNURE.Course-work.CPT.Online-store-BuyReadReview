package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderStatus;
import org.teamone.onlinestorebuyreadreview.dto.order.EditOrderStatusDescription;
import org.teamone.onlinestorebuyreadreview.dto.order.EditOrderStatusName;
import org.teamone.onlinestorebuyreadreview.service.OrderService;
import org.teamone.onlinestorebuyreadreview.util.mapper.order.OrderReadMapper;

import java.util.Arrays;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderReadMapper orderReadMapper;

    @GetMapping
    public String getAllOrders(Model model) {
//        model.addAttribute("orderList",orderService.getAllOrders()
//                .stream()
//                .map()
//                .toList());
        return "order/orders";
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("orderStatusNameList", Arrays.stream(OrderStatus.values())
                .map(OrderStatus::getName)
                .sorted()
                .toList());
        model.addAttribute("orderDetails",orderService.getOrder(id)
                .map(orderReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "order/order";
    }

    @ResponseBody
    @PatchMapping("/status")
    public ResponseEntity<?> updateOrderStatus(@RequestBody EditOrderStatusName editOrderStatusName){

        OrderStatus orderStatus = orderService.updateOrderStatus(editOrderStatusName.getOrderId(),OrderStatus.getInstance(editOrderStatusName.getStatus()));

        return ResponseEntity.ok()
                .body("{\"response\":\"ok\"}");
    }
    @ResponseBody
    @PatchMapping("/description")
    public ResponseEntity<?> updateOrderStatusDescription(@RequestBody EditOrderStatusDescription editOrderStatusDescription){

        orderService.updateOrderStatusDescription(editOrderStatusDescription.getOrderId(),editOrderStatusDescription.getDescription());

        return ResponseEntity.ok()
                .body(editOrderStatusDescription);
    }
}
