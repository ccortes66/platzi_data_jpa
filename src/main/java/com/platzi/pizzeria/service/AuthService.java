package com.platzi.pizzeria.service;

import com.platzi.pizzeria.persistence.model.Users;
import com.platzi.pizzeria.persistence.model.UsersRole;
import com.platzi.pizzeria.persistence.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService
{
    private final UsersRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Users users = repository.findById(username)
                                .orElseThrow(NoSuchElementException::new);

        String[] rols = users.getRoles()
                                 .stream()
                                 .map(UsersRole::getRol)
                                 .toArray(String[]::new);


        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .accountLocked(users.getLocked())
                .roles(rols)
                .disabled(users.getDisabled())
                .build();
    }
}
