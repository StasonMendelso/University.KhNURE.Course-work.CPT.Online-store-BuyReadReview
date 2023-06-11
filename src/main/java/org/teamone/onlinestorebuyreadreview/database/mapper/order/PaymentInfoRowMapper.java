package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.CardPayment;
import org.teamone.onlinestorebuyreadreview.database.entity.CashOnDeliveryPayment;
import org.teamone.onlinestorebuyreadreview.database.entity.CashPayment;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentInfo;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentMethod;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentStatus;
import org.teamone.onlinestorebuyreadreview.database.entity.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class PaymentInfoRowMapper implements RowMapper<PaymentInfo> {
    @Override
    public PaymentInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PaymentMethod paymentMethod = PaymentMethod.getInstance(resultSet.getString("payment_method_name"));
        PaymentType paymentType = null;

        if (paymentMethod.equals(PaymentMethod.CASH)) {
            paymentType = CashPayment.builder()
                    .amountReceived(resultSet.getBigDecimal("cash_payment_amount_received"))
                    .change(resultSet.getBigDecimal("cash_payment_change"))
                    .build();
        }
        if(paymentMethod.equals(PaymentMethod.CARD)){
            paymentType = CardPayment.builder()
                    .receiverAccountNumber(resultSet.getString("card_payment_receiver_account"))
                    .senderAccountNumber(resultSet.getString("card_payment_sender_account"))
                    .build();
        }
        if (paymentMethod.equals(PaymentMethod.CASH_ON_PAYMENT)){
            paymentType = CashOnDeliveryPayment.builder()
                    .amountReceived(resultSet.getBigDecimal("cash_on_delivery_payment_amount_received"))
                    .change(resultSet.getBigDecimal("cash_on_delivery_payment_change"))
                    .build();
        }
        if(paymentType == null){
            throw new RuntimeException("Payment type can't be detected.");
        }

        return PaymentInfo.builder()
                .paymentMethod(paymentMethod)
                .description(resultSet.getString("payment_info_description"))
                .paymentStatus(PaymentStatus.getInstance(resultSet.getString("payment_status_name")))
                .paymentType(paymentType)
                .totalAmount(resultSet.getBigDecimal("payment_info_total_amount"))
                .build();
    }
}
