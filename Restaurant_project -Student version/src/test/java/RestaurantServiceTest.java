import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    @BeforeEach
    public void init(){
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        LocalTime openingTime2 = LocalTime.parse("09:30:00");
        LocalTime closingTime2 = LocalTime.parse("23:00:00");
        service.addRestaurant("Neha's cafe","Pune",openingTime2,closingTime2);
    }

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {

       Restaurant result = service.findRestaurantByName("Neha cafe");
       assertEquals("Neha cafe",result.getName());
    }


    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()-> {
            Restaurant result = service.findRestaurantByName("Taj cafe");
        });

    }

    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }

}