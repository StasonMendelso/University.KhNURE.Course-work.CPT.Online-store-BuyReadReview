package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.repository.BookRepository;
import org.teamone.onlinestorebuyreadreview.dto.book.ReadBookDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.book.BookReadMapper;

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

    public Optional<ReadBookDto> getBookById(Long id){
        return bookRepository.read(id).map(bookReadMapper::map);
    }

    public List<ReadBookDto> getAllBooks() {
        return bookRepository.readAll().stream().map(bookReadMapper::map).toList();
    }
}
