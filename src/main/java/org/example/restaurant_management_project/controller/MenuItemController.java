package org.example.restaurant_management_project.controller;

import org.example.restaurant_management_project.model.MenuItem;
import org.example.restaurant_management_project.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByCategory(category);
        return ResponseEntity.ok(menuItems);
    }
}

