package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.repository.BookRepository;
import org.teamone.onlinestorebuyreadreview.service.BookService;

/**
 * @author Stanislav Hlova
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


    @GetMapping
    public String viewBooks(Model model){
        model.addAttribute("bookList",bookService.getAllBooks());
        return "book/books";
    }
    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("book",bookService.getBookById(id).get());
        return "book/book";
    }
}
