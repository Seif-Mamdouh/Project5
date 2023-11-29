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

    /**
     * Default constructor for the Pizza class.
     */
    public Pizza() {

    }

    public List<String> getToppings() {
        return toppings;
    }

    /**
     * Enum For Pizza Types
     */
    public enum PizzaType {
        DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, HALAL, CHEESE, MIX_GRILL, SALMON, SHRIMP, BUFFALO_CHICKEN, BUILD_YOUR_OWN
    }


    public Pizza(List<String> toppings, Size size, boolean extraSauce, boolean extraCheese) {
        this.toppings = new ArrayList<>(toppings);
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    // Add this method to get default toppings for each pizza type
    public static List<String> getDefaultToppings(PizzaType pizzaType) {
        Map<PizzaType, List<String>> defaultToppingsMap = new HashMap<>();
        // Define default toppings for each pizza type
        defaultToppingsMap.put(PizzaType.DELUXE, List.of("Mushrooms", "Pepperoni", "Green Peppers"));
        defaultToppingsMap.put(PizzaType.SUPREME, List.of("Sausage", "Black Olives", "Onions"));
        defaultToppingsMap.put(PizzaType.MEATZZA, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.SEAFOOD, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.PEPPERONI, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.HALAL, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.CHEESE, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.MIX_GRILL, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.SALMON, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.SHRIMP, List.of("Bacon", "Sausage", "Pepperoni"));
        defaultToppingsMap.put(PizzaType.BUFFALO_CHICKEN, List.of("Bacon", "Sausage", "Pepperoni"));

        return defaultToppingsMap.getOrDefault(pizzaType, new ArrayList<>());
    }

    /**
     * Factory method to create a pizza based on the specified pizza type, size, and additional options.
     *
     * @param pizzaType   The type of the pizza to create.
     * @param size        The size of the pizza to create.
     * @param extraSauce  Indicates whether extra sauce should be added to the pizza.
     * @param extraCheese Indicates whether extra cheese should be added to the pizza.
     * @return A new pizza instance based on the provided parameters.
     */
    public static Pizza createPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese, List<String> toppings) {
        return switch (pizzaType) {
            case DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, HALAL, BUFFALO_CHICKEN, CHEESE, MIX_GRILL, SALMON, SHRIMP -> new SpecialityPizza(pizzaType, size, extraSauce, extraCheese, toppings);
            case BUILD_YOUR_OWN -> new BuildYourOwnPizza(pizzaType, size, extraSauce, extraCheese, toppings);
        };
    }


    /**
     * Abstract method to be implemented by subclasses for calculating the price of the pizza.
     *
     * @return The calculated price of the pizza.
     */
    public abstract double calculatePrice();
}
