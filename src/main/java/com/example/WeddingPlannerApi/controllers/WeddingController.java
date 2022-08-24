package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Wedding;
import com.example.WeddingPlannerApi.repositories.WeddingRepository;
import com.example.WeddingPlannerApi.services.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WeddingController {
    @Autowired
    private WeddingService weddingService;

    @Autowired
    private WeddingRepository weddingRepository;

    @RequestMapping(value="/weddings", method = RequestMethod.POST)
    public ResponseEntity<Wedding> createWedding(@RequestBody Wedding wedding){

        if(wedding.getlId() != null){
            System.out.println("Trying to create a wedding with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        Wedding newWedding = weddingService.createWedding(wedding);
        return ResponseEntity.ok(newWedding);
    }

    @RequestMapping(value="/weddings", method=RequestMethod.GET)
    public List<Wedding> readWeddings() {
        return weddingService.getAllWeddings();
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.GET)
    public ResponseEntity<Wedding> readWeddingById(@PathVariable(value = "lId") Long lId){
        Optional<Wedding> temporalWedding = weddingService.getWeddingById(lId);
        return temporalWedding.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.PUT)
    public ResponseEntity<Wedding> updateWedding(@PathVariable(value = "lId") Long lId,
                                                 @RequestBody Wedding weddingDetails) {

        if(weddingDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        Wedding updatedWedding = weddingService.updateWedding(lId, weddingDetails);
        return ResponseEntity.ok(updatedWedding);
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.DELETE)
    public ResponseEntity<Wedding> deleteWedding(@PathVariable(value = "lId") Long lId) {
        if(!weddingRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        weddingService.deleteWedding(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/weddings", method=RequestMethod.DELETE)
    public void deleteAll() {
        weddingService.deleteAll();
    }
}
