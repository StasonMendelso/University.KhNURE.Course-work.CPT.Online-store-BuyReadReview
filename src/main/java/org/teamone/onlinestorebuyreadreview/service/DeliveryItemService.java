package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.repository.DeliveryItemRepository;
import org.teamone.onlinestorebuyreadreview.util.mapper.deliveryItem.DeliveryItemReadMapper;

import java.util.List;

/**
 * @author Starukhina Anastasiia
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryItemService {
    private final DeliveryItemRepository deliveryItemRepository;
    private final DeliveryItemReadMapper deliveryItemReadMapper;

    public List<DeliveryItem> getDeliveryItems(Long orderId){
       return deliveryItemRepository.readAllByOrderId(orderId).stream().toList();
    }

}
