package com.orders.demo.controller;

import com.orders.demo.dto.OrderRequest;
import com.orders.demo.model.Order;
import com.orders.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = {"application/json", "application/xml"})
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/control-number/{controlNumber}")
    public ResponseEntity<List<Order>> getOrderByControlNumber(@PathVariable Long controlNumber) {
        List<Order> orders = orderService.getOrdersByControlNumber(controlNumber);
        return ResponseEntity.ok(orders);
    }
}
