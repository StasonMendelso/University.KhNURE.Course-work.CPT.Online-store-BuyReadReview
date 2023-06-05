package org.teamone.onlinestorebuyreadreview.util.mapper.book;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;
import org.teamone.onlinestorebuyreadreview.dto.book.EditBookDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Stanislav Hlova
 */
@Component
public class BookEditMapper implements Mapper<Book, EditBookDto> {
    @Override
    public EditBookDto map(Book book) {
        return EditBookDto.builder()
                .id(book.getId().toString())
                .isbn(book.getIsbn())
                .paperQuantity(String.valueOf(book.getPaperQuantity()))
                .price(String.valueOf(book.getPrice()))
                .publisherName(book.getPublisher().getName())
                .title(book.getTitle())
                .hidden(book.getHidden())
                .article(book.getArticle())
                .quantity(String.valueOf(book.getQuantity()))
                .description(book.getDescription())
                .authorNames(book.getAuthors()
                        .stream()
                        .map(Author::getFullName)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .genreNames(book.getGenres()
                        .stream()
                        .map(Genre::getName)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }

    @Override
    public Book unmap(EditBookDto editBookDto) {
        return Book.builder()
                .id(Long.valueOf(editBookDto.getId()))
                .isbn(editBookDto.getIsbn())
                .description(editBookDto.getDescription())
                .article(editBookDto.getArticle())
                .hidden(editBookDto.getHidden())
                .title(editBookDto.getTitle())
                .price(BigDecimal.valueOf(Double.parseDouble(editBookDto.getPrice())))
                .quantity(Integer.valueOf(editBookDto.getQuantity()))
                .paperQuantity(Integer.valueOf(editBookDto.getPaperQuantity()))
                .publisher(Publisher.builder()
                        .name(editBookDto.getPublisherName())
                        .build())
                .authors(editBookDto.getAuthorNames()
                        .stream()
                        .map(fullName -> {
                            String[] splittedName = fullName.split(" ");
                            return Author.builder()
                                    .firstName(splittedName[0])
                                    .lastName(splittedName[1])
                                    .build();
                        })
                        .toList())
                .genres(editBookDto.getGenreNames()
                        .stream()
                        .map(genre -> Genre.builder().name(genre).build())
                        .toList())
                .build();
    }
}
