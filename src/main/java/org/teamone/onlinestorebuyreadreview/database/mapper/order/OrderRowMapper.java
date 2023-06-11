package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Order.builder()
                .id(resultSet.getLong("order_id"))
                .createdAt(resultSet.getTimestamp("order_created_datetime").toLocalDateTime())
                .orderStatus(OrderStatus.getInstance(resultSet.getString("order_status_name")))
                .description(resultSet.getString("order_description"))
                .usedBonuses(resultSet.getInt("order_used_bonuses"))
                .build();
    }
}
