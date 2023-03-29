package com.main.online_clothing_store.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.models.Role;
import com.main.online_clothing_store.repositories.AdminUserRepository;
import com.main.online_clothing_store.repositories.RoleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AdminUserService {
    AdminUserRepository adminUserRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder){
        this.adminUserRepository = adminUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AdminUser> getAllAdminUsers() {
        return adminUserRepository.findAll();
    }

    public Optional<AdminUser> findById(int id) {
        return adminUserRepository.findById(id);
    }

    public Optional<AdminUser> findByEmail(String email) {
        return adminUserRepository.findByEmail(email);
    }

    public AdminUser save(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    public void deleteById(int id) {
        adminUserRepository.deleteById(id);
    }

    public boolean isValidRetypePassword(String password, String retypePassword) {
        return Objects.equals(password, retypePassword);
    }
    @Transactional
    public AdminUser create(AdminUser adminUser) throws IOException {
        Date currentTime = new Date();
        adminUser.setCreatedAt(currentTime);
        adminUser.setModifiedAt(currentTime);
        adminUser.setLastLogin(currentTime);
        adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
        if(adminUser.getUploadAvatar()!=null){
            adminUser.setAvatar(Base64.getEncoder().encodeToString(adminUser.getUploadAvatar().getBytes()));
        }
        return adminUserRepository.save(adminUser);
    }
    @Transactional
    public AdminUser update(AdminUser adminUser) throws IllegalArgumentException, IOException {
        Optional<AdminUser> adminUserUpdateOptional = adminUserRepository.findById(adminUser.getId());
        if (adminUserUpdateOptional.isPresent()) {
            if (!adminUser.getUploadAvatar().isEmpty()) {
                adminUser.setAvatar(Base64.getEncoder().encodeToString(adminUser.getUploadAvatar().getBytes()));
            }
            Date currentTime = new Date();
            adminUser.setModifiedAt(currentTime);
            adminUser.setLastLogin(currentTime);
            if(!adminUser.getNewPassword().isBlank()){
                adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
            }
            return adminUserRepository.save(adminUser);
        }
        return adminUser;
    }
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
        return Pattern.matches(regex, password);
    }

}
