package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author Anna Nikolaichuk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookReview {
   private Long id;
   private Book book;
   private Long requestForPublicationId;
   private Long clientId;
   private String clientPseudonym;
   private String content;
   private List<BookReviewTag> tags;
   private Float rating;

   public String getBookInfo() {
      StringBuilder authorNames = new StringBuilder();
      for (Author author : book.getAuthors()) {
         authorNames.append(author.getFullName()).append(", ");
      }
      String authorsString = authorNames.toString().trim();
      return String.format("%s %s %s", book.getTitle(), " від ", authorsString);
   }
}