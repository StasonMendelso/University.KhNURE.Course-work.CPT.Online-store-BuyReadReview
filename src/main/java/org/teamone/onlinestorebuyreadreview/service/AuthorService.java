package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.repository.AuthorRepository;
import org.teamone.onlinestorebuyreadreview.dto.author.ReadAuthorDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.author.AuthorReadMapper;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorReadMapper authorReadMapper;

    public List<ReadAuthorDto> getAuthors() {
        return authorRepository.readAll()
                .stream()
                .map(authorReadMapper::map)
                .toList();
    }

    public List<Author> getAuthorsByFullName(List<String> authorNames) {
        return authorRepository.readAllByFullName(authorNames);
    }

    @Transactional
    public List<Author> saveAuthors(List<Author> authorsForAdding) {
        return authorRepository.create(authorsForAdding);
    }
}
