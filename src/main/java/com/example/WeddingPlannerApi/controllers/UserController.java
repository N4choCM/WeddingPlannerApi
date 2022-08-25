package com.example.WeddingPlannerApi.controllers;

import java.util.List;
import java.util.Optional;

import com.example.WeddingPlannerApi.entities.User;
import com.example.WeddingPlannerApi.repositories.UserRepository;
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
    public ResponseEntity<User> createUser(@RequestBody User user){

        if(user.getlId() != null){
            System.out.println("Trying to create a user with existing ID.");
            return ResponseEntity.badRequest().build();
        }

        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> readUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.GET)
    public ResponseEntity<User> readUserById(@PathVariable(value = "lId") Long lId){
        Optional<User> temporalUser = userService.getUserById(lId);
        return temporalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable(value = "lId") Long lId, @RequestBody User userDetails) {

        if(userDetails.getlId() == null){
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = userService.updateUser(lId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable(value = "lId") Long lId) {
        if(!userRepository.existsById(lId)){
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(lId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/users", method=RequestMethod.DELETE)
    public void deleteAll() {
        userService.deleteAll();
    }
}
