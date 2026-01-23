package com.aasra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.model.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("loggedInUser");
            
            if (user != null) {
                System.out.println("👋 User logged out: " + user.getName());
            }

            // Invalidate session
            session.invalidate();
        }

        System.out.println("✅ Session destroyed");

        // Redirect to login page
        response.sendRedirect("Login.jsp?success=loggedOut");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
