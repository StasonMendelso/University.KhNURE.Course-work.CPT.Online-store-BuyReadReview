package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.teamone.onlinestorebuyreadreview.service.DeliveryItemService;
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
    private final DeliveryItemService deliveryItemService;
    private final DeliveryReadMapper deliveryReadMapper;
    @GetMapping
    public String viewDeliveries(Model model) {
        model.addAttribute("deliveryList", deliveryService.getAllDeliveries().stream().map(deliveryReadMapper::map).toList());
        return "delivery/deliveries";
    }
    @GetMapping("/{id}")
    public String viewDelivery(@PathVariable("id") Long id, Model model) {
        model.addAttribute("delivery", deliveryService.getDeliveryById(id)
                .map(deliveryReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "delivery/delivery";
    }
}
