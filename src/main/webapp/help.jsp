<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.aasra.model.User,com.aasra.model.Cart,java.util.List,java.util.Map,java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Help & Support - Aasra Foods</title>
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

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
    font-size: 42px;
    font-weight: 800;
    color: #2d4520;
    margin-bottom: 15px;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 15px;
}

.page-subtitle {
    text-align: center;
    color: #666;
    font-size: 18px;
    margin-bottom: 50px;
}

/* Help Grid */
.help-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 30px;
    margin-bottom: 50px;
}

/* Help Card */
.help-card {
    background: white;
    border-radius: 15px;
    padding: 35px 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    text-align: center;
    transition: all 0.3s ease;
}

.help-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 25px rgba(76, 175, 80, 0.2);
}

.help-icon {
    font-size: 56px;
    margin-bottom: 20px;
}

.help-title {
    font-size: 22px;
    font-weight: 700;
    color: #2d4520;
    margin-bottom: 15px;
}

.help-description {
    font-size: 15px;
    color: #666;
    line-height: 1.7;
    margin-bottom: 20px;
}

.help-info {
    font-size: 16px;
    color: #2d4520;
    font-weight: 600;
    margin: 10px 0;
}

.help-link {
    color: #4caf50;
    text-decoration: none;
    font-weight: 600;
}

.help-link:hover {
    text-decoration: underline;
}

/* Contact Cards */
.contact-section {
    background: white;
    border-radius: 15px;
    padding: 40px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    margin-bottom: 40px;
}

.section-title {
    font-size: 28px;
    font-weight: 700;
    color: #2d4520;
    margin-bottom: 30px;
    padding-bottom: 15px;
    border-bottom: 3px solid #4caf50;
    display: flex;
    align-items: center;
    gap: 12px;
}

.contact-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 25px;
}

.contact-card {
    background: linear-gradient(135deg, #f5f9f5 0%, #e8f5e9 100%);
    padding: 25px;
    border-radius: 12px;
    border-left: 4px solid #4caf50;
    transition: all 0.3s ease;
}

.contact-card:hover {
    transform: translateX(5px);
    box-shadow: 0 4px 15px rgba(76, 175, 80, 0.2);
}

.contact-icon {
    font-size: 32px;
    margin-bottom: 15px;
}

.contact-label {
    font-size: 13px;
    color: #999;
    margin-bottom: 5px;
    font-weight: 500;
}

.contact-value {
    font-size: 18px;
    font-weight: 700;
    color: #2d4520;
    word-break: break-word;
}

/* Complaint Form */
.complaint-form {
    background: white;
    border-radius: 15px;
    padding: 40px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    margin-bottom: 40px;
}

.form-group {
    margin-bottom: 25px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    color: #2d4520;
    font-weight: 600;
    font-size: 15px;
}

.form-group input,
.form-group textarea,
.form-group select {
    width: 100%;
    padding: 12px 16px;
    border: 2px solid #e0e0e0;
    border-radius: 8px;
    font-size: 15px;
    transition: all 0.3s ease;
    font-family: inherit;
}

.form-group input:focus,
.form-group textarea:focus,
.form-group select:focus {
    outline: none;
    border-color: #4caf50;
    box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.1);
}

.form-group textarea {
    resize: vertical;
    min-height: 120px;
}

.form-row {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
}

.btn-submit {
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

.btn-submit:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 20px rgba(76, 175, 80, 0.4);
}

.btn-submit:active {
    transform: translateY(0);
}

/* My Complaints Section */
.complaints-history {
    background: white;
    border-radius: 15px;
    padding: 40px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    margin-bottom: 40px;
}

.complaint-item {
    background: #f9f9f9;
    border-radius: 12px;
    padding: 25px;
    margin-bottom: 20px;
    border-left: 4px solid #4caf50;
    transition: all 0.3s ease;
}

.complaint-item:hover {
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    transform: translateX(5px);
}

.complaint-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
    flex-wrap: wrap;
    gap: 10px;
}

.complaint-id {
    font-size: 18px;
    font-weight: 700;
    color: #2d4520;
}

.complaint-status {
    padding: 8px 20px;
    border-radius: 20px;
    font-weight: 700;
    font-size: 14px;
    text-transform: uppercase;
}

.status-PENDING {
    background: #ff9800;
    color: white;
}

.status-IN_PROGRESS {
    background: #2196f3;
    color: white;
}

.status-RESOLVED {
    background: #4caf50;
    color: white;
}

.status-CLOSED {
    background: #9e9e9e;
    color: white;
}

.complaint-details {
    margin-top: 15px;
}

.detail-row {
    display: grid;
    grid-template-columns: 150px 1fr;
    margin-bottom: 10px;
    gap: 10px;
}

.detail-label {
    font-size: 14px;
    color: #999;
    font-weight: 600;
}

.detail-value {
    font-size: 14px;
    color: #333;
}

.complaint-description {
    margin-top: 15px;
    padding-top: 15px;
    border-top: 1px solid #e0e0e0;
}

.empty-complaints {
    text-align: center;
    padding: 60px 20px;
    color: #999;
}

.empty-icon {
    font-size: 64px;
    margin-bottom: 20px;
}

/* Alert */
.alert {
    padding: 15px 25px;
    border-radius: 10px;
    margin-bottom: 25px;
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
    background-color: #3d5a2c;
    color: white;
    text-align: center;
    padding: 2rem;
    margin-top: 4rem;
}

/* Responsive */
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
    
    .page-title {
        font-size: 32px;
    }
    
    .help-grid,
    .contact-grid,
    .form-row {
        grid-template-columns: 1fr;
    }
    
    .contact-section,
    .complaint-form,
    .complaints-history {
        padding: 25px 20px;
    }
    
    .detail-row {
        grid-template-columns: 1fr;
    }
    
    .complaint-header {
        flex-direction: column;
        align-items: flex-start;
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
        <li><a href="#">Offers</a></li>
        <li><a href="help.jsp">Help</a></li>
        
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
    <h1 class="page-title">
        🆘 Help & Support
    </h1>
    <p class="page-subtitle">We're here to help! Contact us anytime.</p>
    
    <%
    String success = request.getParameter("success");
    String error = request.getParameter("error");
    
    if ("submitted".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Your complaint has been submitted successfully! We'll get back to you soon.
    </div>
    <%
    } else if ("failed".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Failed to submit complaint. Please try again.
    </div>
    <%
    } else if ("missingFields".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Please fill in all required fields!
    </div>
    <%
    }
    %>
    
    <!-- My Complaints History (Only for logged-in users) -->
    <%
    if (currentUser != null) {
        List<Map<String, Object>> myComplaints = (List<Map<String, Object>>) request.getAttribute("myComplaints");
        
        if (myComplaints != null && !myComplaints.isEmpty()) {
    %>
    <div class="complaints-history">
        <h2 class="section-title">
            📋 My Complaints (<%= myComplaints.size() %>)
        </h2>
        
        <%
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a");
        
        for (Map<String, Object> complaint : myComplaints) {
            int complaintId = (Integer) complaint.get("complaintId");
            String complaintType = (String) complaint.get("complaintType");
            String subject = (String) complaint.get("subject");
            String description = (String) complaint.get("description");
            String status = (String) complaint.get("status");
            java.sql.Timestamp createdAt = (java.sql.Timestamp) complaint.get("createdAt");
            Integer orderId = (Integer) complaint.get("orderId");
        %>
        
        <div class="complaint-item">
            <div class="complaint-header">
                <div class="complaint-id">Complaint #<%= complaintId %></div>
                <div class="complaint-status status-<%= status %>"><%= status.replace("_", " ") %></div>
            </div>
            
            <div class="complaint-details">
                <div class="detail-row">
                    <span class="detail-label">Subject:</span>
                    <span class="detail-value"><b><%= subject %></b></span>
                </div>
                
                <div class="detail-row">
                    <span class="detail-label">Type:</span>
                    <span class="detail-value"><%= complaintType.replace("_", " ").toUpperCase() %></span>
                </div>
                
                <% if (orderId != null) { %>
                <div class="detail-row">
                    <span class="detail-label">Order ID:</span>
                    <span class="detail-value">#<%= orderId %></span>
                </div>
                <% } %>
                
                <div class="detail-row">
                    <span class="detail-label">Submitted On:</span>
                    <span class="detail-value"><%= dateFormat.format(createdAt) %></span>
                </div>
            </div>
            
            <div class="complaint-description">
                <div class="detail-label" style="margin-bottom: 8px;">Description:</div>
                <div class="detail-value"><%= description %></div>
            </div>
        </div>
        
        <%
        }
        %>
    </div>
    <%
        }
    }
    %>
    
    <!-- Quick Help Section -->
    <div class="help-grid">
        <div class="help-card">
            <div class="help-icon">📞</div>
            <h3 class="help-title">Customer Support</h3>
            <p class="help-description">Need immediate assistance? Call our 24/7 support team.</p>
            <p class="help-info">📱 +91 1800-123-4567</p>
            <p class="help-info">⏰ Available 24/7</p>
        </div>
        
        <div class="help-card">
            <div class="help-icon">✉️</div>
            <h3 class="help-title">Email Support</h3>
            <p class="help-description">Send us an email and we'll respond within 24 hours.</p>
            <p class="help-info">
                <a href="mailto:support@aasrafoods.com" class="help-link">
                    support@aasrafoods.com
                </a>
            </p>
            <p class="help-info">⏰ Reply within 24 hrs</p>
        </div>
        
        <div class="help-card">
            <div class="help-icon">💬</div>
            <h3 class="help-title">Live Chat</h3>
            <p class="help-description">Chat with our support team in real-time.</p>
            <p class="help-info">Available: 9 AM - 9 PM</p>
            <p class="help-info" style="margin-top: 10px;">
                <a href="#" class="help-link">Start Chat →</a>
            </p>
        </div>
    </div>
    
    <!-- Admin Contact Section -->
    <div class="contact-section">
        <h2 class="section-title">
            👨‍💼 System Admin Contact
        </h2>
        
        <div class="contact-grid">
            <div class="contact-card">
                <div class="contact-icon">👤</div>
                <div class="contact-label">Admin Name</div>
                <div class="contact-value">Aasra Foods Admin</div>
            </div>
            
            <div class="contact-card">
                <div class="contact-icon">📧</div>
                <div class="contact-label">Email Address</div>
                <div class="contact-value">admin@aasrafoods.com</div>
            </div>
            
            <div class="contact-card">
                <div class="contact-icon">📞</div>
                <div class="contact-label">Phone Number</div>
                <div class="contact-value">+91 9876543210</div>
            </div>
            
            <div class="contact-card">
                <div class="contact-icon">🏢</div>
                <div class="contact-label">Office Address</div>
                <div class="contact-value">Aasra Foods Headquarters, Maharashtra, India</div>
            </div>
            
            <div class="contact-card">
                <div class="contact-icon">💼</div>
                <div class="contact-label">WhatsApp Support</div>
                <div class="contact-value">+91 9876543210</div>
            </div>
            
            <div class="contact-card">
                <div class="contact-icon">⏰</div>
                <div class="contact-label">Office Hours</div>
                <div class="contact-value">Mon - Sat: 9 AM - 6 PM</div>
            </div>
        </div>
    </div>
    
    <!-- Complaint Form -->
    <div class="complaint-form">
        <h2 class="section-title">
            📝 Submit a Complaint
        </h2>
        
        <form action="submitComplaint" method="post">
            <div class="form-row">
                <div class="form-group">
                    <label for="name">Your Name *</label>
                    <input type="text" id="name" name="name" 
                           value="<%= currentUser != null ? currentUser.getName() : "" %>" 
                           required />
                </div>
                
                <div class="form-group">
                    <label for="email">Email Address *</label>
                    <input type="email" id="email" name="email" 
                           value="<%= currentUser != null ? currentUser.getEmail() : "" %>" 
                           required />
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="phone">Phone Number *</label>
                    <input type="tel" id="phone" name="phone" 
                           value="<%= currentUser != null ? String.valueOf(currentUser.getPhoneNo()) : "" %>" 
                           required pattern="[0-9]{10}" />
                </div>
                
                <div class="form-group">
                    <label for="complaintType">Complaint Type *</label>
                    <select id="complaintType" name="complaintType" required>
                        <option value="">-- Select Type --</option>
                        <option value="food_quality">Food Quality Issue</option>
                        <option value="delivery">Delivery Problem</option>
                        <option value="wrong_order">Wrong Order Received</option>
                        <option value="late_delivery">Late Delivery</option>
                        <option value="missing_items">Missing Items</option>
                        <option value="payment">Payment Issue</option>
                        <option value="restaurant">Restaurant Issue</option>
                        <option value="other">Other</option>
                    </select>
                </div>
            </div>
            
            <div class="form-group">
                <label for="orderId">Order ID (Optional)</label>
                <input type="text" id="orderId" name="orderId" 
                       placeholder="Enter order ID if complaint is related to an order" />
            </div>
            
            <div class="form-group">
                <label for="subject">Subject *</label>
                <input type="text" id="subject" name="subject" 
                       placeholder="Brief summary of your complaint" required />
            </div>
            
            <div class="form-group">
                <label for="description">Complaint Description *</label>
                <textarea id="description" name="description" 
                          placeholder="Please provide detailed information about your complaint..." 
                          required></textarea>
            </div>
            
            <button type="submit" class="btn-submit">📨 Submit Complaint</button>
        </form>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>&copy; 2025 Aasra Foods. All rights reserved.</p>
    <p>Your satisfaction is our priority.</p>
</div>

<script>
// Form validation
document.querySelector('form').addEventListener('submit', function(e) {
    const phone = document.getElementById('phone').value;
    const description = document.getElementById('description').value;
    
    if (phone.length !== 10 || !/^\d+$/.test(phone)) {
        e.preventDefault();
        alert('❌ Please enter a valid 10-digit phone number!');
        return false;
    }
    
    if (description.trim().length < 20) {
        e.preventDefault();
        alert('❌ Please provide a detailed description (at least 20 characters).');
        return false;
    }
});
</script>

</body>
</html>
