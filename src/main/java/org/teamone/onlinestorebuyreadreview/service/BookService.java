package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.teamone.onlinestorebuyreadreview.database.entity.Author;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.repository.BookRepository;
import org.teamone.onlinestorebuyreadreview.dto.book.CreateBookDto;
import org.teamone.onlinestorebuyreadreview.dto.book.EditBookDto;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookCreateMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookEditMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookReadMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;
    private final BookCreateMapper bookCreateMapper;
    private final BookEditMapper bookEditMapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final GenreService genreService;
    private final FileService fileService;

    public Optional<Book> getBookById(Long id) {
        return bookRepository.read(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.readAll();
    }

    @Transactional
    public Optional<ReadBookDto> save(CreateBookDto createBookDto) {
        Book book = bookCreateMapper.unmap(createBookDto);

        saveOrUpdateBookData(book, createBookDto.getAuthorNames(), createBookDto.getGenreNames());

        Optional<Book> bookOptional = bookRepository.create(book);

        if (bookOptional.isPresent()) {
            for (MultipartFile multipartFile : createBookDto.getMultipartFiles()) {
                if (!multipartFile.isEmpty()) {
                    fileService.uploadBookFile(bookOptional.get(), multipartFile);
                }
            }
        }

        return bookOptional.map(bookReadMapper::map);
    }

    public List<String> getAllIsbn() {
        return bookRepository.readAllIsbn();
    }

    public List<String> getAllArticle() {
        return bookRepository.readAllArticle();
    }

    public Optional<String> getIsbnByBookId(Long bookId) {
        return bookRepository.readIsbnByBookId(bookId);
    }

    public Optional<String> getArticleByBookId(Long bookId) {
        return bookRepository.readArticleByBookId(bookId);
    }

    @Transactional
    public Optional<ReadBookDto> update(EditBookDto editBookDto) {
        Book book = bookEditMapper.unmap(editBookDto);

        saveOrUpdateBookData(book, editBookDto.getAuthorNames(), editBookDto.getGenreNames());

        Optional<Book> bookOptional = bookRepository.update(book.getId(), book);

        return bookOptional.map(bookReadMapper::map);
    }

    private void saveOrUpdateBookData(Book book, ArrayList<String> authorNames, ArrayList<String> genreNames) {

        List<Author> authors = authorService.getAuthorsByFullName(authorNames);
        List<Author> authorsForAdding = new ArrayList<>();
        for (String authorFullName : authorNames) {
            if (authors.stream().map(Author::getFullName).noneMatch(s -> s.equals(authorFullName))) {
                authorsForAdding.add(Author.builder().fullName(authorFullName).build());
            }
        }
        if (!authorsForAdding.isEmpty()) {
            List<Author> addedAuthors = authorService.saveAuthors(authorsForAdding);
            authors.addAll(addedAuthors);
        }
        book.setAuthors(authors);

        List<Genre> genres = genreService.getGenresByName(genreNames);
        List<Genre> genresForAdding = new ArrayList<>();
        for (String genreName : genreNames) {
            if (genres.stream().map(Genre::getName).noneMatch(s -> s.equals(genreName))) {
                genresForAdding.add(Genre.builder().name(genreName).build());
            }
        }
        if (!genresForAdding.isEmpty()) {
            List<Genre> addedGenres = genreService.saveGenres(genresForAdding);
            genres.addAll(addedGenres);
        }
        book.setGenres(genres);

        book.setPublisher(publisherService.getPublisherByName(book.getPublisher().getName())
                .orElseGet(() -> publisherService.save(book.getPublisher())
                        .orElseThrow()));
    }

    @Transactional
    public Optional<Book> save(Book book) {
        return bookRepository.create(book);
    }

    public Optional<Book> getBookByTitle(String title) {return bookRepository.readByTitle(title);}

    public List<ReadBookDto> getBooks(){
        return bookRepository.readAll()
                .stream()
                .map(bookReadMapper::map)
                .toList();
    }
}
