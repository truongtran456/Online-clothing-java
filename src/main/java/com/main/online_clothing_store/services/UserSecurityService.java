package com.main.online_clothing_store.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.main.online_clothing_store.models.AdminUser;
import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.models.UserSecurityDetails;
import com.main.online_clothing_store.repositories.AdminUserRepository;
import com.main.online_clothing_store.repositories.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<AdminUser> adminUser = adminUserRepository.findByEmail(username);
        if(adminUser.isPresent()){
            return new UserSecurityDetails(adminUser.get().getEmail(), adminUser.get().getPassword(), adminUser.get().getIs_locked(), "ADMIN");
        }
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isPresent()){
            return new UserSecurityDetails(user.get().getEmail(), user.get().getPassword(), user.get().getIs_locked(), "USER");
        }
        throw new UsernameNotFoundException("Email not found");
    }
    
}
