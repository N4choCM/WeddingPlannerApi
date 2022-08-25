package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Restaurant;
import com.example.WeddingPlannerApi.entities.RestaurantOwner;
import com.example.WeddingPlannerApi.repositories.RestaurantOwnerRepository;
import com.example.WeddingPlannerApi.repositories.RestaurantRepository;
import com.example.WeddingPlannerApi.services.RestaurantOwnerService;
import com.example.WeddingPlannerApi.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestaurantOwnerController {

    @Autowired
    private RestaurantOwnerService restaurantOwnerService;

    @Autowired
    private RestaurantOwnerRepository restaurantOwnerRepository;

    @RequestMapping(value="/restaurant_owners", method = RequestMethod.POST)
    public ResponseEntity<RestaurantOwner> createRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner){

        if(restaurantOwner.getlId() != null){
            System.out.println("Trying to create a restaurant owner with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        RestaurantOwner newRestaurantOwner = restaurantOwnerService.createRestaurantOwner(restaurantOwner);
        return ResponseEntity.ok(newRestaurantOwner);
    }

    @RequestMapping(value="/restaurant_owners", method=RequestMethod.GET)
    public List<RestaurantOwner> readRestaurantOwners() {
        return restaurantOwnerService.getAllRestaurantOwners();
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.GET)
    public ResponseEntity<RestaurantOwner> readRestaurantOwnerById(@PathVariable(value = "lId") Long lId){
        Optional<RestaurantOwner> temporalRestaurantOwner = restaurantOwnerService.getRestaurantOwnerById(lId);
        return temporalRestaurantOwner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.PUT)
    public ResponseEntity<RestaurantOwner> updateRestaurantOwner(@PathVariable(value = "lId") Long lId,
                                                       @RequestBody RestaurantOwner restaurantOwnerDetails) {

        if(restaurantOwnerDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        RestaurantOwner updatedRestaurantOwner = restaurantOwnerService
                .updateRestaurantOwner(lId, restaurantOwnerDetails);
        return ResponseEntity.ok(updatedRestaurantOwner);
    }

    @RequestMapping(value="/restaurant_owners/{lId}", method=RequestMethod.DELETE)
    public ResponseEntity<RestaurantOwner> deleteRestaurantOwner(@PathVariable(value = "lId") Long lId) {
        if(!restaurantOwnerRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        restaurantOwnerService.deleteRestaurantOwner(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/restaurant_owners", method=RequestMethod.DELETE)
    public void deleteAll() {
        restaurantOwnerService.deleteAll();
    }
}
