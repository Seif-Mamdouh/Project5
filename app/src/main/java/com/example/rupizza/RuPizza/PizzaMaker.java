package com.example.rupizza.RuPizza;

/**
 * Utility class for creating pizzas in the pizza ordering system.
 *
 * It provides a method to create a pizza based on the specified type, size, and additional options.
 * @author Seifeldeen Mohamed
 */

public class PizzaMaker {
    /**
     * Creates a pizza based on the specified pizza type, size, and additional options.
     *
     * @param pizzaType   The type of the pizza to create.
     * @param size        The size of the pizza to create.
     * @param extraSauce  Indicates whether extra sauce should be added to the pizza.
     * @param extraCheese Indicates whether extra cheese should be added to the pizza.
     * @return A new pizza instance based on the provided parameters.
     */
    public static Pizza createPizza(Pizza.PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese) {
        return Pizza.createPizza(pizzaType, size, extraSauce, extraCheese);
    }

}
