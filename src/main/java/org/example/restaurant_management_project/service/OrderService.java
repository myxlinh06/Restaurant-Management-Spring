package org.example.restaurant_management_project.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.example.restaurant_management_project.dto.*;
import org.example.restaurant_management_project.model.*;
import org.example.restaurant_management_project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    // Phương thức getOrder
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }
    
    // Phương thức updateOrder
    @Transactional
    public Order updateOrder(Long id, OrderRequest orderRequest) {
        Order existingOrder = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        
        // Cập nhật thông tin cơ bản
        existingOrder.setTableNumber(orderRequest.getTableNumber());
        
        // Xóa các item cũ
        existingOrder.getItems().clear();
        
        // Tạo danh sách item mới
        List<OrderItem> orderItems = orderRequest.getItems().stream()
            .map(item -> {
                OrderItem orderItem = new OrderItem();
                MenuItem menuItem = menuItemRepository.findById(item.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));
                orderItem.setMenuItem(menuItem);
                orderItem.setQuantity(item.getQuantity());
                orderItem.setUnitPrice(menuItem.getPrice());
                orderItem.setOrder(existingOrder);
                return orderItem;
            })
            .collect(Collectors.toList());
        
        // Cập nhật items và tổng tiền
        existingOrder.setItems(orderItems);
        existingOrder.setTotalAmount(calculateTotal(orderItems));
        
        return orderRepository.save(existingOrder);
    }
    
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setTableNumber(orderRequest.getTableNumber());
        order.setStatus(OrderStatus.PENDING);
        
        List<OrderItem> orderItems = orderRequest.getItems().stream()
            .map(item -> {
                OrderItem orderItem = new OrderItem();
                MenuItem menuItem = menuItemRepository.findById(item.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found")); 
                orderItem.setMenuItem(menuItem);
                orderItem.setQuantity(item.getQuantity());
                orderItem.setUnitPrice(menuItem.getPrice());
                orderItem.setOrder(order);
                return orderItem;
            })
            .collect(Collectors.toList());
            
        order.setItems(orderItems);
        order.setTotalAmount(calculateTotal(orderItems));
        
        return orderRepository.save(order);
    }
    
    private BigDecimal calculateTotal(List<OrderItem> items) {
        return items.stream()
            .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}