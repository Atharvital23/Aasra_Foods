package com.aasra.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aasra.daoImpl.MenuDaoI;
import com.aasra.daoImpl.RestaurantDaoI;
import com.aasra.model.Menu;
import com.aasra.model.Restaurant;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String restaurantIdParam = request.getParameter("restaurantId");

        if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
            System.err.println("❌ Restaurant ID missing");
            response.sendRedirect("home");
            return;
        }

        try {
            int restaurantId = Integer.parseInt(restaurantIdParam);

            System.out.println("🍽️ MenuServlet - Loading menu for restaurant ID: " + restaurantId);

            // Fetch restaurant details
            RestaurantDaoI restaurantDao = new RestaurantDaoI();
            Restaurant restaurant = restaurantDao.getRestaurant(restaurantId);

            // Fetch menu items
            MenuDaoI menuDao = new MenuDaoI();
            List<Menu> menuList = menuDao.getMenuByRestaurant(restaurantId);

            if (restaurant == null) {
                System.err.println("❌ Restaurant not found: ID " + restaurantId);
                response.sendRedirect("home");
                return;
            }

            // Set attributes
            request.setAttribute("restaurant", restaurant);
            request.setAttribute("menuList", menuList);

            System.out.println("✅ Loaded " + menuList.size() + " menu items for " + restaurant.getName());

            // Forward to menu.jsp
            request.getRequestDispatcher("menu.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid restaurant ID format: " + restaurantIdParam);
            response.sendRedirect("home");
        } catch (Exception e) {
            System.err.println("❌ Error in MenuServlet");
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
