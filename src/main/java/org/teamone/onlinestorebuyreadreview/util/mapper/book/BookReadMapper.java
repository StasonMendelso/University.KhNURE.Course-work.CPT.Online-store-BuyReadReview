package org.teamone.onlinestorebuyreadreview.util.mapper.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.author.AuthorReadMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.file.FileReadMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.genre.GenreReadMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.publisher.PublisherReadMapper;

/**
 * @author Stanislav Hlova
 */
@RequiredArgsConstructor
@Component
public class BookReadMapper implements Mapper<Book, ReadBookDto> {
    private final PublisherReadMapper publisherReadMapper;
    private final AuthorReadMapper authorReadMapper;
    private final GenreReadMapper genreReadMapper;
    private final FileReadMapper fileReadMapper;

    @Override
    public ReadBookDto map(Book book) {
        return ReadBookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .article(book.getArticle())
                .price(book.getPrice())
                .title(book.getTitle())
                .paperQuantity(book.getPaperQuantity())
                .quantity(book.getQuantity())
                .description(book.getDescription())
                .hidden(book.getHidden())
                .publisher(publisherReadMapper.map(book.getPublisher()))
                .authors(book.getAuthors().stream().map(authorReadMapper::map).toList())
                .genres(book.getGenres().stream().map(genreReadMapper::map).toList())
                .files(book.getFiles().stream().map(fileReadMapper::map).toList())
                .build();
    }

}
