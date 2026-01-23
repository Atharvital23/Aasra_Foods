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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("🏠 HomeServlet - Loading restaurants...");

        try {
            // Fetch all active restaurants from database
            RestaurantDaoI restaurantDao = new RestaurantDaoI();
            List<Restaurant> restaurantList = restaurantDao.getAll();

            // Set restaurants as request attribute
            request.setAttribute("restaurantList", restaurantList);

            System.out.println("✅ Loaded " + restaurantList.size() + " restaurants");

            // Forward to home.jsp
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("❌ Error in HomeServlet");
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
