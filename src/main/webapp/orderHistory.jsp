<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.aasra.model.Orders,com.aasra.model.User,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Order History - Aasra Foods</title>
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
    position: sticky;
    top: 0;
    z-index: 1000;
}

.logo {
    font-size: 26px;
    font-weight: 800;
    color: #90ee90;
    text-decoration: none;
    letter-spacing: 1px;
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
    transition: color 0.3s ease;
}

.nav-links a:hover {
    color: #90ee90;
}

.cart-btn {
    background-color: #4caf50;
    color: white !important;
    padding: 10px 20px;
    border-radius: 25px;
    transition: all 0.3s ease;
}

.cart-btn:hover {
    background-color: #2d5016;
    transform: translateY(-2px);
}

.auth-btn {
    background-color: transparent;
    color: white;
    border: 2px solid white;
    padding: 8px 16px;
    border-radius: 20px;
    cursor: pointer;
    font-weight: 600;
    transition: all 0.3s ease;
}

.auth-btn:hover {
    background-color: white;
    color: #2d5016 !important;
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

/* Order Card */
.order-card {
    background: white;
    border-radius: 15px;
    padding: 25px;
    margin-bottom: 20px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
}

.order-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 25px rgba(76, 175, 80, 0.2);
}

.order-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
    padding-bottom: 15px;
    border-bottom: 2px solid #f0f0f0;
}

.order-id {
    font-size: 20px;
    font-weight: 700;
    color: #2d5016;
}

.order-status {
    padding: 8px 20px;
    border-radius: 20px;
    font-weight: 700;
    font-size: 14px;
}

.status-CONFIRMED {
    background: #2196f3;
    color: white;
}

.status-DISPATCHED {
    background: #ff9800;
    color: white;
}

.status-DELIVERED {
    background: #4caf50;
    color: white;
}

.status-CANCELLED {
    background: #f44336;
    color: white;
}

.order-details {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 15px;
    margin-bottom: 20px;
}

.detail-item {
    display: flex;
    flex-direction: column;
    gap: 5px;
}

.detail-label {
    font-size: 13px;
    color: #999;
    font-weight: 600;
}

.detail-value {
    font-size: 16px;
    color: #2d5016;
    font-weight: 600;
}

.order-actions {
    display: flex;
    gap: 10px;
    justify-content: flex-end;
}

.btn {
    padding: 10px 20px;
    border: none;
    border-radius: 8px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
    text-decoration: none;
    display: inline-block;
}

.btn-view {
    background: #4caf50;
    color: white;
}

.btn-view:hover {
    background: #2d5016;
}

.btn-cancel {
    background: #f44336;
    color: white;
}

.btn-cancel:hover {
    background: #c62828;
}

.btn-rate {
    background: #ff9800;
    color: white;
}

.btn-rate:hover {
    background: #f57c00;
}

/* Empty State */
.empty-state {
    background: white;
    border-radius: 15px;
    padding: 60px 30px;
    text-align: center;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.empty-icon {
    font-size: 80px;
    margin-bottom: 20px;
}

.empty-message {
    font-size: 24px;
    color: #666;
    margin-bottom: 30px;
}

/* Alert */
.alert {
    padding: 15px 25px;
    border-radius: 10px;
    margin-bottom: 20px;
    animation: slideDown 0.5s ease-out;
}

.alert-success {
    background: #4caf50;
    color: white;
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

@media (max-width: 768px) {
    .order-header {
        flex-direction: column;
        gap: 10px;
    }
    
    .order-actions {
        justify-content: center;
        flex-wrap: wrap;
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
        <li><a href="orderHistory">My Orders</a></li>
        
        <%
        com.aasra.model.Cart cart = (com.aasra.model.Cart) session.getAttribute("cart");
        int cartCount = (cart != null) ? cart.getItems().size() : 0;
        %>
        
        <li><a href="cart.jsp" class="cart-btn">🛒 Cart (<%= cartCount %>)</a></li>
        
        <%
        com.aasra.model.User currentUser = (com.aasra.model.User) session.getAttribute("loggedInUser");
        
        if (currentUser != null) {
        %>
        <li><a href="profile.jsp" style="color: #fff; cursor: default; font-weight: 400;">
            Hi, <b><%= currentUser.getName() %></b>
        </a></li>
        <li><a href="logout" class="auth-btn" style="border-color: #ef5350; color: #ef5350;">Logout</a></li>
        <%
        } else {
        %>
        <li><a href="Login.jsp" class="auth-btn">Login</a></li>
        <%
        }
        %>
    </ul>
</div>

<!-- Main Container -->
<div class="container">
    <h1 class="page-title">📦 My Orders</h1>
    
    <%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
    
    if ("rated".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Thank you for your rating!
    </div>
    <%
    } else if ("cancelled".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Order cancelled successfully!
    </div>
    <%
    } else if ("cannotCancel".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Cannot cancel this order. It may have already been dispatched.
    </div>
    <%
    }
    %>
    
    <%
    List<Orders> orderList = (List<Orders>) request.getAttribute("orderList");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
    
    if (orderList != null && !orderList.isEmpty()) {
        for (Orders order : orderList) {
    %>
    
    <div class="order-card">
        <div class="order-header">
            <div class="order-id">Order #<%= order.getOrderId() %></div>
            <div class="order-status status-<%= order.getStatus() %>">
                <%= order.getStatus() %>
            </div>
        </div>
        
        <div class="order-details">
            <div class="detail-item">
                <span class="detail-label">Restaurant</span>
                <span class="detail-value">
                    🏪 <%= order.getRestaurantName() != null ? order.getRestaurantName() : "N/A" %>
                </span>
            </div>
            
            <div class="detail-item">
                <span class="detail-label">Order Date</span>
                <span class="detail-value">
                    📅 <%= order.getOrderDate() != null ? dateFormat.format(order.getOrderDate()) : "N/A" %>
                </span>
            </div>
            
            <div class="detail-item">
                <span class="detail-label">Total Amount</span>
                <span class="detail-value" style="color: #4caf50;">
                    ₹<%= String.format("%.2f", order.getTotalAmount()) %>
                </span>
            </div>
            
            <div class="detail-item">
                <span class="detail-label">Payment Mode</span>
                <span class="detail-value">
                    💳 <%= order.getModeOfPayment() %>
                </span>
            </div>
            
            <% if (order.getDeliveryAgentName() != null) { %>
            <div class="detail-item">
                <span class="detail-label">Delivery Agent</span>
                <span class="detail-value">
                    🛵 <%= order.getDeliveryAgentName() %>
                </span>
            </div>
            <% } %>
        </div>
        
        <div class="order-actions">
            <% if (order.getStatus() == Orders.OrderStatus.CONFIRMED) { %>
            <form action="cancelOrder" method="post" style="display: inline;">
                <input type="hidden" name="orderId" value="<%= order.getOrderId() %>" />
                <button type="submit" class="btn btn-cancel" 
                        onclick="return confirm('Are you sure you want to cancel this order?');">
                    ❌ Cancel Order
                </button>
            </form>
            <% } %>
            
            <% if (order.getStatus() == Orders.OrderStatus.DELIVERED) { %>
            <a href="#" class="btn btn-rate" 
               onclick="rateOrder(<%= order.getOrderId() %>, <%= order.getRestaurantId() %>)">
                ⭐ Rate Order
            </a>
            <% } %>
        </div>
    </div>
    
    <%
        }
    } else {
    %>
    
    <div class="empty-state">
        <div class="empty-icon">📦</div>
        <p class="empty-message">No orders yet!</p>
        <a href="home">
            <button class="btn btn-view" style="padding: 15px 30px; font-size: 16px;">
                🍽️ Start Ordering
            </button>
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

<script>
function rateOrder(orderId, restaurantId) {
    const rating = prompt("Rate this order (1-5 stars):");
    
    if (rating) {
        const ratingValue = parseFloat(rating);
        
        if (ratingValue >= 1 && ratingValue <= 5) {
            const comments = prompt("Add your comments (optional):");
            
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'rating';
            
            const orderIdInput = document.createElement('input');
            orderIdInput.type = 'hidden';
            orderIdInput.name = 'orderId';
            orderIdInput.value = orderId;
            form.appendChild(orderIdInput);
            
            const restaurantIdInput = document.createElement('input');
            restaurantIdInput.type = 'hidden';
            restaurantIdInput.name = 'restaurantId';
            restaurantIdInput.value = restaurantId;
            form.appendChild(restaurantIdInput);
            
            const ratingInput = document.createElement('input');
            ratingInput.type = 'hidden';
            ratingInput.name = 'rating';
            ratingInput.value = ratingValue;
            form.appendChild(ratingInput);
            
            if (comments) {
                const commentsInput = document.createElement('input');
                commentsInput.type = 'hidden';
                commentsInput.name = 'comments';
                commentsInput.value = comments;
                form.appendChild(commentsInput);
            }
            
            document.body.appendChild(form);
            form.submit();
        } else {
            alert('Please enter a rating between 1 and 5!');
        }
    }
}
</script>

</body>
</html>
