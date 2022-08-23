package com.example.WeddingPlannerApi.repositories;

import com.example.WeddingPlannerApi.entities.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {
}
