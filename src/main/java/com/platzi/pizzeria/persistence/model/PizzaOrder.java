package com.platzi.pizzeria.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class PizzaOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NonNull
    @Column(name = "customer_id")
    private Long customerId;
    @Setter(AccessLevel.NONE)
    private LocalDateTime date;
    @NonNull
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Method method;
    @NonNull
    private String additionalNotes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
                referencedColumnName = "id",
                nullable = false,
                insertable = false,
                updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "pizzaOrder")
    @OrderBy("total")
    List<OrderItem> items;

    public static enum Method
    {D,S,C,T}
}
