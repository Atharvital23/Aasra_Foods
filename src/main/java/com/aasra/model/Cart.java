package com.aasra.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    
    private Map<Integer, CartItem> items; // Key: menuId, Value: CartItem
    
    public Cart() {
        this.items = new HashMap<>();
    }
    
    // Add item to cart
    public void addItem(CartItem item) {
        int menuId = item.getMenuId();
        
        if (items.containsKey(menuId)) {
            // Item already exists, increase quantity
            CartItem existingItem = items.get(menuId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // New item
            items.put(menuId, item);
        }
    }
    
    // Remove item from cart
    public void removeItem(int menuId) {
        items.remove(menuId);
    }
    
    // Update quantity
    public void updateQuantity(int menuId, int quantity) {
        if (items.containsKey(menuId)) {
            if (quantity <= 0) {
                items.remove(menuId);
            } else {
                items.get(menuId).setQuantity(quantity);
            }
        }
    }
    
    // Get total amount
    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getSubTotal();
        }
        return total;
    }
    
    // Get item count
    public int getItemCount() {
        return items.size();
    }
    
    // Clear cart
    public void clear() {
        items.clear();
    }
    
    // Get all items
    public Map<Integer, CartItem> getItems() {
        return items;
    }
    
    // Check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
