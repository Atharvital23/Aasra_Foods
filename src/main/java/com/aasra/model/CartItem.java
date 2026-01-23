package com.aasra.model;

public class CartItem {
    
    private int cartItemId;
    private int userId;
    private int menuId;
    private int restaurantId;
    private int quantity;
    
    // Additional fields for display
    private String itemName;
    private double price;
    private String imagePath;
    private String restaurantName;
    
    // Constructors
    public CartItem() {}
    
    public CartItem(int menuId, int restaurantId, String itemName, double price, 
                    int quantity) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Calculate subtotal
    public double getSubTotal() {
        return price * quantity;
    }
    
    // Getters and Setters
    public int getCartItemId() {
        return cartItemId;
    }
    
    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getMenuId() {
        return menuId;
    }
    
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    
    public int getRestaurantId() {
        return restaurantId;
    }
    
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getRestaurantName() {
        return restaurantName;
    }
    
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "CartItem [menuId=" + menuId + ", itemName=" + itemName + 
               ", quantity=" + quantity + ", price=" + price + "]";
    }
}
