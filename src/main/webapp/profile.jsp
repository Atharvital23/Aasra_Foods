<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.aasra.model.User,com.aasra.model.Cart"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>My Profile - Aasra Foods</title>
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #d4e8d4 0%, #c8e6c9 100%);
    margin: 0;
    padding: 0;
    color: #333;
    min-height: 100vh;
}

/* Navbar */
.navbar {
    background-color: #3d5a2c;
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
    border: 2px solid #ef5350;
    color: #ef5350;
    padding: 8px 16px;
    border-radius: 20px;
    cursor: pointer;
    font-weight: 600;
    transition: all 0.3s ease;
}

.auth-btn:hover {
    background-color: #ef5350;
    color: white !important;
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
    color: #2d4520;
    margin-bottom: 40px;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 15px;
}

.page-title-icon {
    font-size: 40px;
}

/* Profile Layout */
.profile-layout {
    display: grid;
    grid-template-columns: 350px 1fr;
    gap: 30px;
}

/* Profile Sidebar */
.profile-sidebar {
    background: white;
    border-radius: 15px;
    padding: 40px 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    text-align: center;
    height: fit-content;
}

.profile-avatar {
    width: 140px;
    height: 140px;
    background: linear-gradient(135deg, #4d7c3f 0%, #2d5016 100%);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 64px;
    font-weight: 700;
    color: white;
    margin: 0 auto 20px;
    box-shadow: 0 6px 20px rgba(76, 175, 80, 0.3);
}

.profile-name {
    font-size: 26px;
    font-weight: 700;
    color: #2d4520;
    margin-bottom: 5px;
}

.profile-username {
    font-size: 15px;
    color: #999;
    margin-bottom: 30px;
}

.profile-stats {
    display: grid;
    grid-template-columns: 1fr;
    gap: 15px;
    margin-top: 30px;
}

.stat-item {
    background: #f5f9f5;
    padding: 18px 20px;
    border-radius: 12px;
    border-left: 4px solid #4caf50;
    text-align: left;
}

.stat-label {
    font-size: 13px;
    color: #999;
    margin-bottom: 8px;
    font-weight: 500;
}

.stat-value {
    font-size: 20px;
    font-weight: 700;
    color: #2d4520;
}

/* Profile Content */
.profile-content {
    background: white;
    border-radius: 15px;
    padding: 35px 40px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
}

.section-title {
    font-size: 22px;
    font-weight: 700;
    color: #2d4520;
    margin-bottom: 25px;
    padding-bottom: 12px;
    border-bottom: 3px solid #4caf50;
    display: flex;
    align-items: center;
    gap: 10px;
}

.form-row {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
}

.form-group {
    margin-bottom: 22px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    color: #2d4520;
    font-weight: 600;
    font-size: 14px;
}

.form-group input,
.form-group textarea {
    width: 100%;
    padding: 12px 16px;
    border: 2px solid #e0e0e0;
    border-radius: 8px;
    font-size: 15px;
    transition: all 0.3s ease;
    font-family: inherit;
    background: white;
}

.form-group input:focus,
.form-group textarea:focus {
    outline: none;
    border-color: #4caf50;
    box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.1);
}

.form-group input:disabled {
    background: #f5f5f5;
    cursor: not-allowed;
    color: #999;
}

.form-group textarea {
    resize: vertical;
    min-height: 100px;
}

.btn-update {
    padding: 14px 35px;
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 16px;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.3s ease;
    box-shadow: 0 4px 12px rgba(76, 175, 80, 0.3);
}

.btn-update:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 20px rgba(76, 175, 80, 0.4);
}

.btn-update:active {
    transform: translateY(0);
}

/* Alert */
.alert {
    padding: 15px 25px;
    border-radius: 10px;
    margin-bottom: 25px;
    animation: slideDown 0.5s ease-out;
    font-weight: 500;
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

/* Security Section */
.security-section {
    margin-top: 40px;
    padding-top: 30px;
    border-top: 2px solid #f0f0f0;
}

.info-box {
    background: #f5f9f5;
    padding: 25px;
    border-radius: 12px;
    border-left: 4px solid #4caf50;
}

.info-box p {
    margin: 0;
    color: #555;
    line-height: 1.7;
}

.info-box b {
    color: #2d4520;
}

/* Footer */
.footer {
    background-color: #3d5a2c;
    color: white;
    text-align: center;
    padding: 2rem;
    margin-top: 4rem;
}

/* Responsive */
@media (max-width: 968px) {
    .profile-layout {
        grid-template-columns: 1fr;
    }
}

@media (max-width: 768px) {
    .navbar {
        flex-direction: column;
        gap: 1rem;
        padding: 1rem;
    }
    
    .nav-links {
        gap: 1rem;
        flex-wrap: wrap;
        justify-content: center;
        font-size: 14px;
    }
    
    .profile-layout {
        grid-template-columns: 1fr;
    }
    
    .form-row {
        grid-template-columns: 1fr;
    }
    
    .page-title {
        font-size: 28px;
    }
    
    .profile-content {
        padding: 25px 20px;
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
        <li><a href="logout" class="auth-btn">Logout</a></li>
        <%
        } else {
            response.sendRedirect("Login.jsp");
            return;
        }
        %>
    </ul>
</div>

<!-- Main Container -->
<div class="container">
    <h1 class="page-title">
        <span class="page-title-icon">👤</span>
        My Profile
    </h1>
    
    <%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
    
    if ("updated".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Profile updated successfully!
    </div>
    <%
    } else if ("updateFailed".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Failed to update profile. Please try again.
    </div>
    <%
    } else if ("missingFields".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Please fill in all required fields!
    </div>
    <%
    } else if ("invalidPhone".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Invalid phone number format!
    </div>
    <%
    }
    %>
    
    <div class="profile-layout">
        <!-- Profile Sidebar -->
        <div class="profile-sidebar">
            <div class="profile-avatar">
                <%= currentUser.getName().substring(0, 1).toUpperCase() %>
            </div>
            
            <div class="profile-name"><%= currentUser.getName() %></div>
            <div class="profile-username">@<%= currentUser.getUserName() %></div>
            
            <div class="profile-stats">
                <div class="stat-item">
                    <div class="stat-label">Role</div>
                    <div class="stat-value"><%= currentUser.getRole() %></div>
                </div>
                
                <div class="stat-item">
                    <div class="stat-label">Member Since</div>
                    <div class="stat-value">
                        <%= currentUser.getCreateDate() != null ? 
                            new java.text.SimpleDateFormat("MMM yyyy").format(currentUser.getCreateDate()) : 
                            "Dec 2025" %>
                    </div>
                </div>
                
                <div class="stat-item">
                    <div class="stat-label">Status</div>
                    <div class="stat-value" style="color: #4caf50;">Active ✓</div>
                </div>
            </div>
        </div>
        
        <!-- Profile Content -->
        <div class="profile-content">
            <h2 class="section-title">
                📝 Edit Profile
            </h2>
            
            <form action="updateProfile" method="post">
                
                <div class="form-group">
                    <label for="name">Full Name *</label>
                    <input type="text" id="name" name="name" 
                           value="<%= currentUser.getName() %>" required />
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="email">Email Address *</label>
                        <input type="email" id="email" name="email" 
                               value="<%= currentUser.getEmail() %>" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="phoneNo">Phone Number *</label>
                        <input type="tel" id="phoneNo" name="phoneNo" 
                               value="<%= currentUser.getPhoneNo() %>" 
                               required pattern="[0-9]{10}" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="address">Delivery Address *</label>
                    <textarea id="address" name="address" 
                              required><%= currentUser.getAddress() != null ? currentUser.getAddress() : "" %></textarea>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="userName">Username</label>
                        <input type="text" id="userName" name="userName" 
                               value="<%= currentUser.getUserName() %>" 
                               disabled />
                    </div>
                    
                    <div class="form-group">
                        <label for="role">Account Type</label>
                        <input type="text" id="role" name="role" 
                               value="<%= currentUser.getRole() %>" 
                               disabled />
                    </div>
                </div>
                
                <button type="submit" class="btn-update">💾 Save Changes</button>
            </form>
            
            <div class="security-section">
                <h2 class="section-title">
                    🔒 Account Security
                </h2>
                
                <div class="info-box">
                    <p>
                        <b>Password Management:</b><br/>
                        To change your password or update security settings, please contact our support team 
                        or use the "Forgot Password" option on the login page.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2025 Aasra Foods. All rights reserved.</p>
    <p>Delivering happiness, one meal at a time.</p>
</div>

<script>
// Form validation
document.querySelector('form').addEventListener('submit', function(e) {
    const phoneNo = document.getElementById('phoneNo').value;
    
    if (phoneNo.length !== 10 || !/^\d+$/.test(phoneNo)) {
        e.preventDefault();
        alert('❌ Please enter a valid 10-digit phone number!');
        return false;
    }
});
</script>

</body>
</html>
