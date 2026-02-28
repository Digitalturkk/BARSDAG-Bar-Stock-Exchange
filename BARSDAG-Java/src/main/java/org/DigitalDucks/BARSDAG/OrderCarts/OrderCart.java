package org.DigitalDucks.BARSDAG.OrderCarts;

import jakarta.persistence.*;
import org.DigitalDucks.BARSDAG.Drinks.Drink;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_cart")
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Drink> drinks = new ArrayList<>(); // need to create order entity to link to order cart and drinks
    private Integer quantity = 1;

    public OrderCart() {
    }

    public OrderCart(List<Drink> drinks, Integer quantity) {
        this.drinks = drinks;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
