package org.teamone.onlinestorebuyreadreview.database.entity;

/**
 * @author Stanislav Hlova
 */
public enum Role {
    GUEST("GUEST"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    COURIER("COURIER"),
    EDITOR("EDITOR"),
    CLIENT("CLIENT");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role getInstance(String name) {
        for (Role role : values()) {
            if (role.name.equals(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException(String.format("There isn't any Role for name: %s", name));
    }
}
