package org.teamone.onlinestorebuyreadreview.database.mapper.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.*;
import org.teamone.onlinestorebuyreadreview.database.mapper.deliveryItem.DeliveryItemRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Starukhina Anastasiia
 */
@Component
@RequiredArgsConstructor
public class DeliveryExtractor implements ResultSetExtractor<Delivery> {
    private final DeliveryItemRowMapper deliveryItemRowMapper;
    @Override
    public Delivery extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }
        Delivery delivery = Delivery.builder()
                .id(resultSet.getLong("delivery_id"))
                .requestId(resultSet.getLong("request_id"))
                .courierId(resultSet.getLong("courier_id"))
                .courierTelephoneNumber(resultSet.getString("courier_telephone_number"))
                .descriptionForStatus(resultSet.getString("description_for_status"))
                .deliveryStatus(DeliveryStatus.getInstance(resultSet.getString("delivery_status")))
                .build();
        Set<DeliveryItem> deliveryItems = new HashSet<>();
        do {
            if(!delivery.getId().equals(resultSet.getLong("delivery_id"))){
                resultSet.previous();
                break;
            }
            DeliveryItem deliveryItem = deliveryItemRowMapper.mapRow(resultSet, resultSet.getRow());
            deliveryItems.add(deliveryItem);
        } while (resultSet.next());

        delivery.setDeliveryItems(deliveryItems.stream().toList());
        return delivery;
    }
}
