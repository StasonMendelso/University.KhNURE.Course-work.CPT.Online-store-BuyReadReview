package org.teamone.onlinestorebuyreadreview.database.mapper.deliveryRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryRequest;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Anastasiia Starukhina
 */
@Component
@RequiredArgsConstructor
public class DeliveryRequestExtractor implements ResultSetExtractor<DeliveryRequest> {
    private final DeliveryRequestRowMapper deliveryRequestRowMapper;
    @Override
    public DeliveryRequest extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if(!resultSet.next()){
            return null;
        }
        return deliveryRequestRowMapper.mapRow(resultSet, resultSet.getRow());
    }
}
