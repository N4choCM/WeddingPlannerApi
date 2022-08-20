package com.example.WeddingPlannerApi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.WeddingPlannerApi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WeddingPlannerApi.services.UserService;

@RestController
@RequestMapping ("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> readUsers() {
        return userService.getAllUsers();
    }

    //TODO: Test whether this works.
    @RequestMapping(value="/users/{lId}", method=RequestMethod.GET)
    public Optional<User> readUserById(@PathVariable(value = "lId") Long lId){
        return userService.getUserById(lId);
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.PUT)
    public User updateUser(@PathVariable(value = "lId") Long lId, @RequestBody User userDetails) {
        return userService.updateUser(lId, userDetails);
    }

    @RequestMapping(value="/users/{lId}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "lId") Long lId) {
        userService.deleteUser(lId);
    }

    //TODO: Test whether this works.
    @RequestMapping(value="/users", method=RequestMethod.DELETE)
    public void deleteAll() {
        userService.deleteAll();
    }
}
