package Lesson06_Building_Zomato.models;

import java.util.ArrayList;
import java.util.List;

import Lesson06_Building_Zomato.strategies.PaymentStrategy;

public abstract class Order {
    private static int _nextOrderId = 0;

    protected int orderId;
    protected Restraunt restraunt;
    protected User user;
    protected PaymentStrategy paymentStrategy;
    protected double total;
    protected List<MenuItem> items = new ArrayList<>();

    public Order() {
        orderId = ++_nextOrderId;
        restraunt = null;
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

    public Restraunt getRestraunt() {
        return restraunt;
    }

    public void setRestraunt(Restraunt restraunt) {
        this.restraunt = restraunt;
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
