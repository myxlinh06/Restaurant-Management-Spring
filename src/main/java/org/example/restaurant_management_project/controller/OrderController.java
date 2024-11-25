package org.example.restaurant_management_project.controller;

import org.example.restaurant_management_project.dto.OrderRequest;
import org.example.restaurant_management_project.model.Order;
import org.example.restaurant_management_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    @ResponseBody
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        Order order = orderService.updateOrder(id, orderRequest);
        return ResponseEntity.ok(order);
    }
}

