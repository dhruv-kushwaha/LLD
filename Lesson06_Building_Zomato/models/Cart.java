package Lesson06_Building_Zomato.models;

import java.util.ArrayList;

public class Cart {
    Restraunt restraunt;
    ArrayList<MenuItem> items = new ArrayList<>();

    public Cart() {
        restraunt = null;
    }

    public void addItems(MenuItem item) {
        if (restraunt == null) {
            System.err.println("Cart: Set a restraunt before adding items.");
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
        restraunt = null;
        items.clear();
    }

    public Restraunt getRestraunt() {
        return restraunt;
    }

    public void setRestraunt(Restraunt restraunt) {
        this.restraunt = restraunt;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }
}