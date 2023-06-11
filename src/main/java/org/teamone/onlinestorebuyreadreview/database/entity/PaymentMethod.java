package org.teamone.onlinestorebuyreadreview.database.entity;

/**
 * @author Stanislav Hlova
 */
public enum PaymentMethod {
    CASH("Готівкою"),
    CARD("Карткою"),
    CASH_ON_PAYMENT("Накладним платіжем");

    private final String name;

    PaymentMethod(String name) {
        this.name = name;
    }

    public static PaymentMethod getInstance(String name) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.name.equals(name)) {
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("No payment method was found for passed value: " + name);
    }

    public String getName() {
        return name;
    }
}
