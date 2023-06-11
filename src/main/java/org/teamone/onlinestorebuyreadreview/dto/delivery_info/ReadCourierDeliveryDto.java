package org.teamone.onlinestorebuyreadreview.dto.delivery_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@Builder
@AllArgsConstructor
public class ReadCourierDeliveryDto extends ReadDeliveryTypeDto{
    private String price;
    private String address;
}
