package org.example.restaurant_management_project.service;

import org.example.restaurant_management_project.model.MenuItem;
import org.example.restaurant_management_project.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuItemRepository.findByCategory(category);
    }
}
