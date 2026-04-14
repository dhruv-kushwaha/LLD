package Lesson06_Building_Zomato.factories;

import Lesson06_Building_Zomato.models.*;
import Lesson06_Building_Zomato.strategies.PaymentStrategy;;

public interface OrderFactory {
    // If we were to just provide the user and get the cart, restaurant, menuitems
    // etc from that then it would break principle of least knowledge.
    Order createOrder(User user, Cart cart, Restaurant restaurant, PaymentStrategy paymentStrategy, double totalCost,
            String orderType);
}
