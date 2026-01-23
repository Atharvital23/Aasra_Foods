package com.aasra.model;

import java.sql.Timestamp;

public class Orders {
    
    public enum PaymentMode {
        UPI, CASH, CREDIT_CARD, DEBIT_CARD
    }
    
    public enum OrderStatus {
        CONFIRMED, DISPATCHED, DELIVERED, CANCELLED
    }
    
    private int orderId;
    private int restaurantId;
    private int userId;
    private double totalAmount;
    private PaymentMode modeOfPayment;
    private OrderStatus status;
    private Timestamp orderDate;
    private Integer deliveryAgentId; // Can be null
    
    // Additional display fields
    private String restaurantName;
    private String customerName;
    private String deliveryAgentName;
    
    // Constructors
    public Orders() {
        this.status = OrderStatus.CONFIRMED;
    }
    
    public Orders(int restaurantId, int userId, double totalAmount, PaymentMode modeOfPayment) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.modeOfPayment = modeOfPayment;
        this.status = OrderStatus.CONFIRMED;
    }
    
    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getRestaurantId() {
        return restaurantId;
    }
    
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public PaymentMode getModeOfPayment() {
        return modeOfPayment;
    }
    
    public void setModeOfPayment(PaymentMode modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    public Timestamp getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    
    public Integer getDeliveryAgentId() {
        return deliveryAgentId;
    }
    
    public void setDeliveryAgentId(Integer deliveryAgentId) {
        this.deliveryAgentId = deliveryAgentId;
    }
    
    public String getRestaurantName() {
        return restaurantName;
    }
    
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getDeliveryAgentName() {
        return deliveryAgentName;
    }
    
    public void setDeliveryAgentName(String deliveryAgentName) {
        this.deliveryAgentName = deliveryAgentName;
    }

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", restaurantId=" + restaurantId + 
               ", userId=" + userId + ", totalAmount=" + totalAmount + 
               ", status=" + status + "]";
    }
}
