package com.example.WeddingPlannerApi.controllers;

import java.util.List;
import java.util.Optional;

import com.example.WeddingPlannerApi.entities.User;
import com.example.WeddingPlannerApi.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @RequestMapping(value="/users", method = RequestMethod.POST)
    @ApiOperation("REST request for entering a new User in the DB.")
    public ResponseEntity<User> createUser(
            @ApiParam("Data of the User to be entered in the DB.")
            @RequestBody User user){

        if(user.getlId() != null){
            System.out.println("Trying to create a user with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    @ApiOperation("REST request for getting all the Users in the DB.")
    public List<User> readUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.GET)
    @ApiOperation("REST request for getting a User by ID.")
    public ResponseEntity<User> readUserById(
            @ApiParam("Primary Key of the User to be found in the DB.")
            @PathVariable(value = "lId") Long lId){
        Optional<User> temporalUser = userService.getUserById(lId);
        return temporalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.PUT)
    @ApiOperation("REST request for updating a User.")
    public ResponseEntity<User> updateUser(
            @ApiParam("Primary Key of the User to be updated in the DB.")
            @PathVariable(value = "lId") Long lId,
            @RequestBody User userDetails) {

        if(userDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = userService.updateUser(lId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting a User by ID.")
    public ResponseEntity<User> deleteUser(
            @ApiParam("Primary Key of the User to be deleted in the DB.")
            @PathVariable(value = "lId") Long lId) {
        if(!userRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/users", method=RequestMethod.DELETE)
    @ApiOperation("REST request for deleting all the Users in the DB.")
    public void deleteAll() {
        userService.deleteAll();
    }
}
