package com.aasra.model;

public class Menu {
    
    private int menuId;
    private int restaurantId;
    private String name;
    private String description;
    private double price;
    private double ratings;
    private String imagePath;
    private boolean isAvailable;
    
    // Constructors
    public Menu() {}
    
    public Menu(int restaurantId, String name, String description, double price, 
                String imagePath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.isAvailable = true;
        this.ratings = 0.0;
    }
    
    // Getters and Setters
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", name=" + name + ", price=" + price + 
               ", restaurantId=" + restaurantId + "]";
    }
}
