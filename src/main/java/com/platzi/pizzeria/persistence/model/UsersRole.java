package com.platzi.pizzeria.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter @Getter
@NoArgsConstructor
public class UsersRole
{
    @Id
    private String username;
    private String rol;
    @Setter(AccessLevel.NONE)
    private LocalDateTime granted_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username",
                referencedColumnName = "username",
                updatable = false,
                insertable = false)
    private Users users;
}
