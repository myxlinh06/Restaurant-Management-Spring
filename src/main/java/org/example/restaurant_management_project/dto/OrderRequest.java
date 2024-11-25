package org.example.restaurant_management_project.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderRequest {

    private String tableNumber;
    private List<OrderItemRequest> items;
    private String specialInstructions;

    public OrderRequest() {
    }

    public OrderRequest(String tableNumber, List<OrderItemRequest> items, String specialInstructions) {
        this.tableNumber = tableNumber;
        this.items = items;
        this.specialInstructions = specialInstructions;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    // Phương thức validate
    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        if (tableNumber == null || tableNumber.trim().isEmpty()) {
            errors.add("Table number is required.");
        }
        if (items == null || items.isEmpty()) {
            errors.add("At least one item is required.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                List<String> itemErrors = items.get(i).validate();
                if (!itemErrors.isEmpty()) {
                    errors.add("Item " + (i + 1) + ": " + String.join(", ", itemErrors));
                }
            }
        }
        return errors;
    }

    public static class OrderItemRequest {
        private Long menuItemId;
        private Integer quantity;

        public OrderItemRequest() {
        }

        public OrderItemRequest(Long menuItemId, Integer quantity) {
            this.menuItemId = menuItemId;
            this.quantity = quantity;
        }

        public Long getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(Long menuItemId) {
            this.menuItemId = menuItemId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        // Phương thức validate
        public List<String> validate() {
            List<String> errors = new ArrayList<>();
            if (menuItemId == null) {
                errors.add("Menu item ID is required.");
            }
            if (quantity == null || quantity <= 0) {
                errors.add("Quantity is required and must be greater than 0.");
            }
            return errors;
        }
    }
}
