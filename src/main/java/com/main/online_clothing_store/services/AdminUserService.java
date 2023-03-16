package com.main.online_clothing_store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.repositories.AdminUserRepository;
import com.main.online_clothing_store.services.interfaces.IAdminUserService;

@Service
public class AdminUserService implements IAdminUserService {
    AdminUserRepository adminUserRepository;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository){
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public List<AdminUser> getAllAdminUsers() {
        // TODO Auto-generated method stub
        return adminUserRepository.findAll();
    }

    @Override
    public Optional<AdminUser> findById(int id) {
        // TODO Auto-generated method stub
        return adminUserRepository.findById(id);
    }

    @Override
    public Optional<AdminUser> findByEmail(String email) {
        // TODO Auto-generated method stub
        return adminUserRepository.findByEmail(email);
    }

    @Override
    public AdminUser save(AdminUser adminUser) {
        // TODO Auto-generated method stub
        return adminUserRepository.save(adminUser);
    }

    @Override
    public void deleteById(int id) {
        // TODO Auto-generated method stub
        adminUserRepository.deleteById(id);
    }
    
}
