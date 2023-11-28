package com.example.rupizza.RuPizza;

import java.util.ArrayList;

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

    /**
     * Enum For Pizza Types
     */
    public enum PizzaType {
        DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI, BUILD_YOUR_OWN
    }

    /**
     * Constructor for creating a pizza with specified toppings, size, sauce, and additional options.
     *
     * @param toppings    The toppings to add to the pizza.
     * @param size        The size of the pizza.
     * @param sauce       The sauce type for the pizza.
     * @param extraSauce  Indicates whether extra sauce should be added to the pizza.
     * @param extraCheese Indicates whether extra cheese should be added to the pizza.
     */
    public Pizza(ArrayList<String> toppings, Size size, Sauce sauce, boolean extraSauce, boolean extraCheese) {
        this.toppings = toppings;
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
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
    public static Pizza createPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese) {
        return switch (pizzaType) {
            case DELUXE, SUPREME, MEATZZA, SEAFOOD, PEPPERONI -> new SpecialityPizza(pizzaType, size, extraSauce, extraCheese);
            case BUILD_YOUR_OWN -> new BuildYourOwnPizza(pizzaType, size, extraSauce, extraCheese, new ArrayList<>());
        };
    }

    /**
     * Abstract method to be implemented by subclasses for calculating the price of the pizza.
     *
     * @return The calculated price of the pizza.
     */
    public abstract double calculatePrice();
}
