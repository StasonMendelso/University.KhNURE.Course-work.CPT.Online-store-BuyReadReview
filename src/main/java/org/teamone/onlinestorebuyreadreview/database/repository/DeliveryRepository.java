package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.BookExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.ReadBooksExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.delivery.DeliveryExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.delivery.ReadDeliveriesExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;

import java.util.List;
import java.util.Optional;

/**
 * @author Starukhina Anastasiia
 */
@Repository
@RequiredArgsConstructor
public class DeliveryRepository implements CrudRepository<Long, Delivery>{
    private final JdbcTemplate jdbcTemplate;

    private final DeliveryExtractor deliveryExtractor;
    private final ReadDeliveriesExtractor readDeliveriesExtractor;

    @Override
    public Optional<Delivery> create(Delivery entity) {
        return Optional.empty();
    }


    @Override
    public Optional<Delivery> update(Long id, Delivery entity) {
        return Optional.empty();
    }

    public Optional<Delivery> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT delivery.id AS 'delivery_id', delivery.courier_id, " +
                        "delivery.courier_telephone_number,\n" +
                        "delivery.description_for_status, delivery_status.courier_delivery_status AS 'delivery_status'\n" +
                        "FROM delivery LEFT JOIN delivery_status ON delivery.delivery_status_id = delivery_status.id WHERE delivery.id = ?"),
                preparedStatement -> preparedStatement.setLong(1, id),
                deliveryExtractor));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Delivery> readAll() {
        return jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT delivery.id AS 'delivery_id', delivery.courier_id, " +
                        "delivery.courier_telephone_number, delivery.description_for_status, " +
                        "delivery_status.courier_delivery_status AS 'delivery_status' FROM delivery " +
                        "LEFT JOIN delivery_status ON delivery.delivery_status_id = delivery_status.id"),
                readDeliveriesExtractor);
    }
}
