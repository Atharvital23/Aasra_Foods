<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.aasra.model.Cart,com.aasra.model.CartItem,com.aasra.model.User,java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Shopping Cart - Aasra Foods</title>
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

/* Cart Layout */
.cart-layout {
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 30px;
}

/* Cart Items */
.cart-items {
    background: white;
    border-radius: 15px;
    padding: 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.cart-item {
    display: flex;
    gap: 20px;
    padding: 20px 0;
    border-bottom: 1px solid #f0f0f0;
}

.cart-item:last-child {
    border-bottom: none;
}

.item-image {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 10px;
}

.item-details {
    flex: 1;
}

.item-name {
    font-size: 20px;
    font-weight: 700;
    color: #2d5016;
    margin-bottom: 5px;
}

.item-restaurant {
    font-size: 14px;
    color: #666;
    margin-bottom: 10px;
}

.item-price {
    font-size: 18px;
    font-weight: 700;
    color: #4caf50;
}

.item-actions {
    display: flex;
    flex-direction: column;
    gap: 10px;
    justify-content: center;
}

.quantity-control {
    display: flex;
    gap: 10px;
    align-items: center;
}

.quantity-input {
    width: 60px;
    padding: 8px;
    border: 2px solid #4caf50;
    border-radius: 8px;
    text-align: center;
    font-size: 16px;
    font-weight: 600;
}

.btn-update {
    padding: 8px 15px;
    background: #4caf50;
    color: white;
    border: none;
    border-radius: 8px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
}

.btn-update:hover {
    background: #2d5016;
}

.btn-remove {
    padding: 8px 15px;
    background: #f44336;
    color: white;
    border: none;
    border-radius: 8px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
}

.btn-remove:hover {
    background: #c62828;
}

/* Cart Summary */
.cart-summary {
    background: white;
    border-radius: 15px;
    padding: 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    height: fit-content;
    position: sticky;
    top: 100px;
}

.summary-title {
    font-size: 24px;
    font-weight: 700;
    color: #2d5016;
    margin-bottom: 20px;
    border-bottom: 2px solid #4caf50;
    padding-bottom: 10px;
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

.btn-checkout {
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

.btn-checkout:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(76, 175, 80, 0.3);
}

.btn-continue {
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
}

.btn-continue:hover {
    background: #4caf50;
    color: white;
}

/* Empty Cart */
.empty-cart {
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

@keyframes slideDown {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.alert-success {
    background: #4caf50;
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
    .cart-layout {
        grid-template-columns: 1fr;
    }
    
    .cart-item {
        flex-direction: column;
    }
    
    .cart-summary {
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
        <li><a href="orderHistory">My Orders</a></li>
        
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
    <h1 class="page-title">🛒 Your Shopping Cart</h1>
    
    <%
    String success = request.getParameter("success");
    
    if ("updated".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Cart updated successfully!
    </div>
    <%
    } else if ("removed".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Item removed from cart!
    </div>
    <%
    } else if ("cleared".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Cart cleared successfully!
    </div>
    <%
    }
    %>
    
    <%
    com.aasra.model.Cart cart = (com.aasra.model.Cart) session.getAttribute("cart");
    
    if (cart != null && !cart.isEmpty()) {
    %>
    
    <div class="cart-layout">
        <!-- Cart Items -->
        <div class="cart-items">
            <%
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
            %>
            
            <div class="cart-item">
                <img src="<%= item.getImagePath() != null ? item.getImagePath() : "https://via.placeholder.com/100" %>" 
                     alt="<%= item.getItemName() %>" class="item-image" />
                
                <div class="item-details">
                    <h3 class="item-name"><%= item.getItemName() %></h3>
                    <p class="item-restaurant">🏪 <%= item.getRestaurantName() != null ? item.getRestaurantName() : "Restaurant" %></p>
                    <p class="item-price">₹<%= String.format("%.2f", item.getPrice()) %> × <%= item.getQuantity() %> = 
                        <b>₹<%= String.format("%.2f", item.getSubTotal()) %></b>
                    </p>
                </div>
                
                <div class="item-actions">
                    <form action="cart" method="post" class="quantity-control">
                        <input type="hidden" name="action" value="update" />
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>" />
                        <input type="number" name="quantity" value="<%= item.getQuantity() %>" 
                               min="1" max="10" class="quantity-input" />
                        <button type="submit" class="btn-update">Update</button>
                    </form>
                    
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="remove" />
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>" />
                        <button type="submit" class="btn-remove">🗑️ Remove</button>
                    </form>
                </div>
            </div>
            
            <%
            }
            %>
        </div>
        
        <!-- Cart Summary -->
        <div class="cart-summary">
            <h2 class="summary-title">Order Summary</h2>
            
            <div class="summary-row">
                <span>Items (<%= cart.getItemCount() %>)</span>
                <span>₹<%= String.format("%.2f", cart.getTotalAmount()) %></span>
            </div>
            
            <div class="summary-row">
                <span>Delivery Fee</span>
                <span>FREE</span>
            </div>
            
            <div class="summary-row summary-total">
                <span>Total</span>
                <span>₹<%= String.format("%.2f", cart.getTotalAmount()) %></span>
            </div>
            
            <form action="checkout" method="get">
                <button type="submit" class="btn-checkout">🚀 Proceed to Checkout</button>
            </form>
            
            <a href="home">
                <button type="button" class="btn-continue">← Continue Shopping</button>
            </a>
            
            <form action="cart" method="post" style="margin-top: 20px;">
                <input type="hidden" name="action" value="clear" />
                <button type="submit" class="btn-remove" style="width: 100%;">
                    🗑️ Clear Cart
                </button>
            </form>
        </div>
    </div>
    
    <%
    } else {
    %>
    
    <!-- Empty Cart -->
    <div class="empty-cart">
        <div class="empty-icon">🛒</div>
        <p class="empty-message">Your cart is empty!</p>
        <a href="home">
            <button type="button" class="btn-checkout">🍽️ Browse Restaurants</button>
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
