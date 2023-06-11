package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryInfo;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderItem;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderStatus;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentInfo;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentStatus;
import org.teamone.onlinestorebuyreadreview.database.entity.ShopDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.ShopDeliveryStatus;
import org.teamone.onlinestorebuyreadreview.database.repository.BookRepository;
import org.teamone.onlinestorebuyreadreview.database.repository.OrderRepository;
import org.teamone.onlinestorebuyreadreview.service.exception.BookQuantityNotEnoughException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private final BookRepository bookRepository;


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
        orderRepository.updateOrderStatusDescription(orderId, description);
    }

    @Transactional
    public Optional<Order> createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.WAITING_FOR_REVIEWING);
        PaymentInfo paymentInfo = order.getPaymentInfo();
        paymentInfo.setPaymentStatus(PaymentStatus.UN_PAYED);
        DeliveryInfo deliveryInfo = order.getDeliveryInfo();
        if (deliveryInfo.getDeliveryMethod().equals(DeliveryMethod.BY_SELF_PICKUP)) {
            ShopDelivery shopDelivery = (ShopDelivery) deliveryInfo.getDeliveryType();
            shopDelivery.setShopDeliveryStatus(ShopDeliveryStatus.ON_WAREHOUSE);
        }

        List<Long> bookIdList = order.getOrderItems()
                .stream()
                .map(orderItem -> orderItem.getBook().getId())
                .toList();
        List<Book> books = bookRepository.readAllById(bookIdList);

        for (OrderItem orderItem : order.getOrderItems()) {
            Long bookId = orderItem.getBook().getId();
            for (Book book : books) {
                if (book.getId().equals(bookId)) {
                    if (book.getQuantity() < orderItem.getQuantity()) {
                        throw new BookQuantityNotEnoughException(book);
                    }
                }
            }
        }

        return orderRepository.create(order);
    }

    public BigDecimal calculateTotal(Order order) {
        BigDecimal result;
        BigDecimal totalAmountForOrderItems = order.getOrderItems()
                .stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal::add)
                .get();
        result = totalAmountForOrderItems.subtract(BigDecimal.valueOf(order.getUsedBonuses()));
        if (order.getDeliveryInfo().getDeliveryMethod().equals(DeliveryMethod.BY_COURIER)) {
            result = result.add(BigDecimal.valueOf(50));
        }
        return result;
    }

    public List<Order> getAllOrdersByClientId(Long clientId) {
        return orderRepository.readAllByClientId(clientId);
    }
}
