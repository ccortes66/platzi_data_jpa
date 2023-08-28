package com.platzi.pizzeria.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Users
{
    @Id
    private String username;
    private String password;
    private String email;
    private Boolean locked;
    private Boolean disabled;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    List<UsersRole> roles;
}
