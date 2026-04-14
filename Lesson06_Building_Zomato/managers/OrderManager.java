package Lesson06_Building_Zomato.managers;

import java.util.ArrayList;
import java.util.List;

import Lesson06_Building_Zomato.models.Order;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private static OrderManager instance;

    private OrderManager() {
        // empty constructor
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void listOrders() {
        System.out.println("\n-----All Orders-----");
        for (Order order : orders) {
            System.out.println(order.getType() + " order for " + order.getUser().getName()
                    + " | Total: ₹" + order.getTotal()
                    + " | At: " + order.getScheduleTime());
        }
    }
}
