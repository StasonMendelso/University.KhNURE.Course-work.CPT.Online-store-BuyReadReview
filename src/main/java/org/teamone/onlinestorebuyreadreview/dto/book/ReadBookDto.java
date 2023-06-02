package org.teamone.onlinestorebuyreadreview.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.teamone.onlinestorebuyreadreview.dto.file.ReadFileDto;
import org.teamone.onlinestorebuyreadreview.dto.genre.ReadGenreDto;
import org.teamone.onlinestorebuyreadreview.dto.publisher.ReadPublisherDto;
import org.teamone.onlinestorebuyreadreview.dto.author.ReadAuthorDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ReadBookDto {
    private Long id;
    private String isbn;
    private String title;
    private Integer paperQuantity;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String article;
    private ReadPublisherDto publisher;
    private List<ReadAuthorDto> authors;
    private List<ReadGenreDto> genres;
    private List<ReadFileDto> files;
}
