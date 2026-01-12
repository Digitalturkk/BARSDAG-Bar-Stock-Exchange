package org.DigitalDucks.BARSDAG.Sales;

import jakarta.persistence.*;
import org.DigitalDucks.BARSDAG.Drinks.Drink;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;
    private Integer quantity;
    private LocalDateTime saleTime;

    public Sale() {
    }

    public Sale(Long id, Drink drink, Integer quantity) {
        this.id = id;
        this.drink = drink;
        this.quantity = quantity;
        this.saleTime = LocalDateTime.now(); // Set sale time to current time by default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }
}
