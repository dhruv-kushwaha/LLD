package Lesson06_Building_Zomato.managers;

import java.util.ArrayList;
import java.util.List;

import Lesson06_Building_Zomato.models.Restaurant;

public class RestaurantManager {
    private List<Restaurant> restaurants = new ArrayList<>();
    private static RestaurantManager instance = null;

    private RestaurantManager() {
        // private constructor
    }

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public List<Restaurant> searchByLocation(String location) {
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getLocation().equalsIgnoreCase(location))
                result.add(restaurant);
        }
        return result;
    }
}
