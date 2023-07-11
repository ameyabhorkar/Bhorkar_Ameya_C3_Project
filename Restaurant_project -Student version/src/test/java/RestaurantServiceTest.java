import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant1,restaurant2;

  /*  @BeforeEach
    public void init(){
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);

    }*/

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
       Restaurant result = service.findRestaurantByName("Amelie's cafe");
       assertEquals("Amelie's cafe",result.getName());
    }


    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        assertThrows(restaurantNotFoundException.class,()-> {
            Restaurant result = service.findRestaurantByName("Taj cafe");
        });

    }

    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant1 = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant1.addToMenu("Vegetable lasagne", 269);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant1 = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant1.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant1 = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant1.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }


   @Test
    public void return_total_order_value_after_selecting_items_from_menu() throws restaurantNotFoundException{
        LocalTime openingTime1 = LocalTime.parse("10:30:00");
        LocalTime closingTime1 = LocalTime.parse("22:00:00");
        restaurant1 = service.addRestaurant("Amelie's cafe","Chennai",openingTime1,closingTime1);
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant1.addToMenu("Vegetable lasagne", 269);
        restaurant1.addToMenu("maggi", 100);
        restaurant1.addToMenu("pasta", 150);

        restaurant2 = service.addRestaurant("Neha cafe","Pune",openingTime1,closingTime1);
        restaurant2.addToMenu("chicken soup",150);
        restaurant2.addToMenu("biryani", 300);
        restaurant2.addToMenu("burger", 100);
        restaurant2.addToMenu("sandwich", 150);

        Restaurant result = service.findRestaurantByName("Amelie's cafe");


       ArrayList<Item> order = new ArrayList<>();
       order.add(result.getMenu().get(1));
       order.add(result.getMenu().get(2));
       int ordertotal = service.totalOrderCost(order);
       System.out.println(ordertotal);



        }





    }
