package com.platzi.pizzeria.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NonNull
    @Column(name = "order_id")
    private Long orderId;
    @NonNull
    @Column(name = "pizza_id")
    private Long pizzaId;
    @NonNull
    private BigDecimal quantity;
    @NonNull
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",
                referencedColumnName = "id",
                insertable = false,
                updatable = false)
    private PizzaOrder pizzaOrder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id",
                referencedColumnName = "id",
                insertable = false,
                updatable = false)
    private Pizza pizza;
}
