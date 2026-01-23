package com.aasra.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.daoImpl.OrdersDaoI;
import com.aasra.model.Orders;
import com.aasra.model.User;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            System.out.println("⚠️ User not logged in. Redirecting to login...");
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            System.out.println("📦 Fetching order history for user: " + loggedInUser.getName());

            // Fetch orders by user ID
            OrdersDaoI ordersDao = new OrdersDaoI();
            List<Orders> orderList = ordersDao.getOrdersByUser(loggedInUser.getUserId());

            // Set orders as request attribute
            request.setAttribute("orderList", orderList);

            System.out.println("✅ Found " + orderList.size() + " orders for user ID: " + loggedInUser.getUserId());

            // Forward to orderHistory.jsp
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("❌ Error in OrderHistoryServlet");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
