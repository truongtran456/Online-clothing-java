package com.main.online_clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.repositories.UserRepository;
import com.main.online_clothing_store.services.interfaces.IUserService;

@Service
public class UserService implements IUserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }
    
}
