package com.example.WeddingPlannerApi.repositories;

import com.example.WeddingPlannerApi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
