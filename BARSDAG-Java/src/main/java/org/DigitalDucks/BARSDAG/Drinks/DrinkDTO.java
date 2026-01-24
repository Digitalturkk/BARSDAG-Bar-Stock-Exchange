package org.DigitalDucks.BARSDAG.Drinks;

public class DrinkDTO {
    private Long id;
    private String name;
    private String description;
    private Double openPrice;
    private Double priceRightNow;
    private Double closedPrice;

    public DrinkDTO() {
    }

    public DrinkDTO(Long id, String name, String description, Double openPrice,
                    Double priceRightNow, Double closedPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.openPrice = openPrice;
        this.priceRightNow = priceRightNow;
        this.closedPrice = closedPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public void setPriceRightNow(Double priceRightNow) {
        this.priceRightNow = priceRightNow;
    }

    public void setClosedPrice(Double closedPrice) {
        this.closedPrice = closedPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public Double getPriceRightNow() {
        return priceRightNow;
    }

    public Double getClosedPrice() {
        return closedPrice;
    }
}
