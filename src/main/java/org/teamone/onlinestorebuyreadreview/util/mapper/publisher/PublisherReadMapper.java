package org.teamone.onlinestorebuyreadreview.util.mapper.publisher;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;
import org.teamone.onlinestorebuyreadreview.dto.publisher.ReadPublisherDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class PublisherReadMapper implements Mapper<Publisher, ReadPublisherDto> {
    @Override
    public ReadPublisherDto map(Publisher publisher) {
        return ReadPublisherDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }
}
