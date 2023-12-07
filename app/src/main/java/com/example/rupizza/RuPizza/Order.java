package com.example.rupizza.RuPizza;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents the order for pizzas in the pizza ordering system.
 * Manages a list of pizzas in the order and provides methods to add pizzas to the order.
 * The order is implemented as a singleton to maintain a single instance throughout the application.
 * Uses JavaFX ObservableList to store and manage the list of pizzas in the order.
 *
 * @author Seifeldeen Mohamed
 */

public class Order {
    private static Order pizzaOrder = new Order();  // Singleton instance
    private List<Pizza> pizzas;
    private static int orderIDCounter = 1;
    private static int nextStoreID = 1;
    private int storeID;
    private Map<Pizza, Integer> pizzaOrderIDs;


    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the order with an empty list of pizzas.
     */
    public Order() {
        this.pizzas = new ArrayList<>();
        this.pizzaOrderIDs = new HashMap<>();
        this.storeID = nextStoreID++;
    }


    /**
     * Retrieves the singleton instance of the Order.
     *
     * @return The singleton instance of the Order.
     */
    public static Order getPizzaOrder() {
        return pizzaOrder;
    }

    /**
     * Resets the currentOrder instance to a fresh order.
     */
    public void resetOrder() {
        pizzaOrder = new Order();
    }

    /**
     * Adds a pizza to the order.
     * If the added pizza is a speciality pizza, sets its order ID.
     *
     * @param pizza The pizza to be added to the order.
     * @return True if the pizza is successfully added to the order, false otherwise.
     */
    public boolean addPizza(Pizza pizza) {
        if (pizza instanceof SpecialityPizza) {
            ((SpecialityPizza) pizza).setOrderID(orderIDCounter++);
        }
        if (pizza instanceof BuildYourOwnPizza) {
            ((BuildYourOwnPizza) pizza).setOrderID(orderIDCounter++);
        }
        return pizzas.add(pizza);
    }

    /**
     * Retrieves the list of pizzas in the order.
     *
     * @return The List containing the pizzas in the order.
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Integer> getOrderIDs() {
        Set<Integer> uniqueOrderIDs = new HashSet<>();
        for (Pizza pizza : pizzas) {
            if (pizza instanceof SpecialityPizza) {
                uniqueOrderIDs.add(((SpecialityPizza) pizza).getPizzaID());
            }
        }
        return new ArrayList<>(uniqueOrderIDs);
    }


}


