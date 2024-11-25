package org.example.restaurant_management_project.repository;

import org.example.restaurant_management_project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    // Tìm tất cả đơn hàng theo trạng thái
    List<Order> findByStatus(String status);
    
    // Tìm đơn hàng theo số bàn
    List<Order> findByTableNumber(String tableNumber);
    
    // Tìm đơn hàng theo số bàn và trạng thái
    List<Order> findByTableNumberAndStatus(String tableNumber, String status);
    
    // Tìm đơn hàng trong một khoảng thời gian
    List<Order> findByCreatedAtBetween(java.util.Date startDate, java.util.Date endDate);
    
    // Đếm số đơn hàng theo trạng thái
    long countByStatus(String status);
}