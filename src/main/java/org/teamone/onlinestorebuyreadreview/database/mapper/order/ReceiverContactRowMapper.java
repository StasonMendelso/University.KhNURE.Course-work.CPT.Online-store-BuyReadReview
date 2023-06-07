package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class ReceiverContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Contact.builder()
                .email(resultSet.getString("receiver_contact_email"))
                .lastName(resultSet.getString("receiver_contact_last_name"))
                .firstName(resultSet.getString("receiver_contact_first_name"))
                .middleName(resultSet.getString("receiver_contact_middle_name"))
                .telephoneNumber(resultSet.getString("receiver_contact_telephone_number"))
                .build();

    }
}
