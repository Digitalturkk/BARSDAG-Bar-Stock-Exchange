package org.DigitalDucks.BARSDAG.Sales;

import java.time.LocalDateTime;

public class SaleDTO {
    private Long id;
    private Long drinkId;
    private Integer quantity;
    private LocalDateTime saleTime;

    public SaleDTO() {
    }

    public SaleDTO(Long id, Long drinkId, Integer quantity,
                   LocalDateTime saleTime) {
        this.id = id;
        this.drinkId = drinkId;
        this.quantity = quantity;
        this.saleTime = saleTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDrinkId(Long drinkId) {
        this.drinkId = drinkId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }

    public Long getDrinkId() {
        return drinkId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }
}
