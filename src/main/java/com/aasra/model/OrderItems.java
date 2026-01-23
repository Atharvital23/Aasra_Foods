package com.aasra.model;

public class OrderItems {
    
    private int orderItemsId;
    private int orderId;
    private int menuId;
    private int quantity;
    private double price;
    private double ratings;
    private String comments;
    
    // Additional display fields
    private String itemName;
    private String imagePath;
    
    // Constructors
    public OrderItems() {}
    
    public OrderItems(int orderId, int menuId, int quantity, double price) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.price = price;
    }
    
    // Calculate subtotal
    public double getSubTotal() {
        return price * quantity;
    }
    
    // Getters and Setters
    public int getOrderItemsId() {
        return orderItemsId;
    }
    
    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getMenuId() {
        return menuId;
    }
    
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public double getRatings() {
        return ratings;
    }
    
    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "OrderItems [orderItemsId=" + orderItemsId + ", orderId=" + orderId + 
               ", menuId=" + menuId + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
