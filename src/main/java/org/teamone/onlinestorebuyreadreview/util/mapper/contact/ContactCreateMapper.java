package org.teamone.onlinestorebuyreadreview.util.mapper.contact;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Contact;
import org.teamone.onlinestorebuyreadreview.dto.order.CreateContactDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class ContactCreateMapper implements Mapper<CreateContactDto, Contact> {
    @Override
    public Contact map(CreateContactDto contactDto) {
        return Contact.builder()
                .firstName(contactDto.getFirstName())
                .middleName(contactDto.getMiddleName())
                .lastName(contactDto.getLastName())
                .email(contactDto.getEmail())
                .telephoneNumber(contactDto.getTelephoneNumber())
                .build();
    }
}
