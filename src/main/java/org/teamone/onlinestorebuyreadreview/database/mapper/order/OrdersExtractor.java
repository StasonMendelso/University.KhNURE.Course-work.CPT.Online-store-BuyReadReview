package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class OrdersExtractor implements ResultSetExtractor<List<Order>> {
    private final OrderExtractor orderExtractor;
    @Override
    public List<Order> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.isBeforeFirst()) {
            return Collections.emptyList();
        }
        List<Order> orders = new ArrayList<>();
        do {
            orders.add(orderExtractor.extractData(resultSet));
        } while (!resultSet.isAfterLast());
        return orders;
    }
}
