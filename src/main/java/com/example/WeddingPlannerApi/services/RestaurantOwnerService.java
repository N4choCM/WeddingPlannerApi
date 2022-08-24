package com.example.WeddingPlannerApi.services;

import com.example.WeddingPlannerApi.entities.RestaurantOwner;
import com.example.WeddingPlannerApi.repositories.RestaurantOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantOwnerService {
    @Autowired
    private RestaurantOwnerRepository restaurantOwnerRepository;

    public RestaurantOwner createRestaurantOwner(RestaurantOwner restaurantOwner){
        return restaurantOwnerRepository.save(restaurantOwner);
    }

    public List<RestaurantOwner> getAllRestaurantOwners(){
        return restaurantOwnerRepository.findAll();
    }

    public Optional<RestaurantOwner> getRestaurantOwnerById(Long lId){
        return restaurantOwnerRepository.findById(lId);
    }

    public RestaurantOwner updateRestaurantOwner(Long lId, RestaurantOwner restaurantOwnerDetails){
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(lId).get();
        restaurantOwner.setiPhone(restaurantOwnerDetails.getiPhone());
        restaurantOwner.setsEmail(restaurantOwnerDetails.getsEmail());
        restaurantOwner.setsName(restaurantOwnerDetails.getsName());
        restaurantOwner.setsLastName(restaurantOwnerDetails.getsLastName());
        restaurantOwner.setRestaurant(restaurantOwnerDetails.getRestaurant());

        return restaurantOwnerRepository.save(restaurantOwner);
    }

    public void deleteRestaurantOwner(Long lId){
        restaurantOwnerRepository.deleteById(lId);
    }

    public void deleteAll(){
        restaurantOwnerRepository.deleteAll();
    }
}
