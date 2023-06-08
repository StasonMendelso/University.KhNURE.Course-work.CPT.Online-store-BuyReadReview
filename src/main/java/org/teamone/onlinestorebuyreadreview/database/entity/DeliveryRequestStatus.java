package org.teamone.onlinestorebuyreadreview.database.entity;

import lombok.Getter;

/**
 * @author Starukhina Anastasiia
 */
@Getter
public enum DeliveryRequestStatus {
    CREATED ("Створено"),
    SELECTED("Обрано"),
    IN_PROCESS("В обробці"),
    DELIVERING("Доставляється"),
    DONE("Виконано"),
    CANCELED("Скасовано");

    private final String name;

    DeliveryRequestStatus(String name) {
        this.name = name;
    }

    public static DeliveryRequestStatus getInstance(String name) {
        for (DeliveryRequestStatus deliveryRequestStatus : DeliveryRequestStatus.values()) {
            if (deliveryRequestStatus.name.equals(name)) {
                return deliveryRequestStatus;
            }
        }
        throw new IllegalArgumentException("Can't get instance for passed name " + name);
    }
}
