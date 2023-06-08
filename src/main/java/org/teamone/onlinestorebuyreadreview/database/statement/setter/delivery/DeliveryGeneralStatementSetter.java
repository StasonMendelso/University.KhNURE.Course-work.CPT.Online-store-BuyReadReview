package org.teamone.onlinestorebuyreadreview.database.statement.setter.delivery;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.teamone.onlinestorebuyreadreview.database.entity.Delivery;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Anastasiia Starukhina
 */
public class DeliveryGeneralStatementSetter implements PreparedStatementSetter {
    int index;
    final Delivery delivery;

    public DeliveryGeneralStatementSetter(Delivery delivery) {
        this.index = 1;
        this.delivery = delivery;
    }

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {

    }
}
