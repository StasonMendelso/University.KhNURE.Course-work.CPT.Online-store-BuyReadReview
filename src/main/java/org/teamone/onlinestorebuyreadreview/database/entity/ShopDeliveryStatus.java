package org.teamone.onlinestorebuyreadreview.database.entity;

/**
 * @author Stanislav Hlova
 */
public enum ShopDeliveryStatus {
    ON_WAREHOUSE("На складі"),
    DELIVERING("Доставляється"),
    WAITING_AT_PICK_UP_POINT("Чекає в пункті самовивозу");
    private final String name;

    ShopDeliveryStatus(String name) {
        this.name = name;
    }

    public static ShopDeliveryStatus getInstance(String name) {
        for (ShopDeliveryStatus shopDeliveryStatus : ShopDeliveryStatus.values()) {
            if (shopDeliveryStatus.name.equals(name)) {
                return shopDeliveryStatus;
            }
        }
        throw new IllegalArgumentException("No shop delivery status was found for passed value: " + name);
    }

    public String getName() {
        return name;
    }
}
