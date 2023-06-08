package org.teamone.onlinestorebuyreadreview.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReview;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.CreateBookReviewDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.EditBookReviewDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.ReadBookReviewDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreviewtag.ReadBookReviewTagDto;
import org.teamone.onlinestorebuyreadreview.service.BookReviewService;
import org.teamone.onlinestorebuyreadreview.service.BookReviewTagService;
import org.teamone.onlinestorebuyreadreview.service.BookService;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreview.BookReviewEditMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreview.BookReviewReadMapper;
import org.teamone.onlinestorebuyreadreview.util.validation.validator.bookreview.CreateBookReviewDtoValidator;
import org.teamone.onlinestorebuyreadreview.util.validation.validator.bookreview.EditBookReviewDtoValidator;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Anna Nikolaichuk
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/bookReviews")
public class BookReviewController {
    private final BookReviewService bookReviewService;
    private final BookReviewTagService bookReviewTagService;
    private final BookService bookService;
    private final CreateBookReviewDtoValidator createBookReviewDtoValidator;
    private final EditBookReviewDtoValidator editBookReviewDtoValidator;
    private final BookReviewReadMapper bookReviewReadMapper;
    private final BookReviewEditMapper bookReviewEditMapper;

    @GetMapping
    public String viewBookReviews(Model model) {
        model.addAttribute("bookReviewList", bookReviewService.getAllBookReviews()
                .stream()
                .map(bookReviewReadMapper::map)
                .toList());
        return "book-review/bookReviews";
    }

    @GetMapping("/{id}")
    public String viewBookReview(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookReview", bookReviewService.getBookReviewById(id)
                .map(bookReviewReadMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "book-review/bookReview";
    }

    @GetMapping("/new")
    public String addBookReview(@ModelAttribute("createBookReviewDto") CreateBookReviewDto createBookReviewDto,
                                Model model) {
        List<ReadBookReviewTagDto> tagList = bookReviewTagService.getTags();
        List<ReadBookDto> bookList = bookService.getBooks();

        model.addAllAttributes(Map.of(
                "tagsList", tagList,
                "bookList", bookList,
                "bookTitleList", bookList.stream().map(ReadBookDto::getTitle).toList(),
                "newInputtedTags", createBookReviewDto.getTagNames().stream().filter(tag -> tagList.stream().map(ReadBookReviewTagDto::getName).noneMatch(s -> s.equals(tag))).toList(),
                "createBookReviewDto", createBookReviewDto));
        return "book-review/addBookReview";
    }

    @PostMapping
    public String createBookReview(@ModelAttribute("createBookReviewDto") @Validated CreateBookReviewDto createBookReviewDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        createBookReviewDtoValidator.validate(createBookReviewDto, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createBookReviewDto", createBookReviewDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult);

            return "redirect:/bookReviews/new";
        }
        Optional<ReadBookReviewDto> savedBookReview = bookReviewService.save(createBookReviewDto);
        return savedBookReview.map(readBookReviewDto -> "redirect:/bookReviews/" + readBookReviewDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @GetMapping("/{id}/edit")
    public String editBookReview(@PathVariable("id") Long id,
                                 @ModelAttribute("editBookReviewDto") EditBookReviewDto editBookReviewDto,
                                 Model model) {
        BookReview bookReview = bookReviewService.getBookReviewById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!model.containsAttribute("errors")) {
            editBookReviewDto = bookReviewEditMapper.map(bookReview);
        }
        List<ReadBookReviewTagDto> tagList = bookReviewTagService.getTags();
        List<ReadBookDto> bookList = bookService.getBooks();

        model.addAllAttributes(Map.of(
                "tagList", tagList,
                "bookList", bookList,
                "bookTitleList", bookList.stream().map(ReadBookDto::getTitle).toList(),
                "newInputtedTags", editBookReviewDto.getTagNames().stream().filter(tag -> tagList.stream().map(ReadBookReviewTagDto::getName).noneMatch(s -> s.equals(tag))).toList(),
                "editBookReviewDto", editBookReviewDto
        ));
        return "book-review/editBookReview";
    }

    @PatchMapping
    public String updateBookReview(@ModelAttribute("editBookReviewDto") @Validated EditBookReviewDto editBookReviewDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        editBookReviewDtoValidator.validate(editBookReviewDto, bindingResult);
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editBookReviewDto", editBookReviewDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult);

            return "redirect:/bookReviews/" + editBookReviewDto.getId() + "/edit";
        }

        Optional<ReadBookReviewDto> updatedBookReview = bookReviewService.update(editBookReviewDto);

        return updatedBookReview.map(readBookReviewDto -> "redirect:/bookReviews/" + editBookReviewDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
