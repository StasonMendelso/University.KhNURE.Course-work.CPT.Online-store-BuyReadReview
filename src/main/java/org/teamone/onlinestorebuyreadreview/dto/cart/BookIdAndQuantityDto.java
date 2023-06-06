package org.teamone.onlinestorebuyreadreview.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
public class BookIdAndQuantityDto {
    @NotNull(message = "Не може бути пустим.")
    @Pattern(regexp = "^[1-9][0-9]*$",message = "Книга містить некоректне значення ідентифікатора.")
    @JsonProperty("book_id")
    private String bookId;
    @NotNull(message = "Не може бути пустим.")
    @Pattern(regexp = "^[1-9][0-9]*$",message = "Кількість може бути лише додатньою.")
    private String quantity;
}
