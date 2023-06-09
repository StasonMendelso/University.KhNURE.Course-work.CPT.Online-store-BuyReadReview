package org.teamone.onlinestorebuyreadreview.database.entity;

/**
 * @author Stanislav Hlova
 */
public enum PaymentStatus {
    PAYED("Оплачено"),
    UN_PAYED("Не оплачено"),
    REFUNDS("Повернення коштів");
    private final String name;

    PaymentStatus(String name) {
        this.name = name;
    }

    public static PaymentStatus getInstance(String name) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.name.equals(name)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("No payment status was found for passed value: " + name);
    }

    public String getName() {
        return name;
    }
}
