package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.dto.author.ReadAuthorDto;
import org.teamone.onlinestorebuyreadreview.dto.book.CreateBookDto;
import org.teamone.onlinestorebuyreadreview.dto.book.EditBookDto;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.dto.genre.ReadGenreDto;
import org.teamone.onlinestorebuyreadreview.dto.publisher.ReadPublisherDto;
import org.teamone.onlinestorebuyreadreview.service.AuthorService;
import org.teamone.onlinestorebuyreadreview.service.BookService;
import org.teamone.onlinestorebuyreadreview.service.GenreService;
import org.teamone.onlinestorebuyreadreview.service.PublisherService;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookEditMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookReadMapper;
import org.teamone.onlinestorebuyreadreview.util.validation.validator.book.CreateBookDtoValidator;
import org.teamone.onlinestorebuyreadreview.util.validation.validator.book.EditBookDtoValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublisherService publisherService;
    private final CreateBookDtoValidator createBookDtoValidator;
    private final EditBookDtoValidator editBookDtoValidator;
    private final BookReadMapper bookReadMapper;
    private final BookEditMapper bookEditMapper;

    @GetMapping
    public String viewBooks(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks()
                .stream()
                .map(bookReadMapper::map)
                .toList());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("book", bookService.getBookById(id)
                .map(bookReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "book/book";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("createBookDto") CreateBookDto createBookDto,
                          Model model) {
        List<ReadAuthorDto> authorList = authorService.getAuthors();
        List<ReadGenreDto> genreList = genreService.getGenres();
        List<ReadPublisherDto> publisherList = publisherService.getPublishers();

        model.addAllAttributes(Map.of(
                "authorList", authorList,
                "genreList", genreList,
                "publisherList", publisherList,
                "publisherNameList", publisherList.stream().map(ReadPublisherDto::getName).toList(),
                "newInputtedAuthors", createBookDto.getAuthorNames().stream().filter(fullName -> authorList.stream().map(readAuthorDto -> readAuthorDto.getLastName() + " " + readAuthorDto.getFirstName()).noneMatch(s -> s.equals(fullName))).toList(),
                "newInputtedGenres", createBookDto.getGenreNames().stream().filter(genre -> genreList.stream().map(ReadGenreDto::getName).noneMatch(s -> s.equals(genre))).toList(),
                "createBookDto", createBookDto));

        return "book/addBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("createBookDto") @Validated CreateBookDto createBookDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        createBookDtoValidator.validate(createBookDto, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createBookDto", createBookDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult);

            return "redirect:/books/new";
        }
        Optional<ReadBookDto> savedBook = bookService.save(createBookDto);

        return savedBook.map(readBookDto -> "redirect:/books/" + readBookDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Long id,
                           @ModelAttribute("editBookDto") EditBookDto editBookDto,
                           Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!model.containsAttribute("errors")) {
            editBookDto = bookEditMapper.map(book);
        }
        List<ReadAuthorDto> authorList = authorService.getAuthors();
        List<ReadGenreDto> genreList = genreService.getGenres();
        List<ReadPublisherDto> publisherList = publisherService.getPublishers();

        model.addAllAttributes(Map.of(
                "authorList", authorList,
                "genreList", genreList,
                "publisherList", publisherList,
                "publisherNameList", publisherList.stream().map(ReadPublisherDto::getName).toList(),
                "newInputtedAuthors", editBookDto.getAuthorNames().stream().filter(fullName -> authorList.stream().map(readAuthorDto -> readAuthorDto.getLastName() + " " + readAuthorDto.getFirstName()).noneMatch(s -> s.equals(fullName))).toList(),
                "newInputtedGenres", editBookDto.getGenreNames().stream().filter(genre -> genreList.stream().map(ReadGenreDto::getName).noneMatch(s -> s.equals(genre))).toList(),
                "editBookDto", editBookDto,
                "bookTitle", book.getTitle()));

        book.getFiles().stream().findFirst().ifPresent(file ->
                model.addAttribute("firstBookFileId", file.getId())
        );

        return "book/editBook";
    }

    @PatchMapping
    public String updateBook(@ModelAttribute("editBookDto") @Validated EditBookDto editBookDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        editBookDtoValidator.validate(editBookDto, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editBookDto", editBookDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult);

            return "redirect:/books/" + editBookDto.getId() + "/edit";
        }

        Optional<ReadBookDto> updatedBook = bookService.update(editBookDto);

        return updatedBook.map(readBookDto -> "redirect:/books/" + editBookDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
