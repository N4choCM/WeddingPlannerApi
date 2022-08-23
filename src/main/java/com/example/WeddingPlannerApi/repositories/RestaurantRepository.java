package com.example.WeddingPlannerApi.repositories;

import com.example.WeddingPlannerApi.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
