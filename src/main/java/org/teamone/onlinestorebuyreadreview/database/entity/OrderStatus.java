package org.teamone.onlinestorebuyreadreview.database.entity;

/**
 * @author Stanislav Hlova
 */
public enum OrderStatus {
    WAITING_FOR_REVIEWING("Очікує розгляду"),
    ACCEPTED("Прийнято"),
    IN_PROCESSING("В обробці"),
    BEING_PACKAGED("Упаковується"),
    READY_TO_DISPATCH("Готово до відправки"),
    TRANSFERRED_TO_DELIVERY_SERVICE("Передано службі доставки"),
    DELIVERING("Доставляється"),
    DELIVERED("Доставлено"),
    RECEIVER_TOOK_ORDER("Забрано отримувачем"),
    DONE("Виконано"),
    ARCHIVED("Архівовано"),
    RETURNED("Повернуто"),
    RECEIVER_REFUSED_ORDER("Відмовлено отримувачем"),
    CANCELED_BY_MANAGER_AT_BUYER_REQUEST("Скасовано менеджером за проханням покупця"),
    RETURN_DELIVERING("Зворотня доставка"),
    RETURN_ORDER_DELIVERED("Доставлено повернуту доставку"),
    PICKED_UP_FROM_DELIVERY_SERVICE("Забрано зі служби доставки"),
    ORDER_RETURNED_TO_WAREHOUSE("Повернуто на склад"),
    REJECTED("Відхилено"),
    CANCELED_BY_BUYER("Скасовано покупцем");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static OrderStatus getInstance(String name) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name.equals(name)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("No order status was found for passed value: " + name);
    }
}
