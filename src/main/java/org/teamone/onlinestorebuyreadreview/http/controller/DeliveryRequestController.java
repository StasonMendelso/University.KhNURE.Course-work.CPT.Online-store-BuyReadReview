package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.teamone.onlinestorebuyreadreview.service.DeliveryRequestService;
import org.teamone.onlinestorebuyreadreview.util.mapper.deliveryRequest.DeliveryRequestReadMapper;

/**
 * @author Anastasiia Starukhina
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/deliveryRequests")
public class DeliveryRequestController {
    private final DeliveryRequestService deliveryRequestService;
    private final DeliveryRequestReadMapper deliveryRequestReadMapper;

    @GetMapping
    public String viewDeliveryRequests(Model model) {
        model.addAttribute("deliveryRequestList",
                deliveryRequestService.getAllDeliveryRequests().stream()
                    .map(deliveryRequestReadMapper::map).toList());
        return "deliveryRequest/deliveryRequests";
    }
    @GetMapping("/{id}")
    public String viewDeliveryRequest(@PathVariable("id") Long id, Model model) {
        model.addAttribute("deliveryRequest", deliveryRequestService.getDeliveryRequestById(id)
                .map(deliveryRequestReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "deliveryRequest/deliveryRequest";
    }
}
