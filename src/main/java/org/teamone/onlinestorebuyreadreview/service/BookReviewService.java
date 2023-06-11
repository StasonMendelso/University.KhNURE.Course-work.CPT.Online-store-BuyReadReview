package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.*;
import org.teamone.onlinestorebuyreadreview.database.repository.BookReviewRepository;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.CreateBookReviewDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.EditBookReviewDto;
import org.teamone.onlinestorebuyreadreview.dto.bookreview.ReadBookReviewDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreview.BookReviewCreateMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreview.BookReviewEditMapper;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreview.BookReviewReadMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Anna Nikolaichuk
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookReviewService {
    private final BookReviewRepository bookReviewRepository;
    private final BookReviewReadMapper bookReviewReadMapper;
    private final BookReviewCreateMapper bookReviewCreateMapper;
    private final BookReviewEditMapper bookReviewEditMapper;
    private final BookReviewTagService bookReviewTagService;
    private final BookService bookService;

    public Optional<BookReview> getBookReviewById(Long id){
        return bookReviewRepository.read(id);
    }

    public List<BookReview> getAllBookReviews() {
        return bookReviewRepository.readAll();
    }

    @Transactional
    public Optional<ReadBookReviewDto> save(CreateBookReviewDto createBookReviewDto) {
        BookReview bookReview = bookReviewCreateMapper.unmap(createBookReviewDto);
        saveOrUpdateBookReviewData(bookReview, createBookReviewDto.getTagNames());
        Optional<BookReview> bookReviewOptional = bookReviewRepository.create(bookReview);
        return bookReviewOptional.map(bookReviewReadMapper::map);
    }

    @Transactional
    public Optional<ReadBookReviewDto> update(EditBookReviewDto editBookReviewDto){
        BookReview bookReview = bookReviewEditMapper.unmap(editBookReviewDto);
        saveOrUpdateBookReviewData(bookReview, editBookReviewDto.getTagNames());
        Optional<BookReview> bookReviewOptional = bookReviewRepository.update(bookReview.getId(), bookReview);
        return bookReviewOptional.map(bookReviewReadMapper::map);
    }

    private void saveOrUpdateBookReviewData(BookReview bookReview, ArrayList<String> tagNames) {

        List<BookReviewTag> tags = bookReviewTagService.getTagsByName(tagNames);
        List<BookReviewTag> tagsForAdding = new ArrayList<>();
        for (String tagName : tagNames) {
            if (tags.stream().map(BookReviewTag::getName).noneMatch(s -> s.equals(tagName))) {
                tagsForAdding.add(BookReviewTag.builder().name(tagName).build());
            }
        }
        if (!tagsForAdding.isEmpty()) {
            List<BookReviewTag> addedTags = bookReviewTagService.saveTags(tagsForAdding);
            tags.addAll(addedTags);
        }
        bookReview.setTags(tags);

        bookReview.setBook(bookService.getBookByTitle(bookReview.getBook().getTitle())
                .orElseGet(() -> bookService.save(bookReview.getBook())
                        .orElseThrow()));
    }

    public List<Long> getAllRequestForPublicationId() {
        return bookReviewRepository.readAllRequestForPublicationId();
    }

    public Optional<Long> getRequestForPublicationIdByBookReviewId(Long bookReviewId){
        return bookReviewRepository.readRequestForPublicationIdByBookReviewId(bookReviewId);
    }



}
