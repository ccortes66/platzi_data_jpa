package com.platzi.pizzeria.persistence.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Users 
{   
   
    @Id
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String email; 
    @Setter(AccessLevel.NONE)
    private Boolean locked;
    @Setter(AccessLevel.NONE)
    private Boolean disabled;

    public Users disabledUser()
    {  
        this.locked = true;
        this.disabled = true;
        return this;
    }
    
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    List<UsersRole> roles;


}
