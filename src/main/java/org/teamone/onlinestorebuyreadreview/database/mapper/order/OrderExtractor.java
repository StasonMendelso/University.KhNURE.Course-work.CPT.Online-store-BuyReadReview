package org.teamone.onlinestorebuyreadreview.database.mapper.order;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.Client;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderItem;
import org.teamone.onlinestorebuyreadreview.database.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class OrderExtractor implements ResultSetExtractor<Order> {
    private final BuyerContactRowMapper buyerContactRowMapper;
    private final ReceiverContactRowMapper receiverContactRowMapper;
    private final DeliveryInfoRowMapper deliveryInfoRowMapper;
    private final PaymentInfoRowMapper paymentInfoRowMapper;
    private final OrderRowMapper orderRowMapper;
    private final OrderItemRowMapper orderItemRowMapper;

    @Override
    public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }
        Order order = orderRowMapper.mapRow(resultSet, resultSet.getRow());
        order.setBuyerContact(buyerContactRowMapper.mapRow(resultSet, resultSet.getRow()));
        order.setReceiverContact(receiverContactRowMapper.mapRow(resultSet, resultSet.getRow()));
        order.setDeliveryInfo(deliveryInfoRowMapper.mapRow(resultSet, resultSet.getRow()));
        order.setPaymentInfo(paymentInfoRowMapper.mapRow(resultSet, resultSet.getRow()));
        long clientId = resultSet.getLong("order_client_id");
        long managerId = resultSet.getLong("order_client_id");
        if (clientId != 0) {
            order.setClient(Client.builder()
                    .id(clientId)
                    .build());
        }
        if (managerId != 0) {
            order.setManager(User.builder()
                    .id(managerId)
                    .build());
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        do {
            if (!order.getId().equals(resultSet.getLong("order_id"))) {
                resultSet.previous();
                continue;
            }
            OrderItem orderItem = orderItemRowMapper.mapRow(resultSet, resultSet.getRow());
            Book book = Book.builder()
                    .id(resultSet.getLong("book_id"))
                    .title(resultSet.getString("book_title"))
                    .article(resultSet.getString("book_article"))
                    .description(resultSet.getString("book_description"))
                    .hidden(resultSet.getBoolean("book_hidden"))
                    .isbn(resultSet.getString("book_isbn"))
                    .quantity(resultSet.getInt("book_quantity"))
                    .paperQuantity(resultSet.getInt("book_paper_quantity"))
                    .price(resultSet.getBigDecimal("book_price"))
                    .build();
            book.setFiles(List.of(File.builder().id(resultSet.getLong("file_id")).build()));
            orderItem.setBook(book);
            orderItemList.add(orderItem);

        } while (resultSet.next());

        order.setOrderItems(orderItemList);
        return order;
    }
}
