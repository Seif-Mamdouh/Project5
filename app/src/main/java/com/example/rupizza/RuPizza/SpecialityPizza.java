package com.example.rupizza.RuPizza;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a specialty pizza in the pizza ordering system, extending the base Pizza class.
 * Manages details such as pizza type, size, and additional toppings like extra sauce and cheese.
 * Provides methods to calculate the total price, tax, and display pizza details.
 * Implements the setOrderID method to assign a unique order ID to each specialty pizza.
 *
 * @author Seifeldeen Mohamed
 */
public class SpecialityPizza extends Pizza {
    /**
     * The type of pizza chosen for the order.
     */
    private PizzaType pizzaType;
    /**
     * The tax rate applied to the pizza order total.
     */
    public static final double TAX_RATE = 0.06625;
    /**
     * The unique identifier for the pizza order.
     */
    private int orderID;

    /**
     * The base price for a deluxe pizza.
     */
    private static final double BASE_PRICE_DELUXE = 14.99;

    /**
     * The base price for a supreme pizza.
     */
    private static final double BASE_PRICE_SUPREME = 15.99;

    /**
     * The base price for a meatzza pizza.
     */
    private static final double BASE_PRICE_MEATZZA = 16.99;

    /**
     * The base price for a seafood pizza.
     */
    private static final double BASE_PRICE_SEAFOOD = 17.99;

    /**
     * The base price for a pepperoni pizza.
     */
    private static final double BASE_PRICE_PEPPERONI = 10.99;

    /**
     * The default base price.
     */
    private static final double DEFAULT = 0.0;

    /**
     * The additional price for a small-sized pizza.
     */
    private static final double SIZE_PRICE_SMALL = 0.0;

    /**
     * The additional price for a medium-sized pizza.
     */
    private static final double SIZE_PRICE_MEDIUM = 2.0;

    /**tty
     * The additional price for a large-sized pizza.
     */
    private static final double SIZE_PRICE_LARGE = 4.0;

    /**
     * The additional price for extra sauce and cheese on the pizza.
     */
    private static final double EXTRA_SAUCE_AND_CHEESE_PRICE = 1.0;

    /**
     * Constructs a SpecialityPizza with the specified parameters.
     *
     * @param pizzaType   The type of specialty pizza.
     * @param size        The size of the pizza (small, medium, large).
     * @param extraSauce  Indicates whether extra sauce is added.
     * @param extraCheese Indicates whether extra cheese is added.
     */
    public SpecialityPizza(PizzaType pizzaType, Size size, boolean extraSauce, boolean extraCheese, List<String> toppings, int quantity) {
        super(toppings, size, extraSauce, extraCheese, quantity);
        this.pizzaType = pizzaType;
    }


    @Override
    public PizzaType getPizzaType() {
        return pizzaType;
    }


    // Implement the abstract method to get the size
    @Override
    public Size getSize() {
        // Return the size specific to SpecialityPizza
        return this.size;
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

    @Override
    public void getOrderID(int orderID) {

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
     * Calculates the total price of the specialty pizza.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double calculatePrice() {
        double basePrice = calculateBasePrice();
        double sizePrice = calculateSizePrice();
        double extraSauceAndCheesePrice = calculateExtraSauceAndCheesePrice();

        return (basePrice + sizePrice + extraSauceAndCheesePrice) * quantity;
    }
    /**
     * Initializes the base prices for each pizza type
     *
     * @return The total price of the pizza.
     */
    public double calculateBasePrice() {
        return switch (pizzaType) {
            case DELUXE -> switch (size) {
                case SMALL, MEDIUM, LARGE -> BASE_PRICE_DELUXE;
            };
            case SUPREME -> switch (size) {
                case SMALL, MEDIUM, LARGE -> BASE_PRICE_SUPREME;
            };
            case MEATZZA-> switch (size) {
                case SMALL, MEDIUM, LARGE -> BASE_PRICE_MEATZZA;
            };
            case SEAFOOD -> switch (size) {
                case SMALL, MEDIUM, LARGE -> BASE_PRICE_SEAFOOD;
            };
            case PEPPERONI, SHRIMP, HALAL, BUFFALO_CHICKEN, SALMON, CHEESE, MIX_GRILL-> switch (size) {
                case SMALL, MEDIUM, LARGE -> BASE_PRICE_PEPPERONI;
            };
            default -> DEFAULT;
        };
    }


    private double calculateSizePrice() {
        switch (size) {
            case SMALL:
                return SIZE_PRICE_SMALL;
            case MEDIUM:
                return SIZE_PRICE_MEDIUM;
            case LARGE:
                return SIZE_PRICE_LARGE;
            default:
                return 0; // or throw an exception for an unknown size
        }
    }

    private double calculateExtraSauceAndCheesePrice() {
        double extraSaucePrice = extraSauce ? EXTRA_SAUCE_AND_CHEESE_PRICE : DEFAULT;
        double extraCheesePrice = extraCheese ? EXTRA_SAUCE_AND_CHEESE_PRICE : DEFAULT;
        return extraSaucePrice + extraCheesePrice;
    }

    /**
     * Returns a string representation of the specialty pizza, including order details.
     *
     * @return A string representation of the pizza.
     */
    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("Order ID: ").append(orderID).append("\n");
        pizzaDetails.append("Pizza Type: ").append(pizzaType).append("\n");
        pizzaDetails.append("Quantity: ").append(quantity).append("\n");
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
        pizzaDetails.append("Toppings: ").append(getDefaultToppings(pizzaType).toString()).append("\n");
        pizzaDetails.append("Total Price: $").append(calculatePrice()).append("\n");
        pizzaDetails.append("Tax: $").append(calculateTax()).append("\n");
        pizzaDetails.append("Total: $").append(total()).append("\n");
        return pizzaDetails.toString();
    }

}

