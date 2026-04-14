package Lesson06_Building_Zomato.models;

import java.util.ArrayList;

public class Cart {
    Restaurant restaurant;
    ArrayList<MenuItem> items = new ArrayList<>();

    public Cart() {
        restaurant = null;
    }

    public void addItems(MenuItem item) {
        if (restaurant == null) {
            System.err.println("Cart: Set a restaurant before adding items.");
            return;
        }
        items.add(item);
    }

    public double getTotalCost() {
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        restaurant = null;
        items.clear();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }
}