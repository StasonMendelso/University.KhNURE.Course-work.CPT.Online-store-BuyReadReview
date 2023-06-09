package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return OrderItem.builder()
                .orderId(resultSet.getLong("order_id"))
                .price(resultSet.getBigDecimal("order_item_price"))
                .quantity(resultSet.getInt("order_item_quantity"))
                .build();
    }
}
