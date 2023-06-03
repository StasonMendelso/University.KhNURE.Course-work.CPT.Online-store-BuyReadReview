package org.teamone.onlinestorebuyreadreview.util.mapper.book;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.entity.Publisher;
import org.teamone.onlinestorebuyreadreview.dto.book.CreateBookDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.math.BigDecimal;

/**
 * @author Stanislav Hlova
 */
@Component
public class BookCreateMapper implements Mapper<Book, CreateBookDto> {
    @Override
    public CreateBookDto map(Book object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Book unmap(CreateBookDto createBookDto) {
        return Book.builder()
                .isbn(createBookDto.getIsbn())
                .description(createBookDto.getDescription())
                .article(createBookDto.getArticle())
                .hidden(createBookDto.getHidden())
                .title(createBookDto.getTitle())
                .price(BigDecimal.valueOf(Double.parseDouble(createBookDto.getPrice())))
                .quantity(Integer.valueOf(createBookDto.getQuantity()))
                .paperQuantity(Integer.valueOf(createBookDto.getPaperQuantity()))
                .publisher(Publisher.builder()
                        .name(createBookDto.getPublisherName())
                        .build())
                .authors(createBookDto.getAuthorNames()
                        .stream()
                        .map(fullName -> {
                            String[] splittedName = fullName.split(" ");
                            return Author.builder()
                                    .firstName(splittedName[0])
                                    .lastName(splittedName[1])
                                    .build();
                        })
                        .toList())
                .genres(createBookDto.getGenreNames()
                        .stream()
                        .map(genre -> Genre.builder().name(genre).build())
                        .toList())
                .build();
    }
}
