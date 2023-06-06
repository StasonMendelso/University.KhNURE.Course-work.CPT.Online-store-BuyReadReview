package org.teamone.onlinestorebuyreadreview.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadCartDto {
    private String quantity;
    private String total;
    private List<ReadCartItemDto> cartItems;
}
