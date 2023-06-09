package org.teamone.onlinestorebuyreadreview.dto.order;

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
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderCheckoutDto {
    private List<ReadOrderItemDto> orderItemList;
    private BigDecimal total;
    private BigDecimal courierDeliveryPrice;

    public boolean isEmpty(){
        return orderItemList==null || orderItemList.isEmpty();
    }
}
