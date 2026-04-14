package Lesson06_Building_Zomato.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Lesson06_Building_Zomato.strategies.PaymentStrategy;

public abstract class Order {
    private static int _nextOrderId = 0;

    protected int orderId;
    protected Restaurant restaurant;
    protected User user;
    protected PaymentStrategy paymentStrategy;
    protected double total;
    protected LocalDateTime scheduleTime;
    protected List<MenuItem> items = new ArrayList<>();

    public Order() {
        orderId = ++_nextOrderId;
        restaurant = null;
        user = null;
        total = 0.0;
        paymentStrategy = null;
        items = null;
    }

    public abstract String getType();

    public int getOrderId() {
        return this.orderId;
    }

    public boolean processPayment() {
        if (paymentStrategy != null) {
            paymentStrategy.pay(total);
            return true;
        } else {
            System.err.println("Please choose a payment mode first");
            return false;
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
        total = 0;
        for (MenuItem menuItem : items) {
            total += menuItem.getPrice();
        }
    }
}
