package com.example.WeddingPlannerApi.services;

import com.example.WeddingPlannerApi.entities.Wedding;
import com.example.WeddingPlannerApi.repositories.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeddingService {

    @Autowired
    private WeddingRepository weddingRepository;

    public Wedding createWedding(Wedding wedding){
        return weddingRepository.save(wedding);
    }

    public List<Wedding> getAllWeddings(){
        return weddingRepository.findAll();
    }

    public Optional<Wedding> getWeddingById(Long lId){
        return weddingRepository.findById(lId);
    }
    public Wedding updateWedding (Long lId, Wedding weddingDetails) {
        Wedding wedding = weddingRepository.findById(lId).get();
        wedding.setbChristian(weddingDetails.isbChristian());
        wedding.setRestaurant(weddingDetails.getRestaurant());
        wedding.setsAddress(weddingDetails.getsAddress());
        wedding.setUser(weddingDetails.getUser());

        return weddingRepository.save(wedding);
    }

    public void deleteWedding(Long lId){
        weddingRepository.deleteById(lId);
    }

    public void deleteAll(){
        weddingRepository.deleteAll();
    }
}
