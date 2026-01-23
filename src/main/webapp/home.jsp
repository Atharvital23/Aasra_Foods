<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.aasra.model.Restaurant,com.aasra.model.User"%>
<%@ page import="com.aasra.model.Cart"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Aasra Foods - Fresh &amp; Fast</title>
<style>
/* --- General Resets --- */
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #e8f5e9 0%, #c8e6c9 100%);
    margin: 0;
    padding: 0;
    color: #333;
    min-height: 100vh;
}

/* --- Navigation Bar --- */
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
    position: relative;
}

.nav-links a:hover {
    color: #90ee90;
}

/* Underline effect for standard links only */
.nav-links a:not(.auth-btn):not(.cart-btn)::after {
    content: '';
    position: absolute;
    width: 0;
    height: 2px;
    bottom: -5px;
    left: 0;
    background: #90ee90;
    transition: width 0.3s ease;
}

.nav-links a:not(.auth-btn):not(.cart-btn):hover::after {
    width: 100%;
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

/* --- Hero Section --- */
.hero {
    background: linear-gradient(rgba(0, 50, 0, 0.5), rgba(0, 50, 0, 0.5)),
        url('https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=1200');
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
    height: 380px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: white;
    animation: heroFade 0.8s ease-out;
}

@keyframes heroFade {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}

.hero h1 {
    font-size: 52px;
    margin-bottom: 10px;
    font-weight: 700;
    text-shadow: 0 2px 8px rgba(0, 0, 0, 0.4);
    animation: slideInLeft 0.8s ease-out;
}

.hero p {
    font-size: 20px;
    margin-bottom: 30px;
    opacity: 0.9;
    animation: fadeInUp 0.8s ease-out 0.2s both;
}

@keyframes slideInLeft {
    from {
        opacity: 0;
        transform: translateX(-50px);
    }
    to {
        opacity: 1;
        transform: translateX(0);
    }
}

@keyframes fadeInUp {
    from {
        opacity: 0;
        transform: translateY(30px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

/* --- Search Bar --- */
.search-container {
    background: white;
    padding: 8px;
    border-radius: 50px;
    display: flex;
    width: 50%;
    max-width: 700px;
    box-shadow: 0 5px 20px rgba(0, 0, 0, 0.2);
    animation: fadeInUp 0.8s ease-out 0.4s both;
}

.search-container input {
    border: none;
    outline: none;
    flex: 1;
    padding: 12px 20px;
    font-size: 16px;
    border-radius: 50px;
    transition: box-shadow 0.3s ease;
}

.search-container input:focus {
    box-shadow: 0 0 15px rgba(76, 175, 80, 0.3);
}

.search-container button {
    background-color: #4caf50;
    color: white;
    border: none;
    padding: 12px 35px;
    border-radius: 40px;
    cursor: pointer;
    font-weight: bold;
    font-size: 16px;
    transition: all 0.3s ease;
    position: relative;
    overflow: hidden;
}

.search-container button:hover {
    background-color: #2d5016;
    transform: translateY(-2px);
}

.search-container button:active {
    transform: translateY(0);
}

/* --- Restaurant Grid Section --- */
.container {
    padding: 50px 4%;
    max-width: 1400px;
    margin: 0 auto;
}

.section-title {
    font-size: 32px;
    margin-bottom: 40px;
    color: #2d5016;
    font-weight: 700;
    display: inline-block;
    border-bottom: 4px solid #4caf50;
    padding-bottom: 8px;
    animation: fadeInUp 0.6s ease-out;
}

.restaurant-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
    gap: 25px;
}

/* --- Restaurant Card --- */
.card {
    background: white;
    border-radius: 12px;
    overflow: hidden;
    transition: all 0.4s cubic-bezier(0.16, 1, 0.3, 1);
    border: none;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
    animation: fadeInUp 0.6s ease-out;
    animation-fill-mode: both;
    cursor: pointer;
    position: relative;
}

.card:nth-child(1) { animation-delay: 0.1s; }
.card:nth-child(2) { animation-delay: 0.15s; }
.card:nth-child(3) { animation-delay: 0.2s; }
.card:nth-child(4) { animation-delay: 0.25s; }
.card:nth-child(5) { animation-delay: 0.3s; }
.card:nth-child(6) { animation-delay: 0.35s; }
.card:nth-child(7) { animation-delay: 0.4s; }
.card:nth-child(8) { animation-delay: 0.45s; }
.card:nth-child(9) { animation-delay: 0.5s; }
.card:nth-child(10) { animation-delay: 0.55s; }
.card:nth-child(11) { animation-delay: 0.6s; }
.card:nth-child(12) { animation-delay: 0.65s; }

.card:hover {
    transform: translateY(-10px);
    box-shadow: 0 12px 30px rgba(76, 175, 80, 0.2);
    filter: drop-shadow(0 8px 20px rgba(45, 80, 22, 0.15));
}

.card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: linear-gradient(135deg, rgba(76, 175, 80, 0.1) 0%, transparent 100%);
    opacity: 0;
    transition: opacity 0.3s ease;
    border-radius: 12px;
    pointer-events: none;
    z-index: 1;
}

.card:hover::before {
    opacity: 1;
}

.card img {
    width: 100%;
    height: 180px;
    object-fit: cover;
    transition: transform 0.4s ease;
    display: block;
}

.card:hover img {
    transform: scale(1.08);
}

.card-body {
    padding: 18px;
    position: relative;
    z-index: 2;
}

.card-title {
    font-size: 18px;
    font-weight: 700;
    margin: 0 0 8px 0;
    color: #2d5016;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.card-cuisine {
    font-size: 14px;
    color: #666;
    margin-bottom: 15px;
}

.card-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-top: 1px solid #f0f0f0;
    padding-top: 12px;
}

.rating-badge {
    background: linear-gradient(135deg, #4caf50 0%, #2d5016 100%);
    color: white;
    padding: 6px 10px;
    border-radius: 8px;
    font-size: 13px;
    font-weight: 700;
    box-shadow: 0 2px 6px rgba(76, 175, 80, 0.3);
}

.eta {
    font-size: 13px;
    color: #777;
    font-weight: 500;
}

/* Alert Messages */
.alert {
    max-width: 1400px;
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

.alert-error {
    background: #f44336;
    color: white;
    box-shadow: 0 4px 15px rgba(244, 67, 54, 0.3);
}

.alert-info {
    background: #2196f3;
    color: white;
    box-shadow: 0 4px 15px rgba(33, 150, 243, 0.3);
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
    .navbar {
        flex-direction: column;
        gap: 1rem;
    }
    .nav-links {
        gap: 1rem;
        flex-wrap: wrap;
        justify-content: center;
    }
    .search-container {
        width: 90%;
        flex-direction: column;
    }
    .hero h1 {
        font-size: 36px;
    }
    .hero p {
        font-size: 16px;
    }
    .container {
        padding: 30px 3%;
    }
    .section-title {
        font-size: 24px;
    }
    .restaurant-grid {
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 1.5rem;
    }
}

@media (max-width: 480px) {
    .navbar {
        padding: 1rem;
    }
    .logo {
        font-size: 20px;
    }
    .nav-links {
        font-size: 14px;
    }
    .hero {
        height: 280px;
    }
    .hero h1 {
        font-size: 28px;
    }
    .search-container {
        width: 95%;
    }
    .restaurant-grid {
        grid-template-columns: 1fr;
    }
}
</style>
</head>
<body>

<div class="navbar">
    <a href="home" class="logo">🌿 Aasra<span class="logo-text">Foods</span></a>
    <ul class="nav-links">
        <li><a href="home">Home</a></li>
        <li><a href="orderHistory">My Orders</a></li>
        <li><a href="#">Offers</a></li>
        <li><a href="help">Help</a></li>

        <%
        // Get Cart from session
        com.aasra.model.Cart cart = (com.aasra.model.Cart) session.getAttribute("cart");
        // Calculate size (default 0 if null)
        int cartCount = (cart != null) ? cart.getItems().size() : 0;
        %>

        <li><a href="cart.jsp" class="cart-btn">🛒 Cart (<%= cartCount %>)</a></li>

        <%
        // Check if user is logged in
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
        <li><a href="SignUp.jsp" class="auth-btn">Sign Up</a></li>
        <%
        }
        %>
    </ul>
</div>

<div class="hero">
    <h1>Delicious Food, Delivered.</h1>
    <p>Fresh meals from the best restaurants near you.</p>
    <form class="search-container" action="search" method="get">
        <input type="text" name="query" placeholder="Search for 'Biryani', 'Pizza'..." required />
        <button type="submit">🔍 Search</button>
    </form>
</div>

<!-- Alert Messages -->
<%
String error = request.getParameter("error");
String searchQuery = (String) request.getAttribute("searchQuery");

if ("emptyCart".equals(error)) {
%>
<div class="container">
    <div class="alert alert-error">
        ❌ Your cart is empty! Please add items before checkout.
    </div>
</div>
<%
}

if (searchQuery != null) {
%>
<div class="container">
    <div class="alert alert-info">
        🔍 Search results for: "<b><%= searchQuery %></b>"
    </div>
</div>
<%
}
%>

<div class="container">
    <h2 class="section-title">
        <%= searchQuery != null ? "Search Results" : "What's on your mind?" %>
    </h2>

    <div class="restaurant-grid">

        <%
        // Retrieve the restaurant list sent by HomeServlet
        List<Restaurant> restList = (List<Restaurant>) request.getAttribute("restaurantList");

        // Check if list is valid
        if (restList != null && !restList.isEmpty()) {
            for (Restaurant r : restList) {
        %>

        <div class="card" onclick="window.location.href='menu?restaurantId=<%= r.getRestaurantId() %>'">
            <img src="<%= r.getImagePath() %>" alt="<%= r.getName() %>" />

            <div class="card-body">
                <h3 class="card-title"><%= r.getName() %></h3>
                <div class="card-cuisine"><%= r.getCuisineType() %></div>

                <div class="card-footer">
                    <span class="rating-badge">⭐ <%= r.getRatings() %></span>
                    <span class="eta">🕐 <%= r.getEta() %> mins</span>
                </div>
            </div>
        </div>

        <%
            }
        } else {
        %>
        <p style="grid-column: 1/-1; text-align: center; color: #666; font-size: 18px; padding: 40px;">
            <%= searchQuery != null ? "No restaurants found matching your search." : "No restaurants available at the moment." %>
        </p>
        <%
        }
        %>

    </div>
</div>

<div class="footer">
    <p>&copy; 2025 Aasra Foods. All rights reserved.</p>
    <p>Delivering happiness, one meal at a time.</p>
</div>

<script>
// Smooth scroll for navigation links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        if (this.getAttribute('href') !== '#') {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        }
    });
});

// Search functionality
const searchForm = document.querySelector('.search-container');
const searchInput = document.querySelector('.search-container input');

searchForm.addEventListener('submit', function(e) {
    const query = searchInput.value;
    if (!query.trim()) {
        e.preventDefault();
        alert('⚠️ Please enter a search query!');
    }
});

// Card click handler
document.querySelectorAll('.card').forEach(card => {
    card.addEventListener('click', function(e) {
        if (e.target !== this && !e.target.closest('form')) {
            console.log('Navigate to restaurant details');
        }
    });
});

// Intersection Observer for scroll animations
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.animation = 'fadeInUp 0.6s ease-out forwards';
        }
    });
}, observerOptions);

document.querySelectorAll('.card').forEach(card => {
    observer.observe(card);
});
</script>

</body>
</html>
