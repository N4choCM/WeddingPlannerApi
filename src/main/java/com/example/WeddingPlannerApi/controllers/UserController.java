package com.example.WeddingPlannerApi.controllers;

import java.util.List;
import java.util.Optional;

import com.example.WeddingPlannerApi.entities.User;
import com.example.WeddingPlannerApi.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WeddingPlannerApi.services.UserService;

@RestController
@RequestMapping ("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/users", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new User in the DB.")
    public ResponseEntity<User> createUser(
            @ApiParam("Data of the User to be entered in the DB.")
            @RequestBody User user){

        log.info("REST request for entering a new User in the DB.");

        if(user.getlId() != null){
            log.warn("Trying to create a User with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        User newUser = userService.createUser(user);
        log.info("New User created.");
        return ResponseEntity.ok(newUser);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Users in the DB.")
    public List<User> readUsers() {

        log.info("REST request for getting all the Users in the DB.");
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a User by ID.")
    public ResponseEntity<User> readUserById(
            @ApiParam("Primary Key of the User to be found in the DB.")
            @PathVariable(value = "lId") Long lId){

        log.info("REST request for getting a User by ID in the DB.");
        Optional<User> temporalUser = userService.getUserById(lId);
        log.info("User found.");
        return temporalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a User.")
    public ResponseEntity<User> updateUser(
            @ApiParam("Primary Key of the User to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody User userDetails) {

        log.info("REST request for updating a User.");

        if(userDetails.getlId() == null){
            log.warn("Trying to update a non existent User.");
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = userService.updateUser(lId, userDetails);
        log.info("User updated.");
        return ResponseEntity.ok(updatedUser);
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a User by ID.")
    public ResponseEntity<User> deleteUser(
            @ApiParam("Primary Key of the User to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {

        log.info("REST request for deleting a User by ID.");
        if(!userRepository.existsById(lId)){
            log.warn("Trying to delete a non existent User.");
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(lId);
        log.info("User deleted.");
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/users", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Users in the DB.")
    public void deleteAll() {

        log.info("REST request for deleting all the Users in the DB.");
        userService.deleteAll();
        log.info("All the Users have been deleted.");
    }
}
