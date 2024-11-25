package org.example.restaurant_management_project.repository;

import java.util.List;

import org.example.restaurant_management_project.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(String category);
}
