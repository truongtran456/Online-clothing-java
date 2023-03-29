package com.main.online_clothing_store.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.models.Role;
import com.main.online_clothing_store.models.RoleAdminUser;
import com.main.online_clothing_store.models.composite_primary_keys.RoleAdminUserId;
import com.main.online_clothing_store.repositories.AdminUserRepository;
import com.main.online_clothing_store.repositories.RoleAdminUserRepository;
import com.main.online_clothing_store.repositories.RoleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class RoleAdminUserService {
    RoleAdminUserRepository roleAdminUserRepository;
    AdminUserRepository adminUserRepository;
    RoleRepository roleRepository;
    @Autowired
    public RoleAdminUserService( RoleAdminUserRepository roleAdminUserRepository,AdminUserRepository adminUserRepository,RoleRepository roleRepository) {
        this.roleAdminUserRepository = roleAdminUserRepository;
        this.adminUserRepository = adminUserRepository;
        this.roleRepository = roleRepository;
    }
    
    public List<RoleAdminUser> getAllAdminUsers() {
        return roleAdminUserRepository.findAll();
    }
    @Transactional
    public RoleAdminUser create(Integer adminUserId,Integer roleId) {
        RoleAdminUser roleAdminUser = new RoleAdminUser();
        roleAdminUser.setId(new RoleAdminUserId(adminUserId, roleId));
        Optional<AdminUser> adminUser = adminUserRepository.findById(adminUserId);
        Optional<Role> role = roleRepository.findById(roleId);
        Date currentTime = new Date();
        roleAdminUser.setCreatedAt(currentTime);
        roleAdminUser.setRole(role.get());
        roleAdminUser.setAdminUser(adminUser.get());
        System.out.println("test2");
        return roleAdminUserRepository.save(roleAdminUser);
    }
    @Transactional
    public void delete(Integer adminUserId, Integer roleId) {
        roleAdminUserRepository.deleteByIdAdminUserIdAndIdRoleId(adminUserId,roleId);
    }
}
