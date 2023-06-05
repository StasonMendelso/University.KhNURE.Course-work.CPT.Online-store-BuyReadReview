package org.teamone.onlinestorebuyreadreview.database.mapper.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;
import org.teamone.onlinestorebuyreadreview.database.mapper.book.BookExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Starukhina Anastasiia
 */
@Component
@RequiredArgsConstructor
public class ReadDeliveriesExtractor implements ResultSetExtractor<List<Delivery>>{
    private final DeliveryExtractor deliveryExtractor;
    @Override
    public List<Delivery> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Delivery> deliveries = new ArrayList<>();
        do{
            deliveries.add(deliveryExtractor.extractData(resultSet));
        }while (!resultSet.isAfterLast());
        return deliveries;
    }
}
