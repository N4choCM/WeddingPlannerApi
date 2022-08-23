package com.example.WeddingPlannerApi.repositories;

import com.example.WeddingPlannerApi.entities.RestaurantOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, Long> {
}
