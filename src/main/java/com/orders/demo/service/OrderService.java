package com.orders.demo.service;

import com.orders.demo.dto.OrderRequest;
import com.orders.demo.model.Order;
import com.orders.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest) {
        if (orderRepository.findByControlNumber(orderRequest.getControlNumber()).isPresent()) {
            throw new IllegalArgumentException("Control number already exists.");
        }

        Order order = new Order();
        order.setControlNumber(orderRequest.getControlNumber());
        order.setRegistrationDate(orderRequest.getRegistrationDate() != null ? orderRequest.getRegistrationDate() : LocalDate.now());
        order.setProductName(orderRequest.getProductName());
        order.setUnitPrice(orderRequest.getUnitPrice());
        order.setQuantity(orderRequest.getQuantity() != null ? orderRequest.getQuantity() : 1);
        order.setClientId(orderRequest.getClientId());

        int quantity = order.getQuantity();
        double totalPrice = order.getUnitPrice() * quantity;
        if (quantity >= 10) {
            totalPrice *= 0.9;
        } else if (quantity > 5) {
            totalPrice *= 0.95;
        }
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByControlNumber(Long controlNumber) {
        return orderRepository.findByControlNumber(controlNumber)
                .map(List::of)
                .orElseThrow(() -> new IllegalArgumentException("Order not found."));
    }

    @Transactional
    public void deleteOrderByControlNumber(Long controlNumber) {
        orderRepository.deleteByControlNumber(controlNumber);
    }

}
