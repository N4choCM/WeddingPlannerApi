package controllers;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping ("/user")
    private ResponseEntity<User> create (@RequestBody User user){
        User temporalUser = userService.createUser(user);

        //TODO: Check in the browser if the URI bellow works.
        try{
            return ResponseEntity.created(new URI("/api/user" + temporalUser.getlId())).body(temporalUser);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping ("/users")
    private ResponseEntity<List<User>> findAllUsers (){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping ("/user/{id}")
    private ResponseEntity<Optional<User>> findUser (@PathVariable ("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping ("/user/{user}")
    private ResponseEntity<Void> removeUser (@PathVariable ("user") User user){
        userService.deleteUser(user);
        //TODO: Check the return statement because I think it might be better a .noContent()
        return ResponseEntity.ok().build();
    }
}
