package org.teamone.onlinestorebuyreadreview.dto.bookreview;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.teamone.onlinestorebuyreadreview.util.validation.annotation.NotEmptyList;

import java.util.ArrayList;
/**
 * @author Anna Nikolaichuk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateBookReviewDto {
    @NotBlank(message = "Рецензія повинна бути написана на якусь книгу.")
    private String bookTitle;
    @NotBlank(message = "Рецензія повинна бути оброблена заявкою.")
    @Pattern(regexp = "^[1-9][0-9]*$",message = "Рецензія містить некоректне значення ідентифікатора заявки.")
    private String requestForPublicationId;
    @NotBlank(message = "Рецензія повинна бути кимось написана.")
    @Pattern(regexp = "^[1-9][0-9]*$",message = "Рецензія містить некоректне значення ідентифікатора клієнта.")
    private String clientId;
    private String clientPseudonym;
    @NotBlank(message = "Рецензія не може бути пустою.")
    @Length(max = 7200, message = "Рецензія може містити лише 7.200 символів")
    private String content;
    @NotEmptyList(message = "Виберіть хоча б один характеризуючий тег до рецензії.")
    private ArrayList<String> tagNames = new ArrayList<>();
}
