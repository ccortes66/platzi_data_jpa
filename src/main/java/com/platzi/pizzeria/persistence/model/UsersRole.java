package com.platzi.pizzeria.persistence.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter
@NoArgsConstructor
@ToString
public class UsersRole 
{   
    @Id
    private String username;
    private String rol;
    @Setter(AccessLevel.NONE)
    private LocalDateTime grantedDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username",
               referencedColumnName = "username",
               updatable = false,
               insertable = false,
               nullable = false )
    private Users users;


}
