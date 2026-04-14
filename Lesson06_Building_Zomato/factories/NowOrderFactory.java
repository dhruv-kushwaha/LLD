package Lesson06_Building_Zomato.factories;

import java.time.LocalDateTime;

import Lesson06_Building_Zomato.models.Cart;
import Lesson06_Building_Zomato.models.DeliveryOrder;
import Lesson06_Building_Zomato.models.Order;
import Lesson06_Building_Zomato.models.PickupOrder;
import Lesson06_Building_Zomato.models.Restaurant;
import Lesson06_Building_Zomato.models.User;
import Lesson06_Building_Zomato.strategies.PaymentStrategy;

public class NowOrderFactory implements OrderFactory {
    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant, PaymentStrategy paymentStrategy,
            double totalCost, String orderType) {

        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
            PickupOrder pickupOrder = new PickupOrder();
            pickupOrder.setRestaurantAddress(restaurant.getLocation());
            order = pickupOrder;
        }

        order.setUser(user);
        order.setTotal(totalCost);
        order.setPaymentStrategy(paymentStrategy);
        order.setRestaurant(restaurant);
        order.setItems(cart.getItems());
        order.setScheduleTime(LocalDateTime.now());
        return order;
    }
}
