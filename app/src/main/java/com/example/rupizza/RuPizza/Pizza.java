package com.example.rupizza.RuPizza;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class representing a pizza in the pizza ordering system.
 * It provides common properties and methods for different types of pizzas.
 *
 * @author Seifeldeen Mohamed
 */
public abstract class Pizza {
    protected ArrayList<String> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    protected int quantity;

    /**
     * Get the type of the pizza.
     *
     * @return The pizza type.
     */
    public abstract PizzaType getPizzaType();
    /**
     * Abstract method to set the order ID for a pizza.
     *
     * @param orderID The order ID to set for the pizza.
     */
    public abstract void setOrderID(int orderID);

    public abstract void getOrderID(int orderID);

    /**
     * Default constructor for the Pizza class.
     */
    public Pizza() {

    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setSize(Size item) {
    }

    /**
     * Enum For Pizza Types
     */
    public enum PizzaType {
        DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, HALAL, CHEESE, MIX_GRILL, SALMON, SHRIMP, BUFFALO_CHICKEN, BUILD_YOUR_OWN
    }

    /**
     * Constructor for creating a pizza with specified toppings, size, sauce, additional options, and quantity.
     *
     * @param toppings    The toppings to add to the pizza.
     * @param size        The size of the pizza.
     * @param extraSauce  Indicates whether extra sauce should be added to the pizza.
     * @param extraCheese Indicates whether extra cheese should be added to the pizza.
     * @param quantity    The quantity of pizzas.
     */
    public Pizza(List<String> toppings, Size size, boolean extraSauce, boolean extraCheese, int quantity) {
        this.toppings = new ArrayList<>(toppings);
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.quantity = quantity;
    }

    /**
     * Get the quantity of the pizza.
     *
     * @return The quantity of the pizza.
     */
    public int getQuantity() {
        return quantity;
    }


    public boolean isExtraCheese() {
        return extraCheese;
    }

    public boolean isExtraSauce() {
        return extraSauce;
    }


    // Abstract method to get the size
    public abstract Size getSize();

    // Add this method to get default toppings for each pizza type
    public static List<String> getDefaultToppings(Pizza.PizzaType pizzaType) {
        Map<PizzaType, List<String>> defaultToppingsMap = new HashMap<>();


        // Define default toppings for each pizza type
        defaultToppingsMap.put(PizzaType.DELUXE, List.of("Sausage","Pepperoni", "Mushrooms","Green Peppers", "Onions", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.SUPREME, List.of("Sausage", "Pepperoni", "Ham", "Green Pepper", "Onion", "Black Olives", "Onions", "Mushrooms", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.MEATZZA, List.of("Sausage", "Pepperoni", "Beef", "Ham", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.SEAFOOD, List.of("Shrimp", "Seafood", "CrabMeat", "Alferdo Sauce"));
        defaultToppingsMap.put(PizzaType.PEPPERONI, List.of("Pepperoni", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.HALAL, List.of("ALL HALAL Meats", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.CHEESE, List.of("Mixed Cheeses", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.MIX_GRILL, List.of("Mix Grill", "Tomato Sauce"));
        defaultToppingsMap.put(PizzaType.SALMON, List.of("Salmon", "Alferdo Sauce"));
        defaultToppingsMap.put(PizzaType.SHRIMP, List.of("Shrimp", "Alferdo Sauce"));
        defaultToppingsMap.put(PizzaType.BUFFALO_CHICKEN, List.of("Buffalo Sauce", "Buffalo Chicken", "Tomato Sauce"));

        return defaultToppingsMap.getOrDefault(pizzaType, new ArrayList<>());
    }

    /**
     * Factory method to create a pizza based on the specified pizza type, size, and additional options.
     *
     * @param pizzaType   The type of the pizza to create.
     * @param size        The size of the pizza to create.
     * @param extraSauce  Indicates whether extra sauce should be added to the pizza.
     * @param extraCheese Indicates whether extra cheese should be added to the pizza.
     * @param toppings    The toppings to add to the pizza.
     * @param quantity    The quantity of pizzas to create.
     * @return A new pizza instance based on the provided parameters.
     */
    public static Pizza createPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese, List<String> toppings, int quantity) {
        return switch (pizzaType) {
            case DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, HALAL, BUFFALO_CHICKEN, CHEESE, MIX_GRILL, SALMON, SHRIMP -> new SpecialityPizza(pizzaType, size, extraSauce, extraCheese, toppings, quantity);
            case BUILD_YOUR_OWN -> new BuildYourOwnPizza(pizzaType, size, extraSauce, extraCheese, toppings, quantity);
        };
    }

    /**
     * Abstract method to be implemented by subclasses for calculating the price of the pizza.
     *
     * @return The calculated price of the pizza.
     */
    public abstract double calculatePrice();
}
