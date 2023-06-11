package org.teamone.onlinestorebuyreadreview.util.mapper.author;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.dto.author.ReadAuthorDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class AuthorReadMapper implements Mapper<Author, ReadAuthorDto> {
    @Override
    public ReadAuthorDto map(Author author) {
        return ReadAuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .pseudonym(author.getPseudonym())
                .build();
    }

    @Override
    public Author unmap(ReadAuthorDto readAuthorDto) {
        return Author.builder()
                .id(readAuthorDto.getId())
                .pseudonym(readAuthorDto.getPseudonym())
                .firstName(readAuthorDto.getFirstName())
                .lastName(readAuthorDto.getLastName())
                .build();
    }
}
