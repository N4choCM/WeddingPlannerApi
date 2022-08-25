package com.example.WeddingPlannerApi.services;

import com.example.WeddingPlannerApi.entities.Restaurant;
import com.example.WeddingPlannerApi.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long lId){
        return restaurantRepository.findById(lId);
    }

    public Restaurant updateRestaurant(Long lId, Restaurant restaurantDetails){
        Restaurant restaurant = restaurantRepository.findById(lId).get();
        restaurant.setsName(restaurantDetails.getsName());
        restaurant.setsAddress(restaurantDetails.getsAddress());
        restaurant.setiPhone(restaurantDetails.getiPhone());
        restaurant.setbCeliacMenu(restaurantDetails.isbCeliacMenu());
        restaurant.setbKidsMenu(restaurantDetails.isbKidsMenu());
        restaurant.setsDessert(restaurantDetails.getsDessert());
        restaurant.setbWelcomeCocktel(restaurantDetails.isbWelcomeCocktel());
        restaurant.setbVeganMenu(restaurantDetails.isbVeganMenu());
        restaurant.setsStarters(restaurantDetails.getsStarters());
        restaurant.setsMainDish(restaurantDetails.getsMainDish());
        restaurant.setWedding(restaurantDetails.getWedding());

        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long lId){
        restaurantRepository.deleteById(lId);
    }

    public void deleteAll(){
        restaurantRepository.deleteAll();
    }
}
