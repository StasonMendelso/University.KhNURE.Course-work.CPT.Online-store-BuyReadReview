package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamone.onlinestorebuyreadreview.dto.author.ReadAuthorDto;
import org.teamone.onlinestorebuyreadreview.dto.book.CreateBookDto;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.dto.genre.ReadGenreDto;
import org.teamone.onlinestorebuyreadreview.dto.publisher.ReadPublisherDto;
import org.teamone.onlinestorebuyreadreview.service.AuthorService;
import org.teamone.onlinestorebuyreadreview.service.BookService;
import org.teamone.onlinestorebuyreadreview.service.GenreService;
import org.teamone.onlinestorebuyreadreview.service.PublisherService;
import org.teamone.onlinestorebuyreadreview.util.validation.validator.CreateBookDtoValidator;

import java.util.List;
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

    @GetMapping
    public String viewBooks(Model model) {
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") Long id,
                           Model model) {
        model.addAttribute("book", bookService.getBookById(id).get());
        return "book/book";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("createBookDto") CreateBookDto createBookDto,
                          Model model) {
        List<ReadAuthorDto> authorList = authorService.getAuthors();
        List<ReadGenreDto> genreList = genreService.getGenres();
        List<ReadPublisherDto> publisherList = publisherService.getPublishers();

        model.addAttribute("authorList", authorList);
        model.addAttribute("genreList", genreList);
        model.addAttribute("publisherList", publisherList);
        model.addAttribute("publisherNameList", publisherList.stream().map(ReadPublisherDto::getName).toList());
        model.addAttribute("createBookDto", createBookDto);
        model.addAttribute("newInputtedAuthors", createBookDto.getAuthorNames().stream().filter(fullName -> authorList.stream().map(readAuthorDto -> readAuthorDto.getLastName() + " " + readAuthorDto.getFirstName()).noneMatch(s -> s.equals(fullName))).toList());
        model.addAttribute("newInputtedGenres", createBookDto.getGenreNames().stream().filter(genre -> genreList.stream().map(ReadGenreDto::getName).noneMatch(s -> s.equals(genre))).toList());

        return "book/addBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("createBookDto") @Validated CreateBookDto createBookDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        createBookDtoValidator.validate(createBookDto,bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createBookDto", createBookDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult);

            return "redirect:/books/new";
        }
        Optional<ReadBookDto> savedBook = bookService.save(createBookDto);

        return savedBook.map(readBookDto -> "redirect:/books/" + readBookDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
