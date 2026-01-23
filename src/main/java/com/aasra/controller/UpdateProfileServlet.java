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

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

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
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phoneNoStr = request.getParameter("phoneNo");
            String address = request.getParameter("address");

            // Validate input
            if (name == null || email == null || phoneNoStr == null || address == null ||
                name.isEmpty() || email.isEmpty() || phoneNoStr.isEmpty() || address.isEmpty()) {

                System.err.println("❌ Update failed: Missing fields");
                response.sendRedirect("profile.jsp?error=missingFields");
                return;
            }

            long phoneNo = Long.parseLong(phoneNoStr);

            System.out.println("✏️ Updating profile for user: " + loggedInUser.getUserName());

            // Update user object
            loggedInUser.setName(name);
            loggedInUser.setEmail(email);
            loggedInUser.setPhoneNo(phoneNo);
            loggedInUser.setAddress(address);

            // Update in database
            UserDaoI userDao = new UserDaoI();
            boolean success = userDao.updateUserProfile(loggedInUser);

            if (success) {
                // Update session with new data
                session.setAttribute("loggedInUser", loggedInUser);

                System.out.println("✅ Profile updated successfully for: " + loggedInUser.getUserName());
                response.sendRedirect("profile.jsp?success=updated");
            } else {
                System.err.println("❌ Profile update failed");
                response.sendRedirect("profile.jsp?error=updateFailed");
            }

        } catch (NumberFormatException e) {
            System.err.println("❌ Invalid phone number format");
            e.printStackTrace();
            response.sendRedirect("profile.jsp?error=invalidPhone");
        } catch (Exception e) {
            System.err.println("❌ Error in UpdateProfileServlet");
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("profile.jsp");
    }
}
