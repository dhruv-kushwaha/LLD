package Lesson06_Building_Zomato;

import Lesson06_Building_Zomato.models.Restaurant;
import Lesson06_Building_Zomato.models.User;
import Lesson06_Building_Zomato.services.NotificationService;
import Lesson06_Building_Zomato.strategies.PaymentStrategy;

import java.time.LocalDateTime;
import java.util.List;

import Lesson06_Building_Zomato.factories.NowOrderFactory;
import Lesson06_Building_Zomato.factories.OrderFactory;
import Lesson06_Building_Zomato.factories.ScheduledOrderFactory;
import Lesson06_Building_Zomato.managers.OrderManager;
import Lesson06_Building_Zomato.managers.RestaurantManager;
import Lesson06_Building_Zomato.models.Cart;
import Lesson06_Building_Zomato.models.MenuItem;
import Lesson06_Building_Zomato.models.Order;

// Orchestrator
public class TomatoApp {

    public TomatoApp() {
        initializeRestaurants();
    }

    private void initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant("Haldiram", "Delhi");
        restaurant1.addMenuItem(new MenuItem("P1", "Samosa", 15));
        restaurant1.addMenuItem(new MenuItem("P2", "Kachori", 30));
        restaurant1.addMenuItem(new MenuItem("P3", "Jalebi", 90));

        Restaurant restaurant2 = new Restaurant("Bikaner", "RDC");
        restaurant1.addMenuItem(new MenuItem("P1", "Rabri", 150));
        restaurant1.addMenuItem(new MenuItem("P2", "Faluda", 80));
        restaurant1.addMenuItem(new MenuItem("P3", "Jalebi", 90));

        Restaurant restaurant3 = new Restaurant("Sandwedges", "London");
        restaurant1.addMenuItem(new MenuItem("P1", "Pizza", 150));
        restaurant1.addMenuItem(new MenuItem("P2", "Burger", 300));
        restaurant1.addMenuItem(new MenuItem("P3", "Bread", 90));

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant3);
    }

    // Search Restaurants
    public List<Restaurant> searchRestaurants(String location) {
        RestaurantManager rm = RestaurantManager.getInstance();
        return rm.searchByLocation(location);
    }

    // Select Restaurant
    // setting the user cart to that restaurant.
    public void selectRestaurant(User user, Restaurant restaurant) {
        Cart cart = user.getCart();
        cart.setRestaurant(restaurant);
    }

    // Add ITem to cart
    public void addToCart(User user, String itemCode) {
        Restaurant restaurant = user.getCart().getRestaurant();
        if (restaurant == null) {
            System.out.println("Please select a restaurant first");
            return;
        }

        for (MenuItem menuItem : restaurant.getMenu()) {
            if (menuItem.getCode().equals(itemCode)) {
                user.getCart().addItems(menuItem);
                break;
            }
        }
    }

    // checkout
    public Order checkoutNow(User user, String orderType, PaymentStrategy paymentStrategy) {
        return checkout(user, orderType, paymentStrategy, new NowOrderFactory());
    }

    public Order checkoutScheduled(User user, String orderType, PaymentStrategy paymentStrategy,
            LocalDateTime scheduledTime) {
        return checkout(user, orderType, paymentStrategy, new ScheduledOrderFactory(scheduledTime));
    }

    public Order checkout(User user, String orderType, PaymentStrategy paymentStrategy, OrderFactory orderFactory) {
        Order order = orderFactory.createOrder(user, user.getCart(), user.getCart().getRestaurant(), paymentStrategy,
                user.getCart().getTotalCost(), orderType);

        OrderManager.getInstance().addOrder(order);
        return order;
    }

    // pay
    public void payForOrder(User user, Order order) {
        boolean isPaymentSuccess = order.processPayment();

        if (isPaymentSuccess) {
            NotificationService.notify(order);
            user.getCart().clear();
        }
    }

    // viewUserCart
    public void printUserCart(User user) {
        System.out.println("Items in cart:");
        System.out.println("------------------------------------");
        for (MenuItem item : user.getCart().getItems()) {
            System.out.println(item.getCode() + " : " + item.getName() + " : ₹" + item.getPrice());
        }
        System.out.println("------------------------------------");
        System.out.println("Grand total : ₹" + user.getCart().getTotalCost());
    }
}
