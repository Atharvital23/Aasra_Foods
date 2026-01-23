package com.aasra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.daoImpl.OrderItemsDaoI;
import com.aasra.daoImpl.RestaurantDaoI;
import com.aasra.model.User;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

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
            // Get form data
            String orderItemsIdStr = request.getParameter("orderItemsId");
            String restaurantIdStr = request.getParameter("restaurantId");
            String ratingStr = request.getParameter("rating");
            String comments = request.getParameter("comments");

            if (ratingStr == null || ratingStr.isEmpty()) {
                System.err.println("❌ Rating failed: Rating value missing");
                response.sendRedirect("orderHistory?error=missingRating");
                return;
            }

            double rating = Double.parseDouble(ratingStr);

            // Validate rating range (1-5)
            if (rating < 1 || rating > 5) {
                System.err.println("❌ Rating failed: Invalid rating value - " + rating);
                response.sendRedirect("orderHistory?error=invalidRating");
                return;
            }

            System.out.println("⭐ Processing rating: " + rating + " stars");

            // Update order item rating (if orderItemsId provided)
            if (orderItemsIdStr != null && !orderItemsIdStr.isEmpty()) {
                int orderItemsId = Integer.parseInt(orderItemsIdStr);

                OrderItemsDaoI orderItemsDao = new OrderItemsDaoI();
                orderItemsDao.updateRating(orderItemsId, rating, comments);

                System.out.println("✅ Order item rated: ID " + orderItemsId);
            }

            // Update restaurant rating (if restaurantId provided)
            if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
                int restaurantId = Integer.parseInt(restaurantIdStr);

                RestaurantDaoI restaurantDao = new RestaurantDaoI();
                restaurantDao.updateRestaurantRating(restaurantId, rating, comments);

                System.out.println("✅ Restaurant rated: ID " + restaurantId);
            }

            // Redirect back to order history
            response.sendRedirect("orderHistory?success=rated");

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid rating format");
            e.printStackTrace();
            response.sendRedirect("orderHistory?error=invalidInput");
        } catch (Exception e) {
            System.err.println("❌ Error in RatingServlet");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("orderHistory");
    }
}
