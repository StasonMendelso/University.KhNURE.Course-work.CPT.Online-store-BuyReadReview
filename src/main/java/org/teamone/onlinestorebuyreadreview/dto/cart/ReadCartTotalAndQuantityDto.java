package org.teamone.onlinestorebuyreadreview.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav Hlova
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadCartTotalAndQuantityDto {
    private String quantity;
    private String total;
}
