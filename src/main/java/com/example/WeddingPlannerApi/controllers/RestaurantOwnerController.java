package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.RestaurantOwner;
import com.example.WeddingPlannerApi.repositories.RestaurantOwnerRepository;
import com.example.WeddingPlannerApi.services.RestaurantOwnerService;
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
public class RestaurantOwnerController {

    @Autowired
    private RestaurantOwnerService restaurantOwnerService;

    @Autowired
    private RestaurantOwnerRepository restaurantOwnerRepository;

    private final Logger log = LoggerFactory.getLogger(RestaurantOwnerController.class);

    @RequestMapping(value="/restaurant_owners", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new Restaurant Owner in the DB.")
    public ResponseEntity<RestaurantOwner> createRestaurantOwner(
            @ApiParam("Data of the Restaurant Owner to be entered in the DB.")
            @RequestBody RestaurantOwner restaurantOwner){

        log.info("REST request for entering a new Restaurant Owner in the DB.");

        if(restaurantOwner.getlId() != null){
            log.warn("Trying to enter a Restaurant Owner with an existing ID.");
            return ResponseEntity.badRequest().build();
        }

        RestaurantOwner newRestaurantOwner = restaurantOwnerService.createRestaurantOwner(restaurantOwner);
        log.info("New Restaurant Owner created.");
        return ResponseEntity.ok(newRestaurantOwner);
    }

    @RequestMapping(value="/restaurant_owners", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Restaurant Owners in the DB.")
    public List<RestaurantOwner> readRestaurantOwners() {

        log.info("REST request for getting all the Restaurant Owners in the DB.");

        return restaurantOwnerService.getAllRestaurantOwners();
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a Restaurant Owner by ID.")
    public ResponseEntity<RestaurantOwner> readRestaurantOwnerById(
            @ApiParam("Primary Key of the Restaurant Owner to be found in the DB.")
            @PathVariable(value = "lId") Long lId){

        log.info("REST request for getting a Restaurant Owner by ID.");

        Optional<RestaurantOwner> temporalRestaurantOwner = restaurantOwnerService.getRestaurantOwnerById(lId);
        log.info("Restaurant Owner found.");
        return temporalRestaurantOwner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a Restaurant Owner by ID.")
    public ResponseEntity<RestaurantOwner> updateRestaurantOwner(
            @ApiParam("Primary Key of the Restaurant Owner to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody RestaurantOwner restaurantOwnerDetails) {

        log.info("REST request for updating a Restaurant Owner by ID.");

        if(restaurantOwnerDetails.getlId() == null){
            log.warn("Trying to update a non existent Restaurant Owner.");
            return ResponseEntity.badRequest().build();
        }

        RestaurantOwner updatedRestaurantOwner = restaurantOwnerService
                .updateRestaurantOwner(lId, restaurantOwnerDetails);
        log.info("Restaurant Owner updated.");
        return ResponseEntity.ok(updatedRestaurantOwner);
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a Restaurant Owner by ID.")
    public ResponseEntity<RestaurantOwner> deleteRestaurantOwner(
            @ApiParam("Primary Key of the Restaurant Owner to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {

        log.info("REST request for deleting a Restaurant Owner by ID.");

        if(!restaurantOwnerRepository.existsById(lId)){
            log.warn("Trying to delete a non existent Restaurant Owner.");
            return ResponseEntity.notFound().build();
        }

        restaurantOwnerService.deleteRestaurantOwner(lId);
        log.info("Restaurant Owner deleted.");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/restaurant_owners", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Restaurant Owners in the DB.")
    public void deleteAll() {

        log.info("REST request for deleting all the Restaurant Owners in the DB.");
        restaurantOwnerService.deleteAll();
        log.info("All the Restaurant Owners have been deleted.");
    }
}
