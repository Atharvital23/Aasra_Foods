package com.aasra.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.daoImpl.OrderItemsDaoI;
import com.aasra.daoImpl.OrdersDaoI;
import com.aasra.daoImpl.OrderHistoryDaoI;
import com.aasra.model.Cart;
import com.aasra.model.CartItem;
import com.aasra.model.OrderHistory;
import com.aasra.model.OrderItems;
import com.aasra.model.Orders;
import com.aasra.model.Orders.OrderStatus;
import com.aasra.model.Orders.PaymentMode;
import com.aasra.model.User;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Cart cart = (Cart) session.getAttribute("cart");

        if (loggedInUser == null) {
            System.out.println("⚠️ User not logged in. Redirecting to login...");
            response.sendRedirect("Login.jsp");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            System.out.println("⚠️ Cart is empty. Redirecting to home...");
            response.sendRedirect("home?error=emptyCart");
            return;
        }

        // Display checkout page
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Cart cart = (Cart) session.getAttribute("cart");

        if (loggedInUser == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("home?error=emptyCart");
            return;
        }

        try {
            // Get form data
            String paymentModeStr = request.getParameter("paymentMode");
            String deliveryAddress = request.getParameter("deliveryAddress");

            if (paymentModeStr == null || deliveryAddress == null || deliveryAddress.isEmpty()) {
                System.err.println("❌ Missing checkout information");
                response.sendRedirect("checkout.jsp?error=missingInfo");
                return;
            }

            PaymentMode paymentMode = PaymentMode.valueOf(paymentModeStr);

            System.out.println("💳 Processing checkout for user: " + loggedInUser.getName());
            System.out.println("   Payment Mode: " + paymentMode);
            System.out.println("   Total Amount: ₹" + cart.getTotalAmount());

            // Get restaurant ID (assuming all items from same restaurant)
            int restaurantId = 0;
            for (CartItem item : cart.getItems().values()) {
                restaurantId = item.getRestaurantId();
                break;
            }

            // Create order
            Orders order = new Orders();
            order.setRestaurantId(restaurantId);
            order.setUserId(loggedInUser.getUserId());
            order.setTotalAmount(cart.getTotalAmount());
            order.setModeOfPayment(paymentMode);
            order.setStatus(OrderStatus.CONFIRMED);

            // Save order to database
            OrdersDaoI ordersDao = new OrdersDaoI();
            int orderId = ordersDao.createOrder(order);

            if (orderId > 0) {
                // Save order items
                OrderItemsDaoI orderItemsDao = new OrderItemsDaoI();

                for (CartItem cartItem : cart.getItems().values()) {
                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(cartItem.getMenuId());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setRatings(0.0);
                    orderItem.setComments("");

                    orderItemsDao.addOrderItem(orderItem);
                }

                // Save order history
                OrderHistoryDaoI historyDao = new OrderHistoryDaoI();
                OrderHistory history = new OrderHistory(orderId, loggedInUser.getUserId(), null);
                historyDao.addOrderHistory(history);

                // Clear cart
                cart.clear();

                System.out.println("✅ Order placed successfully! Order ID: " + orderId);

                // Redirect to success page
                response.sendRedirect("orderSuccess.jsp?orderId=" + orderId);

            } else {
                System.err.println("❌ Failed to create order");
                response.sendRedirect("checkout.jsp?error=orderFailed");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid payment mode");
            e.printStackTrace();
            response.sendRedirect("checkout.jsp?error=invalidPayment");
        } catch (Exception e) {
            System.err.println("❌ Error processing checkout");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
