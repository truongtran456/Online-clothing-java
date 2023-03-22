package com.main.online_clothing_store.services;

import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.main.online_clothing_store.models.User;
import com.main.online_clothing_store.repositories.AdminUserRepository;
import com.main.online_clothing_store.repositories.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;

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
    public User create(User user) throws Exception {
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

    @Transactional
    public User update(User user) throws ObjectNotFoundException, IllegalArgumentException, IOException {
        if(!user.getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new IllegalArgumentException("Can not update your account");
        }
        Optional<User> userUpdateOptional = userRepository.findByEmail(user.getEmail());
        if(userUpdateOptional.isPresent()){
            if(!user.getUpload().isEmpty()){
                user.setAvatar(Base64.getEncoder().encodeToString(user.getUpload().getBytes()));
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -18);
            if(user.getBirthdate().after(calendar.getTime())){
                throw new IllegalArgumentException("User not have enough 18 years old");
            }
            if(!user.getNewPassword().isBlank()){
                user.setPassword(passwordEncoder.encode(user.getNewPassword()));
            }
            Date currentTime = new Date();
            user.setModifiedAt(currentTime);
            return userRepository.save(user);
        }
        throw new ObjectNotFoundException("User does not exists");
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
