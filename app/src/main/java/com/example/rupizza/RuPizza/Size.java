package com.example.rupizza.RuPizza;


/**
 * Enumeration representing the sizes of pizzas in the pizza ordering system.
 * Each size has a display name for user-friendly representation.
 *
 * @author Seifeldeen Mohamed
 */
public enum Size {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");
    private final String displayName;

    /**
     * Constructs a Size enum with the specified display name.
     *
     * @param displayName The user-friendly display name of the size.
     */
    Size(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the size.
     *
     * @return The display name of the size.
     */
    @Override
    public String toString() {
        return displayName;
    }
}


