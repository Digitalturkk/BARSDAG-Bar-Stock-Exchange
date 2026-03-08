package org.DigitalDucks.BARSDAG.Orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.DigitalDucks.BARSDAG.Orders.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Fetching all orders");
        List<OrderDTO> orders = orderService.getAllOrders().stream()
                .map(orderService::convertToDTO)
                .toList();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        log.info("Fetching order with ID: {}", orderId);
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderService.convertToDTO(order));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("Creating new order with number: {}", orderDTO.getOrderNumber());
        Order order = new Order();
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrdersDrinks(orderDTO.getOrdersDrinks());
        orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.convertToDTO(order));
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'MANAGER')")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        log.info("Deleting order with ID: {}", orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}


