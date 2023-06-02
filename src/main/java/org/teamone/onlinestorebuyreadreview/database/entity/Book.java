package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private Integer paperQuantity;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String article;
    private Boolean hidden;
    private Publisher publisher;
    private List<Author> authors;
    private List<Genre> genres;
    private List<File> files;
}
