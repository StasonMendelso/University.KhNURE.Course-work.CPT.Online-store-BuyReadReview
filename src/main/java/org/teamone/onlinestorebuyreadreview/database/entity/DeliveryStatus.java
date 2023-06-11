package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.Getter;

/**
 * @author Starukhina Anastasiia
 */
@Getter
public enum DeliveryStatus {
    CONFIRMED("Підтверджено"),
    IN_PROCESS("В обробці"),
    BEING_PACKED("Упаковується"),
    READY_FOR_DELIVERING("Готово до доставки"),
    PICKED_FROM_WAREHOUSE("Забрано зі складу"),
    DELIVERING("Доставляється"),
    DELIVERED("Доставлено"),
    TAKEN("Узято"),
    REFUSED("Відмовлено отримувачем"),
    IN_RETURN("Повертається"),
    DONE("Виконано"),
    RETURNED_TO_WAREHOUSE("Повернено до складу");
    private final String name;

    DeliveryStatus(String name) {
        this.name = name;
    }

    public static DeliveryStatus getInstance(String name) {
        for (DeliveryStatus deliveryStatus : DeliveryStatus.values()) {
            if (deliveryStatus.name.equals(name)) {
                return deliveryStatus;
            }
        }
        throw new IllegalArgumentException("Can't get instance for passed name " + name);
    }
}

