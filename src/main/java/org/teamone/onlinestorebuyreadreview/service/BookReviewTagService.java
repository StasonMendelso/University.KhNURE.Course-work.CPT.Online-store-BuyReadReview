package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teamone.onlinestorebuyreadreview.database.entity.BookReviewTag;
import org.teamone.onlinestorebuyreadreview.database.repository.BookReviewTagRepository;
import org.teamone.onlinestorebuyreadreview.dto.bookreviewtag.ReadBookReviewTagDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.bookreviewtag.BookReviewTagReadMapper;

import java.util.List;

/**
 * @author Anna Nikolaichuk
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookReviewTagService {
    private final BookReviewTagRepository bookReviewTagRepository;
    private final BookReviewTagReadMapper bookReviewTagReadMapper;

    public List<ReadBookReviewTagDto> getTags(){
        return bookReviewTagRepository.readAll()
                .stream()
                .map(bookReviewTagReadMapper::map)
                .toList();
    }
    public List<BookReviewTag> getTagsByName(List<String> tagNames){
        return bookReviewTagRepository.readAllByName(tagNames);
    }
    public List<BookReviewTag> saveTags(List<BookReviewTag> tagsForAdding){
        return bookReviewTagRepository.create(tagsForAdding);
    }
}
