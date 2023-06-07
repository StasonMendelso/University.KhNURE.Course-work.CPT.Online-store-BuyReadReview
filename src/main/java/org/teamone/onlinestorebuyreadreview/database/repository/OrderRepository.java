package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.teamone.onlinestorebuyreadreview.database.entity.Order;
import org.teamone.onlinestorebuyreadreview.database.entity.OrderStatus;
import org.teamone.onlinestorebuyreadreview.database.mapper.order.OrderExtractor;
import org.teamone.onlinestorebuyreadreview.database.mapper.order.OrdersExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository implements CrudRepository<Long, Order> {
    private final JdbcTemplate jdbcTemplate;
    private final OrderExtractor orderExtractor;
    private final OrdersExtractor ordersExtractor;

    @Override
    public Optional<Order> create(Order entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> read(Long id) {
        return Optional.ofNullable(jdbcTemplate.query(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT book.id AS 'book_id', book.isbn AS 'book_isbn', book.paper_quantity AS 'book_paper_quantity', book.title AS 'book_title', book.description AS 'book_description', book.price AS 'book_price', book.hidden AS 'book_hidden', book.quantity AS 'book_quantity', book.article AS 'book_article',\n" +
                    "first_file.file_id AS 'file_id',\n" +
                    "`order`.id AS 'order_id', `order`.created_datetime AS 'order_created_datetime', `order`.client_id AS 'order_client_id', `order`.manager_id AS 'order_manager_id', `order`.used_bonuses AS 'order_used_bonuses', `order`.description AS 'order_description', \n" +
                    "order_status.`status` AS 'order_status_name',\n" +
                    "order_item.price AS 'order_item_price', order_item.title AS 'order_item_title', order_item.quantity AS 'order_item_quantity',\n" +
                    "buyer_contact.first_name AS 'buyer_contact_first_name', buyer_contact.middle_name AS 'buyer_contact_middle_name', buyer_contact.last_name AS 'buyer_contact_last_name',  buyer_contact.email AS 'buyer_contact_email',  buyer_contact.telephone_number AS 'buyer_contact_telephone_number', \n" +
                    "receiver_contact.first_name AS 'receiver_contact_first_name', receiver_contact.middle_name AS 'receiver_contact_middle_name', receiver_contact.last_name AS 'receiver_contact_last_name',  receiver_contact.email AS 'receiver_contact_email',  receiver_contact.telephone_number AS 'receiver_contact_telephone_number', \n" +
                    "payment_info.total_amount AS 'payment_info_total_amount', payment_info.description AS 'payment_info_description', payment_status.status AS 'payment_status_name', payment_method.name AS 'payment_method_name',\n" +
                    "cash_payment.change AS 'cash_payment_change', cash_payment.amount_received AS 'cash_payment_amount_received',\n" +
                    "card_payment.sender_account AS 'card_payment_sender_account', card_payment.receiver_account AS 'card_payment_receiver_account',\n" +
                    "cash_on_delivery_payment.change AS 'cash_on_delivery_payment_change', cash_on_delivery_payment.amount_received AS 'cash_on_delivery_payment_amount_received',\n" +
                    "delivery_method.name AS 'delivery_method_name',\n" +
                    "courier_delivery.address AS 'courier_delivery_address', courier_delivery.price AS 'courier_delivery_price',\n" +
                    "shop_delivery.address AS 'shop_delivery_address', shop_delivery_status.delivery_status 'shop_delivery_status_name',\n" +
                    "nova_poshta_delivery.town_address AS 'nova_poshta_delivery_town_address', nova_poshta_delivery.departure_number AS 'nova_poshta_delivery_departure_number', nova_poshta_delivery.waybill AS 'nova_poshta_delivery_waybill', nova_poshta_delivery.invoice_number AS 'nova_poshta_delivery_invoice_number'\n" +
                    "\n" +
                    "FROM `order`\n" +
                    "LEFT JOIN `order_status` ON `order`.order_status_id = `order_status`.id\n" +
                    "LEFT JOIN `order_item` ON `order`.id = `order_item`.order_id\n" +
                    "LEFT JOIN `book` ON `book`.id = `order_item`.book_id\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT *\n" +
                    "     FROM book_file\n" +
                    "     WHERE file_id IN (\n" +
                    "        SELECT MIN(file_id)\n" +
                    "        FROM book_file\n" +
                    "        GROUP BY book_id)) AS first_file ON `first_file`.book_id = `book`.id\n" +
                    "LEFT JOIN `buyer_contact` ON `buyer_contact`.id = `order`.id\n" +
                    "LEFT JOIN `receiver_contact` ON `receiver_contact`.id = `order`.id\n" +
                    "LEFT JOIN `payment_info` ON `payment_info`.id = `order`.id\n" +
                    "LEFT JOIN `payment_status` ON `payment_status`.id = `payment_info`.payment_status_id\n" +
                    "LEFT JOIN `payment_method` ON `payment_method`.id = `payment_info`.payment_method_id\n" +
                    "LEFT JOIN `cash_payment` ON `cash_payment`.id = `payment_info`.id\n" +
                    "LEFT JOIN `card_payment` ON `card_payment`.id = `payment_info`.id\n" +
                    "LEFT JOIN `cash_on_delivery_payment` ON `cash_on_delivery_payment`.id = `payment_info`.id\n" +
                    "LEFT JOIN `delivery_info` ON `order`.id = `delivery_info`.id\n" +
                    "LEFT JOIN `delivery_method` ON `delivery_info`.delivery_method_id = `delivery_method`.id\n" +
                    "LEFT JOIN `courier_delivery` ON `courier_delivery`.id = `delivery_info`.id\n" +
                    "LEFT JOIN `shop_delivery` ON `shop_delivery`.id = `delivery_info`.id\n" +
                    "LEFT JOIN `shop_delivery_status` ON `shop_delivery_status`.id = `shop_delivery`.shop_delivery_status\n" +
                    "LEFT JOIN `nova_poshta_delivery` ON `courier_delivery`.id = `delivery_info`.id " +
                    "WHERE `order`.id = ? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        }, orderExtractor));
    }

    @Override
    public Optional<Order> update(Long id, Order entity) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Order> readAll() {
        return jdbcTemplate.query(connection -> connection.prepareStatement(
                "SELECT book.id AS 'book_id', book.isbn AS 'book_isbn', book.paper_quantity AS 'book_paper_quantity', book.title AS 'book_title', book.description AS 'book_description', book.price AS 'book_price', book.hidden AS 'book_hidden', book.quantity AS 'book_quantity', book.article AS 'book_article',\n" +
                "first_file.file_id AS 'file_id',\n" +
                "`order`.id AS 'order_id', `order`.created_datetime AS 'order_created_datetime', `order`.client_id AS 'order_client_id', `order`.manager_id AS 'order_manager_id', `order`.used_bonuses AS 'order_used_bonuses', `order`.description AS 'order_description', \n" +
                "order_status.`status` AS 'order_status_name',\n" +
                "order_item.price AS 'order_item_price', order_item.title AS 'order_item_title', order_item.quantity AS 'order_item_quantity',\n" +
                "buyer_contact.first_name AS 'buyer_contact_first_name', buyer_contact.middle_name AS 'buyer_contact_middle_name', buyer_contact.last_name AS 'buyer_contact_last_name',  buyer_contact.email AS 'buyer_contact_email',  buyer_contact.telephone_number AS 'buyer_contact_telephone_number', \n" +
                "receiver_contact.first_name AS 'receiver_contact_first_name', receiver_contact.middle_name AS 'receiver_contact_middle_name', receiver_contact.last_name AS 'receiver_contact_last_name',  receiver_contact.email AS 'receiver_contact_email',  receiver_contact.telephone_number AS 'receiver_contact_telephone_number', \n" +
                "payment_info.total_amount AS 'payment_info_total_amount', payment_info.description AS 'payment_info_description', payment_status.status AS 'payment_status_name', payment_method.name AS 'payment_method_name',\n" +
                "cash_payment.change AS 'cash_payment_change', cash_payment.amount_received AS 'cash_payment_amount_received',\n" +
                "card_payment.sender_account AS 'card_payment_sender_account', card_payment.receiver_account AS 'card_payment_receiver_account',\n" +
                "cash_on_delivery_payment.change AS 'cash_on_delivery_payment_change', cash_on_delivery_payment.amount_received AS 'cash_on_delivery_payment_amount_received',\n" +
                "delivery_method.name AS 'delivery_method_name',\n" +
                "courier_delivery.address AS 'courier_delivery_address', courier_delivery.price AS 'courier_delivery_price',\n" +
                "shop_delivery.address AS 'shop_delivery_address', shop_delivery_status.delivery_status 'shop_delivery_status_name',\n" +
                "nova_poshta_delivery.town_address AS 'nova_poshta_delivery_town_address', nova_poshta_delivery.departure_number AS 'nova_poshta_delivery_departure_number', nova_poshta_delivery.waybill AS 'nova_poshta_delivery_waybill', nova_poshta_delivery.invoice_number AS 'nova_poshta_delivery_invoice_number'\n" +
                "\n" +
                "FROM `order`\n" +
                "LEFT JOIN `order_status` ON `order`.order_status_id = `order_status`.id\n" +
                "LEFT JOIN `order_item` ON `order`.id = `order_item`.order_id\n" +
                "LEFT JOIN `book` ON `book`.id = `order_item`.book_id\n" +
                "LEFT JOIN (\n" +
                "\tSELECT *\n" +
                "     FROM book_file\n" +
                "     WHERE file_id IN (\n" +
                "        SELECT MIN(file_id)\n" +
                "        FROM book_file\n" +
                "        GROUP BY book_id)) AS first_file ON `first_file`.book_id = `book`.id\n" +
                "LEFT JOIN `buyer_contact` ON `buyer_contact`.id = `order`.id\n" +
                "LEFT JOIN `receiver_contact` ON `receiver_contact`.id = `order`.id\n" +
                "LEFT JOIN `payment_info` ON `payment_info`.id = `order`.id\n" +
                "LEFT JOIN `payment_status` ON `payment_status`.id = `payment_info`.payment_status_id\n" +
                "LEFT JOIN `payment_method` ON `payment_method`.id = `payment_info`.payment_method_id\n" +
                "LEFT JOIN `cash_payment` ON `cash_payment`.id = `payment_info`.id\n" +
                "LEFT JOIN `card_payment` ON `card_payment`.id = `payment_info`.id\n" +
                "LEFT JOIN `cash_on_delivery_payment` ON `cash_on_delivery_payment`.id = `payment_info`.id\n" +
                "LEFT JOIN `delivery_info` ON `order`.id = `delivery_info`.id\n" +
                "LEFT JOIN `delivery_method` ON `delivery_info`.delivery_method_id = `delivery_method`.id\n" +
                "LEFT JOIN `courier_delivery` ON `courier_delivery`.id = `delivery_info`.id\n" +
                "LEFT JOIN `shop_delivery` ON `shop_delivery`.id = `delivery_info`.id\n" +
                "LEFT JOIN `shop_delivery_status` ON `shop_delivery_status`.id = `shop_delivery`.shop_delivery_status\n" +
                "LEFT JOIN `nova_poshta_delivery` ON `courier_delivery`.id = `delivery_info`.id ",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY),ordersExtractor);
    }

    public void updateOrderStatus(Long orderId, Long orderStatusId) {
        jdbcTemplate.update("UPDATE `order` SET order_status_id = ? WHERE `order`.id = ?", orderStatusId, orderId);
    }

    public Optional<Long> getOrderStatusId(OrderStatus orderStatus) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT id FROM order_status WHERE order_status.status= ?", ps -> ps.setString(1, orderStatus.getName()), (resultSet) -> {
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getLong("id");
        }));

    }

    public void updateOrderStatusDescription(Long orderId, String description) {
        jdbcTemplate.update("UPDATE `order` SET description=? WHERE `order`.id=?", description, orderId);
    }
}
