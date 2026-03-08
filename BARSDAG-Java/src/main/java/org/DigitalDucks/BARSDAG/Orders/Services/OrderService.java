package org.DigitalDucks.BARSDAG.Orders.Services;

import org.DigitalDucks.BARSDAG.Orders.Order;
import org.DigitalDucks.BARSDAG.Orders.OrderDTO;

import java.util.List;

public interface OrderService {
    // Create operations
    //void createOrder();
    void saveOrder(Order order);
    // Read operations
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    // Update operations
        // Not implemented yet
    // Delete operations
    void deleteOrder(Long id);

    // DTO conversion
    OrderDTO convertToDTO(Order order);
}
