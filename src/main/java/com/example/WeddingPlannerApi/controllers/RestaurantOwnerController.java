package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.RestaurantOwner;
import com.example.WeddingPlannerApi.repositories.RestaurantOwnerRepository;
import com.example.WeddingPlannerApi.services.RestaurantOwnerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @RequestMapping(value="/restaurant_owners", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new Restaurant Owner in the DB.")
    public ResponseEntity<RestaurantOwner> createRestaurantOwner(
            @ApiParam("Data of the Restaurant Owner to be entered in the DB.")
            @RequestBody RestaurantOwner restaurantOwner){

        if(restaurantOwner.getlId() != null){
            System.out.println("Trying to create a restaurant owner with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        RestaurantOwner newRestaurantOwner = restaurantOwnerService.createRestaurantOwner(restaurantOwner);
        return ResponseEntity.ok(newRestaurantOwner);
    }

    @RequestMapping(value="/restaurant_owners", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Restaurant Owners in the DB.")
    public List<RestaurantOwner> readRestaurantOwners() {
        return restaurantOwnerService.getAllRestaurantOwners();
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a Restaurant Owner by ID.")
    public ResponseEntity<RestaurantOwner> readRestaurantOwnerById(
            @ApiParam("Primary Key of the Restaurant Owner to be found in the DB.")
            @PathVariable(value = "lId") Long lId){
        Optional<RestaurantOwner> temporalRestaurantOwner = restaurantOwnerService.getRestaurantOwnerById(lId);
        return temporalRestaurantOwner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a Restaurant Owner by ID.")
    public ResponseEntity<RestaurantOwner> updateRestaurantOwner(
            @ApiParam("Primary Key of the Restaurant Owner to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody RestaurantOwner restaurantOwnerDetails) {

        if(restaurantOwnerDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        RestaurantOwner updatedRestaurantOwner = restaurantOwnerService
                .updateRestaurantOwner(lId, restaurantOwnerDetails);
        return ResponseEntity.ok(updatedRestaurantOwner);
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a Restaurant Owner by ID.")
    public ResponseEntity<RestaurantOwner> deleteRestaurantOwner(
            @ApiParam("Primary Key of the Restaurant Owner to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {
        if(!restaurantOwnerRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        restaurantOwnerService.deleteRestaurantOwner(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/restaurant_owners", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Restaurant Owners in the DB.")
    public void deleteAll() {
        restaurantOwnerService.deleteAll();
    }
}
