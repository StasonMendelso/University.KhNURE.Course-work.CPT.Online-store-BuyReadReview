package org.teamone.onlinestorebuyreadreview.database.entity;

/**
 * @author Stanislav Hlova
 */
public enum DeliveryMethod {
    BY_COURIER("Кур'єром"),
    BY_SELF_PICKUP("Самовивіз"),
    BY_NOVA_POSHTA("Новою поштою");

    private final String name;

    DeliveryMethod(String name) {
        this.name = name;
    }

    public static DeliveryMethod getInstance(String name) {
        for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
            if (deliveryMethod.name.equals(name)) {
                return deliveryMethod;
            }
        }
        throw new IllegalArgumentException("No delivery method was found for passed value: " + name);
    }

    public String getName() {
        return name;
    }
}
