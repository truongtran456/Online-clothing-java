package com.main.online_clothing_store.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.main.online_clothing_store.models.User;

public interface IUserService {
    List<User> getAllUsers();
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    User save(User user);
    void deleteById(int id);
}
