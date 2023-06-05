package org.teamone.onlinestorebuyreadreview.database.mapper.deliveryItem;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryItem;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.mapper.file.FileRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Starukhina Anastasiia
 */
@Component
@RequiredArgsConstructor
public class DeliveryItemExtractor implements ResultSetExtractor<DeliveryItem> {
    private final DeliveryItemRowMapper deliveryItemRowMapper;
    @Override
    public DeliveryItem extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }
        return deliveryItemRowMapper.mapRow(resultSet, 1);
    }
}
