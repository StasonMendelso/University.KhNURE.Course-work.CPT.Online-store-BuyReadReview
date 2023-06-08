package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.mapper.deliveryItem.DeliveryItemExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.deliveryItem.DeliveryItemRowMapper;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

/**
 * @author Starukhina Anastasiia
 */
@Repository
@RequiredArgsConstructor
public class DeliveryItemRepository implements CrudRepository<Long, DeliveryItem> {
    private final JdbcTemplate jdbcTemplate;
    private final DeliveryItemExtractor deliveryItemExtractor;
    private final DeliveryItemRowMapper deliveryItemRowMapper;

    @Override
    public Optional<DeliveryItem> create(DeliveryItem entity) {
        return Optional.empty();
    }

    @Override
    public Optional<DeliveryItem> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<DeliveryItem> update(Long id, DeliveryItem entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DeliveryItem> readAll() {
        return jdbcTemplate.query("SELECT book_id, delivery_id, price, quantity, " +
                "book_title FROM delivery_item ORDER BY delivery_id", deliveryItemRowMapper);
    }

    public List<DeliveryItem> readAllByDeliveryId(Long deliveryId) {
        return jdbcTemplate.query("SELECT book_id, delivery_id, price, quantity,"+
                "book_title FROM delivery_item ORDER BY delivery_id"+
                "WHERE delivery_id = ?", deliveryItemRowMapper, deliveryId);
    }

    public List<DeliveryItem> readAllByOrderId(Long orderId){
        return jdbcTemplate.query("SELECT order_item.book_id,\n" +
                "delivery.id AS 'delivery_id',\n" +
                "price, order_item.quantity, \n" +
                "order_item.title\n" +
                "FROM order_item\n" +
                "LEFT JOIN  course_work_tkp.order ON \n" +
                "course_work_tkp.order.id = order_item.order_id\n" +
                "LEFT JOIN delivery_request ON \n" +
                "delivery_request.order_id = course_work_tkp.order.id\n" +
                "LEFT JOIN delivery ON \n" +
                "delivery.request_id = delivery_request.id\n" +
                "WHERE course_work_tkp.order.id = ?", deliveryItemRowMapper, orderId);
    }
}
