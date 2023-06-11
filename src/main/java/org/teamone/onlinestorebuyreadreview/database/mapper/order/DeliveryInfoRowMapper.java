package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.CourierDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryInfo;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.DeliveryType;
import org.teamone.onlinestorebuyreadreview.database.entity.NovaPoshtaDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.ShopDelivery;
import org.teamone.onlinestorebuyreadreview.database.entity.ShopDeliveryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class DeliveryInfoRowMapper implements RowMapper<DeliveryInfo> {
    @Override
    public DeliveryInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DeliveryMethod deliveryMethod = DeliveryMethod.getInstance(resultSet.getString("delivery_method_name"));
        DeliveryType deliveryType = null;
        if (deliveryMethod.equals(DeliveryMethod.BY_COURIER)) {
            deliveryType = CourierDelivery.builder()
                    .address(resultSet.getString("courier_delivery_address"))
                    .price(resultSet.getBigDecimal("courier_delivery_price"))
                    .build();
        }
        if(deliveryMethod.equals(DeliveryMethod.BY_NOVA_POSHTA)){
            deliveryType = NovaPoshtaDelivery.builder()
                    .townAddress(resultSet.getString("nova_poshta_delivery_town_address"))
                    .departureNumber(resultSet.getInt("nova_poshta_delivery_departure_number"))
                    .wayBill(resultSet.getBigDecimal("nova_poshta_delivery_waybill"))
                    .invoiceNumber(resultSet.getString("nova_poshta_delivery_invoice_number"))
                    .build();
        }
        if(deliveryMethod.equals(DeliveryMethod.BY_SELF_PICKUP)){
            deliveryType = ShopDelivery.builder()
                    .address(resultSet.getString("shop_delivery_address"))
                    .shopDeliveryStatus(ShopDeliveryStatus.getInstance(resultSet.getString("shop_delivery_status_name")))
                    .build();
        }
        if (deliveryType == null) {
            throw new RuntimeException("Delivery type can't be detected.");
        }
        return DeliveryInfo.builder()
                .deliveryMethod(deliveryMethod)
                .deliveryType(deliveryType)
                .build();
    }
}
