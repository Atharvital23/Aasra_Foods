<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login - Aasra Foods</title>
<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%);
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 20px;
}

.login-container {
    background: white;
    border-radius: 20px;
    box-shadow: 0 10px 40px rgba(45, 80, 22, 0.15);
    padding: 40px;
    width: 100%;
    max-width: 450px;
    animation: slideUp 0.5s ease-out;
}

@keyframes slideUp {
    from {
        opacity: 0;
        transform: translateY(30px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.logo-section {
    text-align: center;
    margin-bottom: 30px;
}

.logo {
    font-size: 36px;
    font-weight: 800;
    color: #2d5016;
    margin-bottom: 10px;
}

.logo-icon {
    font-size: 48px;
}

.logo-text {
    color: #4caf50;
}

.subtitle {
    color: #666;
    font-size: 16px;
}

.form-title {
    font-size: 28px;
    font-weight: 700;
    color: #2d5016;
    margin-bottom: 10px;
    text-align: center;
}

.form-subtitle {
    text-align: center;
    color: #666;
    margin-bottom: 30px;
    font-size: 14px;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    color: #2d5016;
    font-weight: 600;
    font-size: 14px;
}

.form-group input {
    width: 100%;
    padding: 12px 15px;
    border: 2px solid #e0e0e0;
    border-radius: 10px;
    font-size: 15px;
    transition: all 0.3s ease;
}

.form-group input:focus {
    outline: none;
    border-color: #4caf50;
    box-shadow: 0 0 10px rgba(76, 175, 80, 0.2);
}

.btn-login {
    width: 100%;
    padding: 14px;
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
    border: none;
    border-radius: 10px;
    font-size: 16px;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.3s ease;
    margin-top: 10px;
}

.btn-login:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(76, 175, 80, 0.3);
}

.btn-login:active {
    transform: translateY(0);
}

.divider {
    text-align: center;
    margin: 25px 0;
    position: relative;
}

.divider::before {
    content: '';
    position: absolute;
    left: 0;
    top: 50%;
    width: 100%;
    height: 1px;
    background: #e0e0e0;
}

.divider span {
    background: white;
    padding: 0 15px;
    position: relative;
    color: #999;
    font-size: 14px;
}

.signup-link {
    text-align: center;
    margin-top: 20px;
    color: #666;
    font-size: 14px;
}

.signup-link a {
    color: #4caf50;
    text-decoration: none;
    font-weight: 700;
    transition: color 0.3s ease;
}

.signup-link a:hover {
    color: #2d5016;
}

.alert {
    padding: 12px 15px;
    border-radius: 8px;
    margin-bottom: 20px;
    font-size: 14px;
    animation: slideDown 0.4s ease-out;
}

@keyframes slideDown {
    from {
        opacity: 0;
        transform: translateY(-10px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.alert-success {
    background-color: #d4edda;
    color: #155724;
    border: 1px solid #c3e6cb;
}

.alert-error {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
}

.back-home {
    text-align: center;
    margin-top: 20px;
}

.back-home a {
    color: #4caf50;
    text-decoration: none;
    font-weight: 600;
    font-size: 14px;
    transition: color 0.3s ease;
}

.back-home a:hover {
    color: #2d5016;
}

@media (max-width: 480px) {
    .login-container {
        padding: 30px 20px;
    }
    
    .logo {
        font-size: 28px;
    }
    
    .form-title {
        font-size: 24px;
    }
}
</style>
</head>
<body>

<div class="login-container">
    <div class="logo-section">
        <div class="logo-icon">🌿</div>
        <div class="logo">Aasra<span class="logo-text">Foods</span></div>
        <p class="subtitle">Delicious Food, Delivered Fresh</p>
    </div>

    <h2 class="form-title">Welcome Back!</h2>
    <p class="form-subtitle">Sign in to continue ordering</p>

    <%
    String error = request.getParameter("error");
    String success = request.getParameter("success");

    if ("registered".equals(success)) {
    %>
    <div class="alert alert-success">
        ✅ Registration successful! Please login to continue.
    </div>
    <%
    } else if ("loggedOut".equals(success)) {
    %>
    <div class="alert alert-success">
        👋 You have been logged out successfully.
    </div>
    <%
    }

    if ("emptyFields".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Please fill in all fields.
    </div>
    <%
    } else if ("invalidCredentials".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Invalid username or password. Please try again.
    </div>
    <%
    } else if ("notCustomer".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ This portal is for customers only. Please use the appropriate login portal.
    </div>
    <%
    } else if ("systemError".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ System error occurred. Please try again later.
    </div>
    <%
    }
    %>

    <form action="login" method="post">
        <div class="form-group">
            <label for="userName">Username</label>
            <input type="text" id="userName" name="userName" 
                   placeholder="Enter your username" required />
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" 
                   placeholder="Enter your password" required />
        </div>

        <button type="submit" class="btn-login">🔐 Login</button>
    </form>

    <div class="divider">
        <span>OR</span>
    </div>

    <div class="signup-link">
        Don't have an account? <a href="SignUp.jsp">Sign Up</a>
    </div>

    <div class="back-home">
        <a href="home">← Back to Home</a>
    </div>
</div>

</body>
</html>
