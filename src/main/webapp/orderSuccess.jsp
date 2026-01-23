<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Order Successful - Aasra Foods</title>
<style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%);
    margin: 0;
    padding: 0;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.success-container {
    background: white;
    border-radius: 20px;
    padding: 60px 40px;
    max-width: 600px;
    text-align: center;
    box-shadow: 0 10px 40px rgba(45, 80, 22, 0.15);
    animation: zoomIn 0.6s ease-out;
}

@keyframes zoomIn {
    from {
        opacity: 0;
        transform: scale(0.8);
    }
    to {
        opacity: 1;
        transform: scale(1);
    }
}

.success-icon {
    font-size: 100px;
    margin-bottom: 30px;
    animation: bounce 1s ease-in-out;
}

@keyframes bounce {
    0%, 20%, 50%, 80%, 100% {
        transform: translateY(0);
    }
    40% {
        transform: translateY(-20px);
    }
    60% {
        transform: translateY(-10px);
    }
}

.success-title {
    font-size: 36px;
    font-weight: 800;
    color: #2d5016;
    margin-bottom: 15px;
}

.success-message {
    font-size: 18px;
    color: #666;
    margin-bottom: 30px;
    line-height: 1.6;
}

.order-id {
    background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%);
    padding: 20px;
    border-radius: 15px;
    margin-bottom: 30px;
}

.order-id-label {
    font-size: 14px;
    color: #666;
    margin-bottom: 5px;
}

.order-id-value {
    font-size: 32px;
    font-weight: 800;
    color: #4caf50;
}

.info-box {
    background: #f9f9f9;
    padding: 20px;
    border-radius: 15px;
    margin-bottom: 30px;
    border-left: 4px solid #4caf50;
}

.info-text {
    font-size: 15px;
    color: #666;
    line-height: 1.8;
    text-align: left;
}

.btn-group {
    display: flex;
    gap: 15px;
    justify-content: center;
}

.btn {
    padding: 15px 30px;
    border: none;
    border-radius: 10px;
    font-size: 16px;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.3s ease;
    text-decoration: none;
    display: inline-block;
}

.btn-primary {
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
}

.btn-primary:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(76, 175, 80, 0.3);
}

.btn-secondary {
    background: white;
    color: #4caf50;
    border: 2px solid #4caf50;
}

.btn-secondary:hover {
    background: #4caf50;
    color: white;
}

@media (max-width: 480px) {
    .success-container {
        padding: 40px 20px;
    }
    
    .success-icon {
        font-size: 80px;
    }
    
    .success-title {
        font-size: 28px;
    }
    
    .btn-group {
        flex-direction: column;
    }
}
</style>
</head>
<body>

<div class="success-container">
    <div class="success-icon">✅</div>
    
    <h1 class="success-title">Order Placed Successfully!</h1>
    
    <p class="success-message">
        Thank you for your order! Your delicious food is being prepared and will be delivered soon.
    </p>
    
    <%
    String orderIdParam = request.getParameter("orderId");
    
    if (orderIdParam != null) {
    %>
    <div class="order-id">
        <div class="order-id-label">Your Order ID</div>
        <div class="order-id-value">#<%= orderIdParam %></div>
    </div>
    <%
    }
    %>
    
    <div class="info-box">
        <div class="info-text">
            📦 <b>What happens next?</b><br/>
            • Your order is confirmed and being prepared<br/>
            • You will receive updates on your order status<br/>
            • A delivery agent will be assigned shortly<br/>
            • Track your order in "My Orders" section
        </div>
    </div>
    
    <div class="btn-group">
        <a href="orderHistory" class="btn btn-primary">
            📦 View My Orders
        </a>
        
        <a href="home" class="btn btn-secondary">
            🏠 Back to Home
        </a>
    </div>
</div>

</body>
</html>
