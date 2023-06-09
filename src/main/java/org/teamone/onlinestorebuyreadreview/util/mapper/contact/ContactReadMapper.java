package org.teamone.onlinestorebuyreadreview.util.mapper.contact;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Contact;
import org.teamone.onlinestorebuyreadreview.dto.contact.ReadContactDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class ContactReadMapper implements Mapper<Contact, ReadContactDto> {
    @Override
    public ReadContactDto map(Contact contact) {
        return ReadContactDto.builder()
                .firstName(contact.getFirstName())
                .middleName(contact.getMiddleName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .telephoneNumber(contact.getTelephoneNumber())
                .build();
    }
}
