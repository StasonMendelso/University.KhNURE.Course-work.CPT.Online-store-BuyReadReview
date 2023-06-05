package org.teamone.onlinestorebuyreadreview.database.mapper.deliveryItem;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.entity.File;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Starukhina Anastasiia
 */
@Component
public class DeliveryItemRowMapper implements RowMapper<DeliveryItem> {
    @Override
    public DeliveryItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return DeliveryItem.builder()
                .bookId(resultSet.getLong("book_id"))
                .deliveryId(resultSet.getLong("delivery_id"))
                .price(resultSet.getBigDecimal("price"))
                .quantity(resultSet.getInt("quantity"))
                .bookTitle(resultSet.getString("book_title"))
                .build();
    }
}
