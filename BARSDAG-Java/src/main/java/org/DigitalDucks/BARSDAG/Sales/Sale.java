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
    private Drink drink; // need to change it to orders
    private Integer quantity;
    private LocalDateTime saleTime;

    public Sale() {
    }

    // Automatically set saleTime before persisting
    @PrePersist
    protected void onCreate() {
        this.saleTime = LocalDateTime.now();
    }

    public Sale(Drink drink, Integer quantity) {
        this.drink = drink;
        this.quantity = quantity;
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
