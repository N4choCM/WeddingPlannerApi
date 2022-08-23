package com.example.WeddingPlannerApi.services;

import java.util.List;
import java.util.Optional;

import com.example.WeddingPlannerApi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.WeddingPlannerApi.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User updateUser (Long lId, User userDetails){
        User user = userRepository.findById(lId).get();
        user.setsName(userDetails.getsName());
        user.setsLastName(userDetails.getsLastName());
        user.setsEmail(userDetails.getsEmail());
        user.setsUserName(userDetails.getsUserName());
        user.setsPassword(userDetails.getsPassword());
        user.setiPhone(userDetails.getiPhone());

        return userRepository.save(user);
    }

    public void deleteUser(Long lId){
        userRepository.deleteById(lId);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }
}
