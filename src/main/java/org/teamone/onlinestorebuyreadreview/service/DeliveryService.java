package org.teamone.onlinestorebuyreadreview.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.repository.DeliveryRepository;
import org.teamone.onlinestorebuyreadreview.util.mapper.delivery.DeliveryReadMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Starukhina Anastasiia
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryReadMapper deliveryReadMapper;
    private final DeliveryItemService deliveryItemService;

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.read(id);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.readAll();
    }
}
