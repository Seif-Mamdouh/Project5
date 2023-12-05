package com.example.rupizza.RuPizza;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreOrders {
    /**
     * Field for all the store orders.
     */
    private final static StoreOrders instance = new StoreOrders();
    private static int orderIDCounter = 0;

    /**
     * Method to get instance of store orders.
     */
    public static StoreOrders getInstance() {
        return instance;
    }

    /**
     * Field for list of orders in the store right now.
     */
    private List<Integer> displayOrderView = new ArrayList<>();

    private HashMap<Integer, Order> mapping = new HashMap<Integer, Order>();
    /**
     * Gets the mapping of order IDs to their respective orders.
     *
     * @return A HashMap containing order IDs as keys and their respective orders as values.
     */
    public HashMap<Integer, Order> getMapping() {
        return mapping;
    }

    /**
     * Adds an order to the mapping.
     *
     * @param obj The object to be added as an order. Must be an instance of the Order class.
     * @return {@code true} if the object was successfully added as an order, {@code false} otherwise.
     */
    public boolean add(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }

        Order O = (Order) obj;
        mapping.put(orderIDCounter, O);
        displayOrderView.add(orderIDCounter++);
        return true;
    }


    /**
     * Removes an order from the mapping based on its order ID.
     *
     * @param obj The order ID to be removed.
     * @return {@code true} if the order was successfully removed, {@code false} otherwise.
     */
    public boolean remove(Integer obj) {
        if(!mapping.containsKey(obj)) {
            return false;
        }
        displayOrderView.remove(obj);
        mapping.remove(obj);
        return true;
    }

    /**
     * Gets orders numbers in store right now.
     */
    public List<Integer> getOrders() {
        return displayOrderView;
    }

    /**
     * Writes orders to file parameter.
     * @param file pointer to file to write to
     */
//    public void exportTo(File file) {
//        FileWriter exporter = null;
//        try {
//            exporter = new FileWriter(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(mapping.isEmpty())
//        {
//            try {
//                exporter.write("No Orders Currently In System");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            for(Order order:mapping.values())
//            {
//
//                try {
//                    String toWrite = "Store ID: " + order.getStoreID() + "\n";
//                    exporter.write(toWrite);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                for(Object m : order.getPizzas())
//                {
//                    try {
//                        String toWriteItem = "\t" + m.toString() + "\n";
//                        exporter.write(toWriteItem);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        try {
//            exporter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * Calculates the total cost of pizzas in the given order.
     *
     * @param order The order for which to calculate the total cost.
     * @return The total cost of all pizzas in the specified order.
     */
    public double calculateTotal(Order order) {
        double tot = 0;
        for(Order x : mapping.values()) {
            if(x == order) {
            for(Object t : x.getPizzas()) {
                if (t instanceof SpecialityPizza) {
                    tot+=((SpecialityPizza) t).total();
                }
                if (t instanceof BuildYourOwnPizza) {
                    tot+=((BuildYourOwnPizza) t).total();
                }
            }
        }
        }
        return tot;
    }


    public boolean contains(Order order) {
        return mapping.containsValue(order);
    }

}
