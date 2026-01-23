package com.aasra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.daoImpl.OrdersDaoI;
import com.aasra.model.Orders;
import com.aasra.model.User;

@WebServlet("/cancelOrder")
public class CancelOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            System.out.println("⚠️ User not logged in. Redirecting to login...");
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            String orderIdStr = request.getParameter("orderId");

            if (orderIdStr == null || orderIdStr.isEmpty()) {
                System.err.println("❌ Order ID missing");
                response.sendRedirect("orderHistory?error=missingOrderId");
                return;
            }

            int orderId = Integer.parseInt(orderIdStr);

            System.out.println("❌ Attempting to cancel order: ID " + orderId);

            OrdersDaoI ordersDao = new OrdersDaoI();

            // Verify order belongs to user
            Orders order = ordersDao.getOrder(orderId);

            if (order == null) {
                System.err.println("❌ Order not found: ID " + orderId);
                response.sendRedirect("orderHistory?error=orderNotFound");
                return;
            }

            if (order.getUserId() != loggedInUser.getUserId()) {
                System.err.println("❌ Unauthorized cancellation attempt for order: ID " + orderId);
                response.sendRedirect("orderHistory?error=unauthorized");
                return;
            }

            // Cancel order
            boolean success = ordersDao.cancelOrder(orderId);

            if (success) {
                System.out.println("✅ Order cancelled successfully: ID " + orderId);
                response.sendRedirect("orderHistory?success=cancelled");
            } else {
                System.err.println("❌ Order cancellation failed: ID " + orderId);
                response.sendRedirect("orderHistory?error=cannotCancel");
            }

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid order ID format");
            e.printStackTrace();
            response.sendRedirect("orderHistory?error=invalidOrderId");
        } catch (Exception e) {
            System.err.println("❌ Error in CancelOrderServlet");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
