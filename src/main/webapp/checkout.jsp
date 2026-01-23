<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.aasra.model.Cart,com.aasra.model.CartItem,com.aasra.model.User,java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Checkout - Aasra Foods</title>
<style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%);
    margin: 0;
    padding: 0;
    color: #333;
    min-height: 100vh;
}

/* Navbar */
.navbar {
    background-color: #2d5016;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    padding: 1rem 2rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.logo {
    font-size: 26px;
    font-weight: 800;
    color: #90ee90;
    text-decoration: none;
}

.logo-text {
    color: #ffffff;
}

.nav-links {
    display: flex;
    list-style: none;
    gap: 2rem;
    margin: 0;
    align-items: center;
}

.nav-links a {
    text-decoration: none;
    color: #ffffff;
    font-weight: 600;
}

/* Container */
.container {
    padding: 40px 4%;
    max-width: 1200px;
    margin: 0 auto;
}

.page-title {
    font-size: 36px;
    font-weight: 800;
    color: #2d5016;
    margin-bottom: 30px;
    text-align: center;
}

.checkout-layout {
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 30px;
}

/* Checkout Form */
.checkout-form {
    background: white;
    border-radius: 15px;
    padding: 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.section-title {
    font-size: 22px;
    font-weight: 700;
    color: #2d5016;
    margin-bottom: 20px;
    border-bottom: 2px solid #4caf50;
    padding-bottom: 10px;
}

.form-group {
    margin-bottom: 25px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    color: #2d5016;
    font-weight: 600;
    font-size: 15px;
}

.form-group input,
.form-group textarea,
.form-group select {
    width: 100%;
    padding: 12px 15px;
    border: 2px solid #e0e0e0;
    border-radius: 10px;
    font-size: 15px;
    transition: all 0.3s ease;
    font-family: inherit;
}

.form-group input:focus,
.form-group textarea:focus,
.form-group select:focus {
    outline: none;
    border-color: #4caf50;
    box-shadow: 0 0 10px rgba(76, 175, 80, 0.2);
}

.form-group textarea {
    resize: vertical;
    min-height: 100px;
}

/* Payment Options */
.payment-options {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 15px;
}

.payment-option {
    position: relative;
}

.payment-option input[type="radio"] {
    position: absolute;
    opacity: 0;
}

.payment-label {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 15px;
    border: 2px solid #e0e0e0;
    border-radius: 10px;
    cursor: pointer;
    transition: all 0.3s ease;
    font-weight: 600;
}

.payment-option input[type="radio"]:checked + .payment-label {
    border-color: #4caf50;
    background: #f1f8f4;
}

.payment-label:hover {
    border-color: #4caf50;
}

/* Order Summary */
.order-summary {
    background: white;
    border-radius: 15px;
    padding: 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    height: fit-content;
    position: sticky;
    top: 100px;
}

.summary-title {
    font-size: 22px;
    font-weight: 700;
    color: #2d5016;
    margin-bottom: 20px;
    border-bottom: 2px solid #4caf50;
    padding-bottom: 10px;
}

.order-item {
    display: flex;
    justify-content: space-between;
    padding: 10px 0;
    border-bottom: 1px solid #f0f0f0;
}

.item-info {
    flex: 1;
}

.item-name {
    font-weight: 600;
    color: #2d5016;
}

.item-quantity {
    font-size: 14px;
    color: #666;
}

.item-price {
    font-weight: 700;
    color: #4caf50;
}

.summary-row {
    display: flex;
    justify-content: space-between;
    padding: 10px 0;
    font-size: 16px;
}

.summary-total {
    font-size: 24px;
    font-weight: 800;
    color: #4caf50;
    border-top: 2px solid #f0f0f0;
    padding-top: 15px;
    margin-top: 10px;
}

.btn-place-order {
    width: 100%;
    padding: 15px;
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
    border: none;
    border-radius: 10px;
    font-size: 18px;
    font-weight: 700;
    cursor: pointer;
    margin-top: 20px;
    transition: all 0.3s ease;
}

.btn-place-order:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(76, 175, 80, 0.3);
}

.btn-back {
    width: 100%;
    padding: 12px;
    background: white;
    color: #4caf50;
    border: 2px solid #4caf50;
    border-radius: 10px;
    font-size: 16px;
    font-weight: 700;
    cursor: pointer;
    margin-top: 10px;
    transition: all 0.3s ease;
    text-align: center;
    text-decoration: none;
    display: block;
}

.btn-back:hover {
    background: #4caf50;
    color: white;
}

/* Alert */
.alert {
    padding: 15px 25px;
    border-radius: 10px;
    margin-bottom: 20px;
    animation: slideDown 0.5s ease-out;
}

.alert-error {
    background: #f44336;
    color: white;
}

/* Footer */
.footer {
    background-color: #2d5016;
    color: white;
    text-align: center;
    padding: 2rem;
    margin-top: 3rem;
}

/* Responsive */
@media (max-width: 768px) {
    .checkout-layout {
        grid-template-columns: 1fr;
    }
    
    .payment-options {
        grid-template-columns: 1fr;
    }
    
    .order-summary {
        position: static;
    }
}
</style>
</head>
<body>

<!-- Navbar -->
<div class="navbar">
    <a href="home" class="logo">🌿 Aasra<span class="logo-text">Foods</span></a>
    <ul class="nav-links">
        <li><a href="home">Home</a></li>
        <li><a href="cart.jsp">Cart</a></li>
        <li><a href="orderHistory">My Orders</a></li>
    </ul>
</div>

<!-- Main Container -->
<div class="container">
    <h1 class="page-title">💳 Checkout</h1>
    
    <%
    String error = request.getParameter("error");
    
    if ("missingInfo".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Please fill in all required fields!
    </div>
    <%
    } else if ("invalidPayment".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Please select a valid payment method!
    </div>
    <%
    }
    
    com.aasra.model.Cart cart = (com.aasra.model.Cart) session.getAttribute("cart");
    com.aasra.model.User currentUser = (com.aasra.model.User) session.getAttribute("loggedInUser");
    
    if (cart != null && !cart.isEmpty() && currentUser != null) {
    %>
    
    <div class="checkout-layout">
        <!-- Checkout Form -->
        <div class="checkout-form">
            <form action="checkout" method="post">
                
                <!-- Delivery Details -->
                <h2 class="section-title">📍 Delivery Details</h2>
                
                <div class="form-group">
                    <label for="customerName">Full Name *</label>
                    <input type="text" id="customerName" name="customerName" 
                           value="<%= currentUser.getName() %>" readonly 
                           style="background: #f5f5f5;" />
                </div>
                
                <div class="form-group">
                    <label for="phoneNo">Phone Number *</label>
                    <input type="tel" id="phoneNo" name="phoneNo" 
                           value="<%= currentUser.getPhoneNo() %>" readonly 
                           style="background: #f5f5f5;" />
                </div>
                
                <div class="form-group">
                    <label for="deliveryAddress">Delivery Address *</label>
                    <textarea id="deliveryAddress" name="deliveryAddress" 
                              placeholder="Enter your complete delivery address" 
                              required><%= currentUser.getAddress() != null ? currentUser.getAddress() : "" %></textarea>
                </div>
                
                <!-- Payment Method -->
                <h2 class="section-title">💰 Payment Method</h2>
                
                <div class="payment-options">
                    <div class="payment-option">
                        <input type="radio" id="cash" name="paymentMode" value="CASH" checked />
                        <label for="cash" class="payment-label">
                            💵 Cash on Delivery
                        </label>
                    </div>
                    
                    <div class="payment-option">
                        <input type="radio" id="upi" name="paymentMode" value="UPI" />
                        <label for="upi" class="payment-label">
                            📱 UPI Payment
                        </label>
                    </div>
                    
                    <div class="payment-option">
                        <input type="radio" id="card" name="paymentMode" value="CREDIT_CARD" />
                        <label for="card" class="payment-label">
                            💳 Credit Card
                        </label>
                    </div>
                    
                    <div class="payment-option">
                        <input type="radio" id="debit" name="paymentMode" value="DEBIT_CARD" />
                        <label for="debit" class="payment-label">
                            💳 Debit Card
                        </label>
                    </div>
                </div>
                
                <button type="submit" class="btn-place-order" style="margin-top: 30px;">
                    🚀 Place Order
                </button>
            </form>
        </div>
        
        <!-- Order Summary -->
        <div class="order-summary">
            <h2 class="summary-title">📦 Order Summary</h2>
            
            <%
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
            %>
            
            <div class="order-item">
                <div class="item-info">
                    <div class="item-name"><%= item.getItemName() %></div>
                    <div class="item-quantity">Qty: <%= item.getQuantity() %> × ₹<%= String.format("%.2f", item.getPrice()) %></div>
                </div>
                <div class="item-price">₹<%= String.format("%.2f", item.getSubTotal()) %></div>
            </div>
            
            <%
            }
            %>
            
            <div class="summary-row" style="margin-top: 20px;">
                <span>Subtotal (<%= cart.getItemCount() %> items)</span>
                <span>₹<%= String.format("%.2f", cart.getTotalAmount()) %></span>
            </div>
            
            <div class="summary-row">
                <span>Delivery Fee</span>
                <span style="color: #4caf50; font-weight: 700;">FREE</span>
            </div>
            
            <div class="summary-row">
                <span>Taxes</span>
                <span>₹0.00</span>
            </div>
            
            <div class="summary-row summary-total">
                <span>Total Amount</span>
                <span>₹<%= String.format("%.2f", cart.getTotalAmount()) %></span>
            </div>
            
            <a href="cart.jsp" class="btn-back">← Back to Cart</a>
        </div>
    </div>
    
    <%
    } else {
    %>
    <div style="background: white; border-radius: 15px; padding: 60px; text-align: center;">
        <div style="font-size: 60px; margin-bottom: 20px;">🛒</div>
        <p style="font-size: 20px; color: #666;">Your cart is empty or you're not logged in!</p>
        <a href="home">
            <button class="btn-place-order" style="max-width: 300px; margin: 20px auto;">Browse Restaurants</button>
        </a>
    </div>
    <%
    }
    %>
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2025 Aasra Foods. All rights reserved.</p>
    <p>Delivering happiness, one meal at a time.</p>
</div>

</body>
</html>
