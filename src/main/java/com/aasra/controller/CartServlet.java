package com.aasra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.daoImpl.MenuDaoI;
import com.aasra.model.Cart;
import com.aasra.model.CartItem;
import com.aasra.model.Menu;
import com.aasra.model.User;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("cart.jsp");
            return;
        }

        switch (action) {
            case "add":
                addToCart(request, response);
                break;
            case "update":
                updateCart(request, response);
                break;
            case "remove":
                removeFromCart(request, response);
                break;
            case "clear":
                clearCart(request, response);
                break;
            default:
                response.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display cart
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    // ===================================
    // ADD ITEM TO CART
    // ===================================
    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            System.out.println("⚠️ User not logged in. Redirecting to login...");
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            System.out.println("🛒 Adding to cart: Menu ID " + menuId + ", Quantity: " + quantity);

            // Fetch menu item details
            MenuDaoI menuDao = new MenuDaoI();
            Menu menu = menuDao.getMenu(menuId);

            if (menu == null || !menu.isAvailable()) {
                System.err.println("❌ Menu item not available: ID " + menuId);
                response.sendRedirect("menu?restaurantId=" + restaurantId + "&error=unavailable");
                return;
            }

            // Get or create cart from session
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            // Create cart item
            CartItem item = new CartItem(menuId, restaurantId, menu.getName(), menu.getPrice(), quantity);
            item.setImagePath(menu.getImagePath());
            item.setUserId(loggedInUser.getUserId());

            // Add to cart
            cart.addItem(item);

            System.out.println("✅ Item added to cart: " + menu.getName() + " x " + quantity);

            // Redirect back to menu
            response.sendRedirect("menu?restaurantId=" + restaurantId + "&success=added");

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid input in addToCart");
            e.printStackTrace();
            response.sendRedirect("home");
        } catch (Exception e) {
            System.err.println("❌ Error adding to cart");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // ===================================
    // UPDATE CART QUANTITY
    // ===================================
    private void updateCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            response.sendRedirect("cart.jsp");
            return;
        }

        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            System.out.println("🔄 Updating cart: Menu ID " + menuId + " → Quantity: " + quantity);

            cart.updateQuantity(menuId, quantity);

            System.out.println("✅ Cart updated successfully");

            response.sendRedirect("cart.jsp?success=updated");

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid input in updateCart");
            e.printStackTrace();
            response.sendRedirect("cart.jsp");
        } catch (Exception e) {
            System.err.println("❌ Error updating cart");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // ===================================
    // REMOVE ITEM FROM CART
    // ===================================
    private void removeFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            response.sendRedirect("cart.jsp");
            return;
        }

        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));

            System.out.println("🗑️ Removing from cart: Menu ID " + menuId);

            cart.removeItem(menuId);

            System.out.println("✅ Item removed from cart");

            response.sendRedirect("cart.jsp?success=removed");

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid menu ID in removeFromCart");
            e.printStackTrace();
            response.sendRedirect("cart.jsp");
        } catch (Exception e) {
            System.err.println("❌ Error removing from cart");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // ===================================
    // CLEAR ENTIRE CART
    // ===================================
    private void clearCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            cart.clear();
            System.out.println("✅ Cart cleared");
        }

        response.sendRedirect("cart.jsp?success=cleared");
    }
}
