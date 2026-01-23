package com.aasra.model;

public class Restaurant {
    
    private int restaurantId;
    private String name;
    private String imagePath;
    private double ratings;
    private int eta; // Estimated time in minutes
    private String cuisineType;
    private String address;
    private boolean isActive;
    private int restaurantOwnerId;
    
    // Constructors
    public Restaurant() {}
    
    public Restaurant(String name, String imagePath, double ratings, int eta, 
                      String cuisineType, String address, int restaurantOwnerId) {
        this.name = name;
        this.imagePath = imagePath;
        this.ratings = ratings;
        this.eta = eta;
        this.cuisineType = cuisineType;
        this.address = address;
        this.restaurantOwnerId = restaurantOwnerId;
        this.isActive = true;
    }
    
    // Getters and Setters
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
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public double getRatings() {
        return ratings;
    }
    
    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
    
    public int getEta() {
        return eta;
    }
    
    public void setEta(int eta) {
        this.eta = eta;
    }
    
    public String getCuisineType() {
        return cuisineType;
    }
    
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public int getRestaurantOwnerId() {
        return restaurantOwnerId;
    }
    
    public void setRestaurantOwnerId(int restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + 
               ", cuisineType=" + cuisineType + ", ratings=" + ratings + "]";
    }
}
