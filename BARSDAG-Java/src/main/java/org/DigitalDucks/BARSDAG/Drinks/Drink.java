package org.DigitalDucks.BARSDAG.Drinks;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "Description is mandatory")
    @Column(nullable = false)
    private String description;
    // price variables for opening, current and closing prices
    @NotNull(message = "Open Price is mandatory")
    @Column(nullable = false)
    private Double openPrice;
    private Double priceRightNow;
    private Double closedPrice;

    public Drink() {
    }

    public Drink(Long id, String name, String description, Double openPrice,
                 Double priceRightNow, Double closedPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.openPrice = openPrice;
        this.priceRightNow = priceRightNow;
        this.closedPrice = closedPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getPriceRightNow() {
        return priceRightNow;
    }

    public void setPriceRightNow(Double priceRightNow) {
        this.priceRightNow = priceRightNow;
    }

    public Double getClosedPrice() {
        return closedPrice;
    }

    public void setClosedPrice(Double closedPrice) {
        this.closedPrice = closedPrice;
    }
}
