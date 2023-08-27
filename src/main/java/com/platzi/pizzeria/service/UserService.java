package com.platzi.pizzeria.service;

import org.springframework.stereotype.Service;

import com.platzi.pizzeria.persistence.model.Users;
import com.platzi.pizzeria.persistence.model.UsersRole;
import com.platzi.pizzeria.persistence.repository.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserService implements UserDetailsService
{
    private final UserRepository repository;

    
    @Autowired
    public UserService(UserRepository repository) 
    {
        this.repository = repository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        Users users = repository.findById(username.trim())
                                .orElseThrow(() -> new NoSuchElementException());

        String [] roles = users.getRoles().stream()
                                          .map(UsersRole::getRol)
                                          .toArray(String[]::new);
        

        return User.builder()
                   .username(users.getUsername())
                   .password(users.getPassword())
                   .authorities(new CustomGrantedAutority(roles).grantedAuthority())
                   .accountLocked(users.getLocked())
                   .disabled(users.getDisabled())
                   .build();
    } 
}

@RequiredArgsConstructor
class CustomGrantedAutority
{   
    
    private final String[] myRoles;
    
    private String [] getAutorities(String rol)
    {
        if("ADMIN".equals(rol) || "CUSTOMER".equals(rol))
           {return new String[] {"random_order"};}
        return new String[] {}; 
    }

    public List<GrantedAuthority> grantedAuthority()
    {   
        List<GrantedAuthority> authorities = new ArrayList<>(myRoles.length);
        for(String role :myRoles)
        {
           authorities.add(new SimpleGrantedAuthority("ROLE_" +role));
           for(String authority: this.getAutorities(role))
              {authorities.add(new SimpleGrantedAuthority(authority));}
        }
        return authorities;
    }

}
