package org.teamone.onlinestorebuyreadreview.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import org.teamone.onlinestorebuyreadreview.util.validation.annotation.NotEmptyList;

import java.util.ArrayList;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateBookDto {
    @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{4}-[0-9]{1}$", message = "ISBN повинен відповідати формату \"123-123-12-1234-1\".")
    private String isbn;
    @NotBlank(message = "Книга повинна мати назву.")
    private String title;
    @Pattern(regexp = "^[1-9][0-9]*$",message = "Книга повинна містити певну додатню кількість сторінок.")
    private String paperQuantity;
    @NotBlank(message = "Книга повинна містити опис.")
    @Length(max = 3400, message = "Опис книги може містити лише 3.400 символів")
    private String description;
    @Pattern(regexp = "^[1-9][0-9]*(\\.[0-9]+)?$", message = "Некоректна ціна.")
    private String price;
    @Pattern(regexp = "^[0-9]+$",message = "Кількість книг повинна бути невід'ємною.")
    private String quantity;
    @NotBlank(message = "Книга повинна містити артикул.")
    private String article;
    @NotNull(message = "Виберіть приховувати книгу в каталозі чи ні.")
    private Boolean hidden;
    @NotBlank(message = "Книга повинна мати видавництво.")
    private String publisherName;
    @NotEmptyList(message = "Виберіть автора(-ів) книги.")
    private ArrayList<String> authorNames = new ArrayList<>();
    @NotEmptyList(message = "Виберіть жанр(-и) книги.")
    private ArrayList<String> genreNames = new ArrayList<>();

    private ArrayList<MultipartFile> multipartFiles = new ArrayList<>();
}
