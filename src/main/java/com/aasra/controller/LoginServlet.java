package com.aasra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.daoImpl.UserDaoI;
import com.aasra.model.User;
import com.aasra.model.User.Role;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            System.err.println("❌ Login failed: Username or password is empty");
            response.sendRedirect("Login.jsp?error=emptyFields");
            return;
        }

        try {
            System.out.println("🔐 Login attempt: " + userName);

            // Authenticate user
            UserDaoI userDao = new UserDaoI();
            User user = userDao.loginUser(userName, password);

            if (user != null) {
                // Check if user is a customer
                if (user.getRole() != Role.Customer) {
                    System.err.println("❌ Access denied: User is not a customer - " + user.getRole());
                    response.sendRedirect("Login.jsp?error=notCustomer");
                    return;
                }

                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", user);
                session.setMaxInactiveInterval(30 * 60); // 30 minutes

                System.out.println("✅ Login successful: " + user.getName() + " (Customer)");

                // Redirect to home
                response.sendRedirect("home");

            } else {
                System.err.println("❌ Login failed: Invalid credentials for " + userName);
                response.sendRedirect("Login.jsp?error=invalidCredentials");
            }

        } catch (Exception e) {
            System.err.println("❌ Error in LoginServlet");
            e.printStackTrace();
            response.sendRedirect("Login.jsp?error=systemError");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Login.jsp");
    }
}
