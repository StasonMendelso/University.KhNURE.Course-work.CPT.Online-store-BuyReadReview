package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderStatus;
import org.teamone.onlinestorebuyreadreview.database.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.readAll();
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.read(id);
    }

    @Transactional
    public OrderStatus updateOrderStatus(Long orderId, OrderStatus newStatus) {
        orderRepository.updateOrderStatus(orderId, orderRepository.getOrderStatusId(newStatus)
                .orElseThrow(() -> new RuntimeException("Can't find order status")));
        return newStatus;

    }

    @Transactional
    public void updateOrderStatusDescription(Long orderId, String description) {
        orderRepository.updateOrderStatusDescription(orderId,description);
    }
}
