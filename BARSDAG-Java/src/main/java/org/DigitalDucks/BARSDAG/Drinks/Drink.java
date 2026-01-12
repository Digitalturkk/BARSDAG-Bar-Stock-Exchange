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
    private Double OpenPrice;
    private Double PriceRightNow;
    private Double ClosedPrice;

    public Drink() {
    }

    public Drink(Long id, String name, String description, Double openPrice,
                 Double priceRightNow, Double closedPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        OpenPrice = openPrice;
        PriceRightNow = priceRightNow;
        ClosedPrice = closedPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Description is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is mandatory") String description) {
        this.description = description;
    }

    public Double getOpenPrice() {
        return OpenPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.OpenPrice = openPrice;
    }

    public Double getPriceRightNow() {
        return PriceRightNow;
    }

    public void setPriceRightNow(Double priceRightNow) {
        PriceRightNow = priceRightNow;
    }

    public Double getClosedPrice() {
        return ClosedPrice;
    }

    public void setClosedPrice(Double closedPrice) {
        ClosedPrice = closedPrice;
    }
}
