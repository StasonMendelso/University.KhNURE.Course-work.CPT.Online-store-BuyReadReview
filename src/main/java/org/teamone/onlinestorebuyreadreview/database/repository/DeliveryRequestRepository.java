package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;
import org.teamone.onlinestorebuyreadreview.database.mapper.deliveryRequest.DeliveryRequestExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.deliveryRequest.ReadDeliveryRequestsExtractor;
import org.teamone.onlinestorebuyreadreview.database.statement.creator.PrepareStatementCreatorWithScrolledResultSet;

import java.util.List;
import java.util.Optional;

/**
 * @author Anastasiia Starukhina
 */
@Repository
@RequiredArgsConstructor
public class DeliveryRequestRepository implements CrudRepository<Long, DeliveryRequest>{
    private final JdbcTemplate jdbcTemplate;
    private final DeliveryRequestExtractor deliveryRequestExtractor;
    private final ReadDeliveryRequestsExtractor readDeliveryRequestsExtractor;
    @Override
    public Optional<DeliveryRequest> create(DeliveryRequest entity) {
        return Optional.empty();
    }


    @Override
    public Optional<DeliveryRequest> update(Long id, DeliveryRequest entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DeliveryRequest> readAll() {
        return jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet(
                        "SELECT delivery_request.id AS 'request_id', \n" +
                        "delivery_request.manager_id,\n" +
                        "user.first_name AS 'manager_first_name',\n" +
                        "user.last_name AS 'manager_last_name', \n" +
                        "delivery_request.courier_id, \n" +
                        "delivery_request.order_id, \n" +
                        "delivery_request.client_wish_description, \n" +
                        "delivery_request.creation_date,\n" +
                        "delivery_request_status.request_delivery_status,\n" +
                        "delivery_request.description_for_status\n" +
                        "FROM delivery_request\n" +
                        "LEFT JOIN user ON delivery_request.manager_id = user.id\n" +
                        "LEFT JOIN delivery_request_status ON delivery_request_status.id \n" +
                        "= delivery_request.delivery_request_status_id"),
                readDeliveryRequestsExtractor);
    }

    @Override
    public Optional<DeliveryRequest> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(
                new PrepareStatementCreatorWithScrolledResultSet(
                        "SELECT delivery_request.id AS 'request_id', \n" +
                                "delivery_request.manager_id,\n" +
                                "user.first_name AS 'manager_first_name',\n" +
                                "user.last_name AS 'manager_last_name', \n" +
                                "delivery_request.courier_id, \n" +
                                "delivery_request.order_id, \n" +
                                "delivery_request.client_wish_description, \n" +
                                "delivery_request.creation_date,\n" +
                                "delivery_request_status.request_delivery_status,\n" +
                                "delivery_request.description_for_status\n" +
                                "FROM delivery_request\n" +
                                "LEFT JOIN user ON delivery_request.manager_id = user.id\n" +
                                "LEFT JOIN delivery_request_status ON delivery_request_status.id \n" +
                                "= delivery_request.delivery_request_status_id\n" +
                                "WHERE delivery_request.id = ?"),
                preparedStatement -> preparedStatement.setLong(1, id),
                deliveryRequestExtractor));
    }
}
