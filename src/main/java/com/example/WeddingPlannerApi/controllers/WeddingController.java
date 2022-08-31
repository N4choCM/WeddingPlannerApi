package com.example.WeddingPlannerApi.controllers;

import com.example.WeddingPlannerApi.entities.Wedding;
import com.example.WeddingPlannerApi.repositories.WeddingRepository;
import com.example.WeddingPlannerApi.services.WeddingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(WeddingController.class);


    @RequestMapping(value="/weddings", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new Wedding in the DB.")
    public ResponseEntity<Wedding> createWedding(
            @ApiParam("Data of the Wedding to be entered in the DB.")
            @RequestBody Wedding wedding){

        log.info("REST request for entering a new Wedding in the DB.");

        if(wedding.getlId() != null){
            log.warn("Trying to create a wedding with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        Wedding newWedding = weddingService.createWedding(wedding);
        log.info("Wedding created.");
        return ResponseEntity.ok(newWedding);
    }

    @RequestMapping(value="/weddings", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Weddings in the DB.")
    public List<Wedding> readWeddings() {

        log.info("REST request for getting all the Weddings in the DB.");
        return weddingService.getAllWeddings();
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a Wedding by ID.")
    public ResponseEntity<Wedding> readWeddingById(
            @ApiParam("Primary Key of the Wedding to be found in the DB.")
            @PathVariable(value = "lId") Long lId){

        log.info("REST request for getting a Wedding by ID.");
        Optional<Wedding> temporalWedding = weddingService.getWeddingById(lId);
        log.info("Wedding found.");
        return temporalWedding.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a Wedding.")
    public ResponseEntity<Wedding> updateWedding(
            @ApiParam("Primary Key of the Wedding to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody Wedding weddingDetails) {

        log.info("REST request for updating a Wedding.");

        if(weddingDetails.getlId() == null){
            log.warn("Trying to update a non existing Wedding.");
            return ResponseEntity.badRequest().build();
        }

        Wedding updatedWedding = weddingService.updateWedding(lId, weddingDetails);
        log.info("Wedding updated.");
        return ResponseEntity.ok(updatedWedding);
    }

    @RequestMapping(value="/weddings/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a Wedding by ID.")
    public ResponseEntity<Wedding> deleteWedding(
            @ApiParam("Primary Key of the Wedding to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {

        log.info("REST request for deleting a Wedding by ID.");
        if(!weddingRepository.existsById(lId)){
            log.warn("Trying to delete a non existent Wedding.");
            return ResponseEntity.notFound().build();
        }

        weddingService.deleteWedding(lId);
        log.info("Wedding deleted.");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/weddings", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Weddings in the DB.")
    public void deleteAll() {

        log.info("REST request for deleting all the Weddings in the DB.");
        weddingService.deleteAll();
        log.info("All the Weddings have been deleted.");
    }
}
