package com.main.online_clothing_store.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.main.online_clothing_store.models.AdminUser;

public interface IAdminUserService {
    List<AdminUser> getAllAdminUsers();
    Optional<AdminUser> findById(int id);
    Optional<AdminUser> findByEmail(String email);
    AdminUser save(AdminUser adminUser);
    void deleteById(int id);
}
