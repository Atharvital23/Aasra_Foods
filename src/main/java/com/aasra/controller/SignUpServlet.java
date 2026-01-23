package com.aasra.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aasra.daoImpl.UserDaoI;
import com.aasra.model.User;
import com.aasra.model.User.Role;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get form data
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phoneNoStr = request.getParameter("phoneNo");
            String address = request.getParameter("address");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            // Validate input
            if (name == null || email == null || phoneNoStr == null || address == null ||
                userName == null || password == null || confirmPassword == null ||
                name.isEmpty() || email.isEmpty() || phoneNoStr.isEmpty() || address.isEmpty() ||
                userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                System.err.println("❌ Registration failed: Missing fields");
                response.sendRedirect("SignUp.jsp?error=missingFields");
                return;
            }

            // Validate password match
            if (!password.equals(confirmPassword)) {
                System.err.println("❌ Registration failed: Passwords don't match");
                response.sendRedirect("SignUp.jsp?error=passwordMismatch");
                return;
            }

            long phoneNo = Long.parseLong(phoneNoStr);

            System.out.println("📝 Registration attempt: " + userName);

            UserDaoI userDao = new UserDaoI();

            // Check if username exists
            if (userDao.isUsernameExists(userName)) {
                System.err.println("❌ Registration failed: Username already exists - " + userName);
                response.sendRedirect("SignUp.jsp?error=usernameExists");
                return;
            }

            // Check if email exists
            if (userDao.isEmailExists(email)) {
                System.err.println("❌ Registration failed: Email already exists - " + email);
                response.sendRedirect("SignUp.jsp?error=emailExists");
                return;
            }

            // Create user object
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNo(phoneNo);
            user.setAddress(address);
            user.setUserName(userName);
            user.setPassword(password); // Hash in production
            user.setRole(Role.Customer);

            // Register user
            boolean success = userDao.registerUser(user);

            if (success) {
                System.out.println("✅ Registration successful: " + userName);
                response.sendRedirect("Login.jsp?success=registered");
            } else {
                System.err.println("❌ Registration failed: Database error");
                response.sendRedirect("SignUp.jsp?error=registrationFailed");
            }

        } catch (NumberFormatException e) {
            System.err.println("❌ Registration failed: Invalid phone number format");
            e.printStackTrace();
            response.sendRedirect("SignUp.jsp?error=invalidPhone");
        } catch (Exception e) {
            System.err.println("❌ Error in SignUpServlet");
            e.printStackTrace();
            response.sendRedirect("SignUp.jsp?error=systemError");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("SignUp.jsp");
    }
}
