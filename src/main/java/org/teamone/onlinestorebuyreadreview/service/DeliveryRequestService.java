package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;
import org.teamone.onlinestorebuyreadreview.database.repository.DeliveryRequestRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Anastasiia Starukhina
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryRequestService {
    private final DeliveryRequestRepository deliveryRequestRepository;

    public Optional<DeliveryRequest> getDeliveryRequestById(Long id) {
        return deliveryRequestRepository.read(id);
    }

    public List<DeliveryRequest> getAllDeliveryRequests() {
        return deliveryRequestRepository.readAll();
    }
}
