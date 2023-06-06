package org.teamone.onlinestorebuyreadreview.database.mapper.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Starukhina Anastasiia
 */
@Component
@RequiredArgsConstructor
public class DeliveryRowMapper implements RowMapper<Delivery> {

    @Override
    public Delivery mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Delivery.builder()
                .id(resultSet.getLong("delivery_id"))
                .requestId((resultSet.getLong("request_id")))
                .courierId(resultSet.getLong("courier_id"))
                .courierTelephoneNumber(resultSet.getString("courier_telephone_number"))
                .descriptionForStatus(resultSet.getString("description_for_status"))
                .deliveryStatus(DeliveryStatus.getInstance(resultSet.getString("delivery_status")))
                .build();
    }
}
