package org.teamone.onlinestorebuyreadreview.database.mapper.deliveryRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Anastasiia Starukhina
 */

/*    private Long id;
    private Long managerId;
    private Long courierId;
    private Long orderId;
    private String clientWishDescription;
    private LocalDate creationDate;
    private DeliveryRequestStatus deliveryRequestStatus;
    private String descriptionForStatus;*/
@Component
@RequiredArgsConstructor
public class DeliveryRequestRowMapper implements RowMapper<DeliveryRequest> {
    @Override
    public DeliveryRequest mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return DeliveryRequest.builder()
                .id(resultSet.getLong("request_id"))
                .managerId(resultSet.getLong("manager_id"))
                .manager_first_name(resultSet.getString("manager_first_name"))
                .manager_last_name(resultSet.getString("manager_last_name"))
                .courierId(resultSet.getLong("courier_id"))
                .orderId(resultSet.getLong("order_id"))
                .clientWishDescription(resultSet.getString("client_wish_description"))
                .creationDate(resultSet.getDate("creation_date").toLocalDate())
                .deliveryRequestStatus(DeliveryRequestStatus.getInstance(resultSet.getString("request_delivery_status")))
                .descriptionForStatus(resultSet.getString("description_for_status"))
                .build();
    }
}
