package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Restaurant;
import com.example.WeddingPlannerApi.repositories.RestaurantRepository;
import com.example.WeddingPlannerApi.services.RestaurantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @RequestMapping(value="/restaurants", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new Restaurant in the DB.")
    public ResponseEntity<Restaurant> createRestaurant(
            @ApiParam("Data of the Restaurant to be entered in the DB.")
            @RequestBody Restaurant restaurant){

        if(restaurant.getlId() != null){
            System.out.println("Trying to create a restaurant with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(newRestaurant);
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Restaurants in the DB.")
    public List<Restaurant> readRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a Restaurant by ID.")
    public ResponseEntity<Restaurant> readRestaurantById(
            @ApiParam("Primary Key of the restaurant to be found in the DB.")
            @PathVariable(value = "lId") Long lId){
        Optional<Restaurant> temporalRestaurant = restaurantService.getRestaurantById(lId);
        return temporalRestaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a Restaurant.")
    public ResponseEntity<Restaurant> updateRestaurant(
            @ApiParam("Primary Key of the restaurant to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody Restaurant restaurantDetails) {

        if(restaurantDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(lId, restaurantDetails);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a Restaurant by ID.")
    public ResponseEntity<Restaurant> deleteRestaurant(
            @ApiParam("Primary Key of the restaurant to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {
        if(!restaurantRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        restaurantService.deleteRestaurant(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Restaurants in the DB.")
    public void deleteAll() {
        restaurantService.deleteAll();
    }
}
