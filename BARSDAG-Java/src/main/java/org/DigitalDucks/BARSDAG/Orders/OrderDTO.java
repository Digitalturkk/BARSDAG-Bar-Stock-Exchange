package org.DigitalDucks.BARSDAG.Orders;

import org.DigitalDucks.BARSDAG.Drinks.Drink;

import java.util.List;

public class OrderDTO {
    private Long id;
    private String orderNumber;
    private List<Drink> ordersDrinks;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String orderNumber, List<Drink> ordersDrinks) {
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
