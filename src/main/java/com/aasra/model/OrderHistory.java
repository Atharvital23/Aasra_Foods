package com.aasra.model;

public class OrderHistory {
    
    private int orderHistoryId;
    private int orderId;
    private int userId;
    private Integer assignedAgentId; // Can be null
    
    // Constructors
    public OrderHistory() {}
    
    public OrderHistory(int orderId, int userId, Integer assignedAgentId) {
        this.orderId = orderId;
        this.userId = userId;
        this.assignedAgentId = assignedAgentId;
    }
    
    // Getters and Setters
    public int getOrderHistoryId() {
        return orderHistoryId;
    }
    
    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Integer getAssignedAgentId() {
        return assignedAgentId;
    }
    
    public void setAssignedAgentId(Integer assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    @Override
    public String toString() {
        return "OrderHistory [orderHistoryId=" + orderHistoryId + ", orderId=" + orderId + 
               ", userId=" + userId + ", assignedAgentId=" + assignedAgentId + "]";
    }
}
