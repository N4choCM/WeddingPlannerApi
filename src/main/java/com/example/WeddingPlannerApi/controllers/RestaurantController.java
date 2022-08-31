package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Restaurant;
import com.example.WeddingPlannerApi.repositories.RestaurantRepository;
import com.example.WeddingPlannerApi.services.RestaurantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(RestaurantController.class);


    @RequestMapping(value="/restaurants", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new Restaurant in the DB.")
    public ResponseEntity<Restaurant> createRestaurant(
            @ApiParam("Data of the Restaurant to be entered in the DB.")
            @RequestBody Restaurant restaurant){

        log.info("REST request for entering a new Restaurant in the DB.");

        if(restaurant.getlId() != null){
            log.warn("Trying to enter a Restaurant with an existing ID.");
            return ResponseEntity.badRequest().build();
        }

        Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
        log.info("New Restaurant created.");
        return ResponseEntity.ok(newRestaurant);
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Restaurants in the DB.")
    public List<Restaurant> readRestaurants() {

        log.info("REST request for getting all the Restaurants in the DB.");

        return restaurantService.getAllRestaurants();
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a Restaurant by ID.")
    public ResponseEntity<Restaurant> readRestaurantById(
            @ApiParam("Primary Key of the restaurant to be found in the DB.")
            @PathVariable(value = "lId") Long lId){

        log.info("REST request for getting a Restaurant by ID.");

        Optional<Restaurant> temporalRestaurant = restaurantService.getRestaurantById(lId);
        log.info("Restaurant found.");
        return temporalRestaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a Restaurant.")
    public ResponseEntity<Restaurant> updateRestaurant(
            @ApiParam("Primary Key of the restaurant to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody Restaurant restaurantDetails) {

        log.info("REST request for updating a Restaurant by ID.");

        if(restaurantDetails.getlId() == null){
            log.warn("Trying to update a non existent Restaurant.");
            return ResponseEntity.badRequest().build();
        }

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(lId, restaurantDetails);
        log.info("Restaurant updated.");
        return ResponseEntity.ok(updatedRestaurant);
    }

    @RequestMapping(value="/restaurants/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a Restaurant by ID.")
    public ResponseEntity<Restaurant> deleteRestaurant(
            @ApiParam("Primary Key of the restaurant to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {

        log.info("REST request for deleting a Restaurant by ID.");

        if(!restaurantRepository.existsById(lId)){

            log.warn("Trying to delete a non existent Restaurant.");
            return ResponseEntity.notFound().build();
        }

        restaurantService.deleteRestaurant(lId);
        log.info("Restaurant deleted.");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Restaurants in the DB.")
    public void deleteAll() {

        log.info("REST request for deleting all the Restaurants in the DB.");
        restaurantService.deleteAll();
        log.info("All the Restaurants have been deleted.");
    }
}
