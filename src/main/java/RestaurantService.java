import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(Restaurant restaurant: restaurants) {
            if(restaurant.getName().equals(restaurantName))
                return restaurant;
        }
        throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved==null)
            throw new restaurantNotFoundException(restaurantName);

        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Double findTotalAmount(Restaurant restaurant, String[] itemNames) throws restaurantNotFoundException {
        Restaurant restaurantToBeCalculated =findRestaurantByName(restaurant.getName());
        double orderAmount=0.0;
        for(String itemName: itemNames){
           Item itemAdded = restaurantToBeCalculated.findItemByName(itemName);
            orderAmount = orderAmount + itemAdded.getPrice();
        }

        return  orderAmount;
    }
}
