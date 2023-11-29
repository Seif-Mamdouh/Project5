package com.example.rupizza.RuPizza;


import java.util.List;

/**
 * Represents a customizable pizza where customers can build their own pizza by choosing size, toppings, and additional options.
 * Extends the Pizza class.
 * @author Satya Pandya
 */

public class BuildYourOwnPizza extends Pizza {

    private PizzaType pizzaType;
    /**
     * The base price for a small pizza.
     */
    private static final double BASE_PRICE_SMALL = 8.99;
    /**
     * The price for each additional topping.
     */
    private static final double TOPPING_PRICE = 1.49;
    /**
     * The number of toppings included in the base price.
     */
    private static final int INCLUDED_TOPPINGS = 4;
    /**
     * The tax rate applied to the pizza price.
     */
    public static final double TAX_RATE = 0.06625;
    private int orderID;
    private List<String> toppings;

    private static final double DEFAULT_NUMBER = 0.0;
    private static final double SIZE_PRICE_SMALL = 0.0;
    private static final double SIZE_PRICE_MEDIUM = 2.0;
    private static final double SIZE_PRICE_LARGE = 4.0;
    private static final double EXTRACHEESE_PRICE = 1.0;
    private static final double EXTRASUACE_PRICE = 1.0;



    /**
     * Constructs a new BuildYourOwnPizza object with the specified parameters.
     *
     * @param size        The size of the pizza (small, medium, large).
     * @param extraSauce  Indicates whether extra sauce is added to the pizza.
     * @param extraCheese Indicates whether extra cheese is added to the pizza.
     * @param toppings    The list of toppings chosen for the pizza.
     * @param quantity
     */
    public BuildYourOwnPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese, List<String> toppings, int quantity) {
        this.pizzaType = pizzaType;
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        this.toppings = toppings;
    }

    @Override
    public PizzaType getPizzaType() {
        return pizzaType;
    }

    /**
     * Sets the order ID for the specialty pizza.
     *
     * @param orderID The unique order ID to be assigned to the pizza.
     */
    @Override
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Retrieves the order ID of the specialty pizza.
     *
     * @return The order ID of the pizza.
     */
    public Integer getPizzaID() {
        return orderID;
    }

    /**
     * Calculates the total cost of the specialty pizza, including tax.
     *
     * @return The total cost of the pizza.
     */
    public double total(){
        return calculateTax() + calculatePrice();
    }
    /**
     * Calculates the tax amount for the specialty pizza.
     *
     * @return The tax amount for the pizza.
     */
    public double calculateTax() {
        return calculatePrice() * TAX_RATE;
    }

    /**
     * Calculates the price without tax for the order.
     * The toppings include an extra item (sauce) which is not actually part of the toppings.
     * We add it to our "toppings" array for our specific implementation.
     * @return
     */
    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double sizePrice = calculateSizePrice();
        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();
        double toppingsPrice = calculateToppingsPrice();

        return basePrice + sizePrice + extraSauceAndCheesePrice + toppingsPrice;
    }

    private double calculateBasePrice() {
        switch (size) {
            case SMALL, MEDIUM, LARGE -> {
                return BASE_PRICE_SMALL;
            }
        }
        return DEFAULT_NUMBER;
    }

    private double calculateSizePrice() {
        switch (size) {
            case SMALL -> {
                return SIZE_PRICE_SMALL;
            }
            case MEDIUM -> {
                return SIZE_PRICE_MEDIUM;
            }
            case LARGE -> {
                return SIZE_PRICE_LARGE;
            }
        }
        return DEFAULT_NUMBER;
    }

    private double calculateExtraSauceAndCheesePrice() {
        double extraSaucePrice = extraSauce ? EXTRASUACE_PRICE : DEFAULT_NUMBER;
        double extraCheesePrice = extraCheese ? EXTRACHEESE_PRICE : DEFAULT_NUMBER;

        return extraSaucePrice + extraCheesePrice;
    }

    private double calculateToppingsPrice() {
        int additionalToppings = Math.max(0, toppings.size() - INCLUDED_TOPPINGS);
        return additionalToppings * TOPPING_PRICE;
    }


    public List<String> getToppings() {
        return toppings;
    }

    @Override
    public Size getSize() {
        return null;
    }


    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }


    /**
     * Returns a string representation of the BuildYourOwnPizza object, including details such as order ID, pizza size,
     * extra cheese and sauce options, chosen toppings, total price, tax, and overall total.
     *
     * @return A string representation of the BuildYourOwnPizza object.
     */
    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("OrderID: ").append(orderID).append("\n");
        pizzaDetails.append("Build Your Own Pizza").append("\n");

        pizzaDetails.append("Size: ").append(size).append("\n");
        if (extraCheese) {
            pizzaDetails.append("Extra Cheese: yes\n");
        } else {
            pizzaDetails.append("Extra Cheese: no\n");
        }

        if (extraSauce) {
            pizzaDetails.append("Extra Sauce: yes\n");
        } else {
            pizzaDetails.append("Extra Sauce: no\n");
        }
        pizzaDetails.append("Toppings: ").append(String.join(", ", toppings)).append("\n"); // Include toppings here
        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");
        pizzaDetails.append("Tax: $").append(calculateTax()).append("\n");
        pizzaDetails.append("Total: $").append(total()).append("\n");

        return pizzaDetails.toString();
    }
}
