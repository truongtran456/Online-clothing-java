package com.main.online_clothing_store.services;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.repositories.AdminUserRepository;
import com.main.online_clothing_store.repositories.UserRepository;

@Service
public class UserService {
    UserRepository userRepository;
    AdminUserRepository adminUserRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder  = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User save(User user) throws Exception {
        if(adminUserRepository.findByEmail(user.getEmail()).isPresent()){
            throw new Exception("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date currentTime = new Date();
        user.setLastLogin(currentTime);
        user.setIsLocked(false);
        user.setCreatedAt(currentTime);
        user.setModifiedAt(currentTime);
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
   
    public Boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
        return Pattern.matches(regex, password);
    }

    public Boolean isValidRetypePassword(String password, String retypePassword) {
        return Objects.equals(password, retypePassword);
    }
}
