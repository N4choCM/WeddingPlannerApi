package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Wedding;
import com.example.WeddingPlannerApi.repositories.WeddingRepository;
import com.example.WeddingPlannerApi.services.WeddingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api")
public class WeddingController {
    @Autowired
    private WeddingService weddingService;

    @Autowired
    private WeddingRepository weddingRepository;

    @RequestMapping(value="/weddings", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new Wedding in the DB.")
    public ResponseEntity<Wedding> createWedding(
            @ApiParam("Data of the Wedding to be entered in the DB.")
            @RequestBody Wedding wedding){

        if(wedding.getlId() != null){
            System.out.println("Trying to create a wedding with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        Wedding newWedding = weddingService.createWedding(wedding);
        return ResponseEntity.ok(newWedding);
    }

    @RequestMapping(value="/weddings", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Weddings in the DB.")
    public List<Wedding> readWeddings() {
        return weddingService.getAllWeddings();
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a Wedding by ID.")
    public ResponseEntity<Wedding> readWeddingById(
            @ApiParam("Primary Key of the Wedding to be found in the DB.")
            @PathVariable(value = "lId") Long lId){
        Optional<Wedding> temporalWedding = weddingService.getWeddingById(lId);
        return temporalWedding.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a Wedding.")
    public ResponseEntity<Wedding> updateWedding(
            @ApiParam("Primary Key of the Wedding to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody Wedding weddingDetails) {

        if(weddingDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        Wedding updatedWedding = weddingService.updateWedding(lId, weddingDetails);
        return ResponseEntity.ok(updatedWedding);
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a Wedding by ID.")
    public ResponseEntity<Wedding> deleteWedding(
            @ApiParam("Primary Key of the Wedding to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {
        if(!weddingRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        weddingService.deleteWedding(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/weddings", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Weddings in the DB.")
    public void deleteAll() {
        weddingService.deleteAll();
    }
}
