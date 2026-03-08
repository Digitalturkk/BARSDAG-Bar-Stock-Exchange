package org.DigitalDucks.BARSDAG.Orders.Services;

import org.DigitalDucks.BARSDAG.Orders.Order;
import org.DigitalDucks.BARSDAG.Orders.OrderDTO;
import org.DigitalDucks.BARSDAG.Orders.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplement implements OrderService {
    final private OrderRepository orderRepository;

    public OrderServiceImplement(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setOrdersDrinks(order.getOrdersDrinks());
        return dto;
    }
}
