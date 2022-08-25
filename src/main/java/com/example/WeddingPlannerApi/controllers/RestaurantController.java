package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Restaurant;
import com.example.WeddingPlannerApi.repositories.RestaurantRepository;
import com.example.WeddingPlannerApi.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @RequestMapping(value="/restaurants", method = RequestMethod.POST)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){

        if(restaurant.getlId() != null){
            System.out.println("Trying to create a restaurant with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(newRestaurant);
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    public List<Restaurant> readRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.GET)
    public ResponseEntity<Restaurant> readRestaurantById(@PathVariable(value = "lId") Long lId){
        Optional<Restaurant> temporalRestaurant = restaurantService.getRestaurantById(lId);
        return temporalRestaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.PUT)
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value = "lId") Long lId,
                                                 @RequestBody Restaurant restaurantDetails) {

        if(restaurantDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(lId, restaurantDetails);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.DELETE)
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable(value = "lId") Long lId) {
        if(!restaurantRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        restaurantService.deleteRestaurant(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.DELETE)
    public void deleteAll() {
        restaurantService.deleteAll();
    }
}
