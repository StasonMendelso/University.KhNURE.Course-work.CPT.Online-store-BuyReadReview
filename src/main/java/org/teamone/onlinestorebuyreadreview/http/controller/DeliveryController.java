package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamone.onlinestorebuyreadreview.dto.book.CreateBookDto;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.dto.delivery.CreateDeliveryDto;
import org.teamone.onlinestorebuyreadreview.service.DeliveryItemService;
import org.teamone.onlinestorebuyreadreview.service.DeliveryService;
import org.teamone.onlinestorebuyreadreview.util.mapper.delivery.DeliveryReadMapper;

import java.util.Optional;

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
        model.addAttribute("deliveryList",
                deliveryService.getAllDeliveries().stream().
                        map(deliveryReadMapper::map).toList());
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
