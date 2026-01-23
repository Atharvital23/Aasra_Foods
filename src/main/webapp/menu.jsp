<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.aasra.model.Menu,com.aasra.model.Restaurant,com.aasra.model.User,com.aasra.model.Cart"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Menu - Aasra Foods</title>
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
    box-shadow: 0 4px 6px rgba(46, 125, 50, 0.2);
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

/* Restaurant Header */
.restaurant-header {
    background: white;
    padding: 30px;
    margin: 20px auto;
    max-width: 1200px;
    border-radius: 15px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    display: flex;
    gap: 30px;
    align-items: center;
}

.restaurant-image {
    width: 200px;
    height: 200px;
    object-fit: cover;
    border-radius: 15px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.restaurant-info {
    flex: 1;
}

.restaurant-name {
    font-size: 36px;
    font-weight: 800;
    color: #2d5016;
    margin-bottom: 10px;
}

.restaurant-cuisine {
    font-size: 18px;
    color: #666;
    margin-bottom: 15px;
}

.restaurant-meta {
    display: flex;
    gap: 20px;
    align-items: center;
}

.rating-badge {
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
    padding: 8px 15px;
    border-radius: 10px;
    font-size: 16px;
    font-weight: 700;
}

.eta-badge {
    background: #f5f5f5;
    color: #2d5016;
    padding: 8px 15px;
    border-radius: 10px;
    font-weight: 600;
}

/* Container */
.container {
    padding: 20px 4%;
    max-width: 1400px;
    margin: 0 auto;
}

.section-title {
    font-size: 28px;
    margin-bottom: 30px;
    color: #2d5016;
    font-weight: 700;
    border-bottom: 3px solid #4caf50;
    padding-bottom: 10px;
    display: inline-block;
}

/* Menu Grid */
.menu-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 25px;
}

/* Menu Card */
.menu-card {
    background: white;
    border-radius: 15px;
    overflow: hidden;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    transition: all 0.3s ease;
    position: relative;
}

.menu-card:hover {
    transform: translateY(-8px);
    box-shadow: 0 12px 30px rgba(76, 175, 80, 0.2);
}

.menu-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.menu-body {
    padding: 20px;
}

.menu-name {
    font-size: 20px;
    font-weight: 700;
    color: #2d5016;
    margin-bottom: 8px;
}

.menu-description {
    font-size: 14px;
    color: #666;
    margin-bottom: 15px;
    line-height: 1.5;
}

.menu-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-top: 15px;
    border-top: 1px solid #f0f0f0;
}

.menu-price {
    font-size: 24px;
    font-weight: 800;
    color: #4caf50;
}

.menu-rating {
    background: #f5f5f5;
    padding: 5px 10px;
    border-radius: 8px;
    font-size: 14px;
    color: #2d5016;
    font-weight: 600;
}

/* Add to Cart Section */
.cart-section {
    margin-top: 15px;
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

.btn-add-cart {
    flex: 1;
    padding: 10px;
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
    border: none;
    border-radius: 8px;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.3s ease;
}

.btn-add-cart:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 15px rgba(76, 175, 80, 0.3);
}

.btn-add-cart:active {
    transform: translateY(0);
}

.unavailable {
    opacity: 0.6;
    pointer-events: none;
}

.unavailable-badge {
    position: absolute;
    top: 10px;
    right: 10px;
    background: #f44336;
    color: white;
    padding: 5px 15px;
    border-radius: 20px;
    font-size: 12px;
    font-weight: 700;
}

/* Alert Messages */
.alert {
    max-width: 1200px;
    margin: 20px auto;
    padding: 15px 25px;
    border-radius: 10px;
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
    box-shadow: 0 4px 15px rgba(76, 175, 80, 0.3);
}

.alert-error {
    background: #f44336;
    color: white;
    box-shadow: 0 4px 15px rgba(244, 67, 54, 0.3);
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
    .restaurant-header {
        flex-direction: column;
        text-align: center;
    }
    
    .restaurant-name {
        font-size: 28px;
    }
    
    .menu-grid {
        grid-template-columns: 1fr;
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

<!-- Success/Error Messages -->
<%
String success = request.getParameter("success");
String error = request.getParameter("error");

if ("added".equals(success)) {
%>
<div class="alert alert-success">
    ✅ Item added to cart successfully!
</div>
<%
} else if ("unavailable".equals(error)) {
%>
<div class="alert alert-error">
    ❌ This item is currently unavailable.
</div>
<%
}
%>

<!-- Restaurant Header -->
<%
Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");

if (restaurant != null) {
%>
<div class="restaurant-header">
    <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>" class="restaurant-image" />
    
    <div class="restaurant-info">
        <h1 class="restaurant-name"><%= restaurant.getName() %></h1>
        <p class="restaurant-cuisine"><%= restaurant.getCuisineType() %></p>
        
        <div class="restaurant-meta">
            <span class="rating-badge">⭐ <%= restaurant.getRatings() %></span>
            <span class="eta-badge">🕐 <%= restaurant.getEta() %> mins</span>
        </div>
    </div>
</div>
<%
}
%>

<!-- Menu Items -->
<div class="container">
    <h2 class="section-title">Our Menu</h2>
    
    <div class="menu-grid">
        <%
        List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
        
        if (menuList != null && !menuList.isEmpty()) {
            for (Menu menu : menuList) {
        %>
        
        <div class="menu-card <%= !menu.isAvailable() ? "unavailable" : "" %>">
            <% if (!menu.isAvailable()) { %>
            <span class="unavailable-badge">Not Available</span>
            <% } %>
            
            <img src="<%= menu.getImagePath() %>" alt="<%= menu.getName() %>" class="menu-image" />
            
            <div class="menu-body">
                <h3 class="menu-name"><%= menu.getName() %></h3>
                <p class="menu-description"><%= menu.getDescription() %></p>
                
                <div class="menu-footer">
                    <span class="menu-price">₹<%= String.format("%.2f", menu.getPrice()) %></span>
                    <span class="menu-rating">⭐ <%= menu.getRatings() %></span>
                </div>
                
                <% if (menu.isAvailable()) { %>
                <form action="cart" method="post" class="cart-section">
                    <input type="hidden" name="action" value="add" />
                    <input type="hidden" name="menuId" value="<%= menu.getMenuId() %>" />
                    <input type="hidden" name="restaurantId" value="<%= menu.getRestaurantId() %>" />
                    
                    <input type="number" name="quantity" value="1" min="1" max="10" class="quantity-input" />
                    
                    <button type="submit" class="btn-add-cart">🛒 Add to Cart</button>
                </form>
                <% } %>
            </div>
        </div>
        
        <%
            }
        } else {
        %>
        <p style="grid-column: 1/-1; text-align: center; color: #666; font-size: 18px;">
            No menu items available at the moment.
        </p>
        <%
        }
        %>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2025 Aasra Foods. All rights reserved.</p>
    <p>Delivering happiness, one meal at a time.</p>
</div>

</body>
</html>
