package com.main.online_clothing_store.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityDetails implements UserDetails{
    private String username;
    private String password;
    private Boolean isLocked;
    private String rolesString;

    public UserSecurityDetails(String username, String password, Boolean isLocked, String rolesString){
        this.username = username;
        this.password = password;
        this.isLocked = isLocked;
        this.rolesString = rolesString;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        String[] authStrings = this.rolesString.split(", ");
        for(String authString : authStrings) {
            roles.add(new SimpleGrantedAuthority(authString));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
