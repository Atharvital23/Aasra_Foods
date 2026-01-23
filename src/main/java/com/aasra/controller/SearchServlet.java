package com.aasra.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aasra.daoImpl.RestaurantDaoI;
import com.aasra.model.Restaurant;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query");

        if (query == null || query.trim().isEmpty()) {
            System.out.println("⚠️ Empty search query. Redirecting to home...");
            response.sendRedirect("home");
            return;
        }

        try {
            System.out.println("🔍 Searching for: " + query);

            // Search restaurants
            RestaurantDaoI restaurantDao = new RestaurantDaoI();
            List<Restaurant> restaurantList = restaurantDao.searchRestaurants(query);

            // Set search results as request attribute
            request.setAttribute("restaurantList", restaurantList);
            request.setAttribute("searchQuery", query);

            System.out.println("✅ Found " + restaurantList.size() + " restaurants matching: " + query);

            // Forward to home.jsp (it will display search results)
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("❌ Error in SearchServlet");
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
