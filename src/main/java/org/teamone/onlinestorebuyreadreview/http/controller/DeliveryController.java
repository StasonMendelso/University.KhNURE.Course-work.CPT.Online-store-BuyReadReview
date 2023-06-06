package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teamone.onlinestorebuyreadreview.service.DeliveryService;
import org.teamone.onlinestorebuyreadreview.util.mapper.delivery.DeliveryReadMapper;

/**
 * @author Starukhina Anastasiia
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryReadMapper deliveryReadMapper;
    @GetMapping
    public String viewBooks(Model model) {
        model.addAttribute("deliveryList", deliveryService.getAllDeliveries().stream().map(deliveryReadMapper::map).toList());
        return "delivery/deliveries";
    }
}
