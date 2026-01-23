<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sign Up - Aasra Foods</title>
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

.signup-container {
    background: white;
    border-radius: 20px;
    box-shadow: 0 10px 40px rgba(45, 80, 22, 0.15);
    padding: 40px;
    width: 100%;
    max-width: 550px;
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

.form-row {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 15px;
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

.btn-signup {
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

.btn-signup:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(76, 175, 80, 0.3);
}

.btn-signup:active {
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

.login-link {
    text-align: center;
    margin-top: 20px;
    color: #666;
    font-size: 14px;
}

.login-link a {
    color: #4caf50;
    text-decoration: none;
    font-weight: 700;
    transition: color 0.3s ease;
}

.login-link a:hover {
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

@media (max-width: 768px) {
    .form-row {
        grid-template-columns: 1fr;
    }
}

@media (max-width: 480px) {
    .signup-container {
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

<div class="signup-container">
    <div class="logo-section">
        <div class="logo-icon">🌿</div>
        <div class="logo">Aasra<span class="logo-text">Foods</span></div>
        <p class="subtitle">Join us for delicious meals!</p>
    </div>

    <h2 class="form-title">Create Account</h2>
    <p class="form-subtitle">Fill in your details to get started</p>

    <%
    String error = request.getParameter("error");

    if ("missingFields".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Please fill in all required fields.
    </div>
    <%
    } else if ("passwordMismatch".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Passwords do not match. Please try again.
    </div>
    <%
    } else if ("usernameExists".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Username already exists. Please choose a different username.
    </div>
    <%
    } else if ("emailExists".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Email already registered. Please use a different email.
    </div>
    <%
    } else if ("invalidPhone".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Invalid phone number format. Please enter a valid phone number.
    </div>
    <%
    } else if ("registrationFailed".equals(error)) {
    %>
    <div class="alert alert-error">
        ❌ Registration failed. Please try again later.
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

    <form action="signup" method="post">
        <div class="form-group">
            <label for="name">Full Name *</label>
            <input type="text" id="name" name="name" 
                   placeholder="Enter your full name" required />
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="email">Email *</label>
                <input type="email" id="email" name="email" 
                       placeholder="your@email.com" required />
            </div>

            <div class="form-group">
                <label for="phoneNo">Phone Number *</label>
                <input type="tel" id="phoneNo" name="phoneNo" 
                       placeholder="9876543210" required pattern="[0-9]{10}" />
            </div>
        </div>

        <div class="form-group">
            <label for="address">Delivery Address *</label>
            <input type="text" id="address" name="address" 
                   placeholder="Enter your complete address" required />
        </div>

        <div class="form-group">
            <label for="userName">Username *</label>
            <input type="text" id="userName" name="userName" 
                   placeholder="Choose a unique username" required />
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="password">Password *</label>
                <input type="password" id="password" name="password" 
                       placeholder="Create a strong password" required minlength="6" />
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password *</label>
                <input type="password" id="confirmPassword" name="confirmPassword" 
                       placeholder="Re-enter password" required minlength="6" />
            </div>
        </div>

        <button type="submit" class="btn-signup">📝 Create Account</button>
    </form>

    <div class="divider">
        <span>OR</span>
    </div>

    <div class="login-link">
        Already have an account? <a href="Login.jsp">Login</a>
    </div>

    <div class="back-home">
        <a href="home">← Back to Home</a>
    </div>
</div>

<script>
// Password match validation
document.querySelector('form').addEventListener('submit', function(e) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    
    if (password !== confirmPassword) {
        e.preventDefault();
        alert('❌ Passwords do not match!');
        return false;
    }
});
</script>

</body>
</html>
