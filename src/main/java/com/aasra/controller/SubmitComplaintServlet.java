package com.aasra.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.model.User;

@WebServlet("/submitComplaint")
public class SubmitComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("loggedInUser");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Get form data
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String complaintType = request.getParameter("complaintType");
            String orderIdStr = request.getParameter("orderId");
            String subject = request.getParameter("subject");
            String description = request.getParameter("description");

            // Validate inputs
            if (name == null || email == null || phone == null || 
                complaintType == null || subject == null || description == null ||
                name.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty() || 
                complaintType.trim().isEmpty() || subject.trim().isEmpty() || description.trim().isEmpty()) {

                System.err.println("❌ Missing required fields in complaint form");
                response.sendRedirect("help.jsp?error=missingFields");
                return;
            }

            Integer orderId = null;
            if (orderIdStr != null && !orderIdStr.trim().isEmpty()) {
                try {
                    orderId = Integer.parseInt(orderIdStr);
                } catch (NumberFormatException e) {
                    System.err.println("⚠️ Invalid order ID format: " + orderIdStr);
                }
            }

            Integer userId = (currentUser != null) ? currentUser.getUserId() : null;

            System.out.println("📝 Processing complaint from: " + name);
            System.out.println("   Type: " + complaintType);
            System.out.println("   Subject: " + subject);

            
            String url = "jdbc:mysql://localhost:3306/aasra_foods";
            String username = "root";
            String password = "Atharvital@123"; // Change to your MySQL password

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("✅ UserDao - Database connected!");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ MySQL Driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Database connection failed!");
                e.printStackTrace();
            }

            System.out.println("✅ Database connected successfully");

            String sql = "INSERT INTO complaints (userId, name, email, phone, complaintType, orderId, subject, description, status, createdAt) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'PENDING', ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, userId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, complaintType);
            pstmt.setObject(6, orderId);
            pstmt.setString(7, subject);
            pstmt.setString(8, description);
            pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Complaint submitted successfully! Rows inserted: " + rows);
                response.sendRedirect("help.jsp?success=submitted");
            } else {
                System.err.println("❌ Failed to insert complaint - no rows affected");
                response.sendRedirect("help.jsp?error=failed");
            }

        } catch (java.sql.SQLException e) {
            System.err.println("❌ SQL Error in SubmitComplaintServlet");
            System.err.println("   Error Code: " + e.getErrorCode());
            System.err.println("   SQL State: " + e.getSQLState());
            System.err.println("   Message: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("help.jsp?error=failed");
        } catch (Exception e) {
            System.err.println("❌ Unexpected error in SubmitComplaintServlet");
            e.printStackTrace();
            response.sendRedirect("help.jsp?error=failed");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("help.jsp");
    }
}
