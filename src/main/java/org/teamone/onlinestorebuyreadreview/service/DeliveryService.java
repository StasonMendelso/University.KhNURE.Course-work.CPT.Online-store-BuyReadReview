package org.teamone.onlinestorebuyreadreview.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.*;
import org.teamone.onlinestorebuyreadreview.database.repository.DeliveryRepository;
import org.teamone.onlinestorebuyreadreview.dto.delivery.CreateDeliveryDto;
import org.teamone.onlinestorebuyreadreview.dto.delivery.ReadDeliveryDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.delivery.DeliveryCreateMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.delivery.DeliveryReadMapper;

import java.util.ArrayList;
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
    private final DeliveryCreateMapper deliveryCreateMapper;
    private final DeliveryItemService deliveryItemService;

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.read(id);
    }

    public Optional<ReadDeliveryDto> save(CreateDeliveryDto createDeliveryDto){
        Delivery delivery = deliveryCreateMapper.unmap(createDeliveryDto);
        Optional<Delivery> deliveryOptional = deliveryRepository.create(delivery);
        return deliveryOptional.map(deliveryReadMapper::map);
    }
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.readAll();
    }
}
