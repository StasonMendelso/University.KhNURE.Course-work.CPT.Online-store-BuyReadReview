package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;
import org.teamone.onlinestorebuyreadreview.database.repository.PublisherRepository;
import org.teamone.onlinestorebuyreadreview.dto.publisher.ReadPublisherDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.publisher.PublisherReadMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherReadMapper publisherReadMapper;

    public List<ReadPublisherDto> getPublishers() {
        return publisherRepository.readAll()
                .stream()
                .map(publisherReadMapper::map)
                .toList();
    }

    public Optional<Publisher> getPublisherByName(String name) {
        return publisherRepository.readByName(name);
    }
    @Transactional
    public Optional<Publisher> save(Publisher publisher) {
        return publisherRepository.create(publisher);
    }
}
