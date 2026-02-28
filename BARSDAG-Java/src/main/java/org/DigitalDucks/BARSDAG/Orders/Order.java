package org.DigitalDucks.BARSDAG.Orders;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import org.DigitalDucks.BARSDAG.Drinks.Drink;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Max(3)
    private String orderNumber; // Using String to allow for leading zeros, e.g., "001", "002", etc.
    @OneToMany
    private List<Drink> ordersDrinks;

    public Order() {
    }

    public Order(Long id, String orderNumber, List<Drink> ordersDrinks) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.ordersDrinks = ordersDrinks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Drink> getOrdersDrinks() {
        return ordersDrinks;
    }

    public void setOrdersDrinks(List<Drink> ordersDrinks) {
        this.ordersDrinks = ordersDrinks;
    }
}
