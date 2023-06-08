package org.teamone.onlinestorebuyreadreview.database.mapper.deliveryRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Anastasiia Starukhina
 */
@Component
@RequiredArgsConstructor
public class ReadDeliveryRequestsExtractor implements ResultSetExtractor<List<DeliveryRequest>> {
    private final DeliveryRequestExtractor deliveryRequestExtractor;

    @Override
    public List<DeliveryRequest> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(!resultSet.isBeforeFirst())
            return Collections.emptyList();
        List<DeliveryRequest> deliveryRequests = new ArrayList<>();
        do{
            deliveryRequests.add(deliveryRequestExtractor.extractData(resultSet));
        }while(!resultSet.isAfterLast());
        return deliveryRequests;
    }
}
