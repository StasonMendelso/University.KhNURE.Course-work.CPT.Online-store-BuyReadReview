package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.mapper.delivery.DeliveryExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.delivery.ReadDeliveriesExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.BatchPreparedStatementSetterWithBatchSize;
import org.teamone.onlinestorebuyreadreview.database.statement.setter.delivery.DeliveryInsertStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Optional<Delivery> create(Delivery delivery) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO delivery(request_id, courier_id, \n" +
                    "courier_telephone_number, description_for_status, \n" +
                    "delivery_status_id) VALUE (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            new DeliveryInsertStatementSetter(delivery).setValues(preparedStatement);
            return preparedStatement;
        }, keyHolder);
        Long generatedKey = keyHolder.getKey().longValue();
        insertIntoDeliveryItem(generatedKey, delivery.getDeliveryItems());
        return read(generatedKey);
    }
    private void insertIntoDeliveryItem(Long deliveryId, List<DeliveryItem> items) {
        jdbcTemplate.batchUpdate("INSERT INTO delivery_item(book_id, delivery_id, price, quantity, book_title) VALUE (?,?,?,?,?)", new BatchPreparedStatementSetterWithBatchSize(items.size()) {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                int index = 1;
                preparedStatement.setLong(index++, deliveryId);
                preparedStatement.setLong(index++, items.get(i).getDeliveryId());
                preparedStatement.setBigDecimal(index++, items.get(i).getPrice());
                preparedStatement.setLong(index++, items.get(i).getQuantity());
                preparedStatement.setString(index++, items.get(i).getBookTitle());
            }

        });
    }

    public Optional<Delivery> update(/*Long deliveryId, Delivery delivery*/) {
        return Optional.empty();
    }

    @Override
    public Optional<Delivery> update(Long id, Delivery entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
    @Override
    public Optional<Delivery> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet(
                        "SELECT delivery.id AS 'delivery_id',delivery.request_id, delivery.courier_id, \n" +
                        "delivery.creation_date, delivery.courier_telephone_number, delivery.description_for_status, \n" +
                        "delivery_status.courier_delivery_status AS 'delivery_status',\n" +
                        "delivery_item.book_id, delivery_item.price, delivery_item.quantity, delivery_item.book_title \n" +
                        "FROM delivery LEFT JOIN delivery_status ON delivery.delivery_status_id \n" +
                        "= delivery_status.id \n" +
                        "LEFT JOIN delivery_item ON delivery.id = delivery_item.delivery_id\n" +
                        "WHERE delivery.id = ?"),
                preparedStatement -> preparedStatement.setLong(1, id),
                deliveryExtractor));
    }

    @Override
    public List<Delivery> readAll() {
        return jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet("SELECT delivery.id AS 'delivery_id', delivery.request_id, delivery.courier_id, \n" +
                        "delivery.creation_date, delivery.courier_telephone_number, delivery.description_for_status, \n" +
                        "delivery_status.courier_delivery_status AS 'delivery_status', delivery_item.book_id,\n" +
                        "delivery_item.price, delivery_item.quantity, delivery_item.book_title FROM delivery\n" +
                        "LEFT JOIN delivery_status ON delivery.delivery_status_id = delivery_status.id\n" +
                        "LEFT JOIN delivery_item ON delivery.id = delivery_item.delivery_id"),
                readDeliveriesExtractor);
    }
}
