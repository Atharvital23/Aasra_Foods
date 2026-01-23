package com.aasra.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aasra.model.User;

@WebServlet("/help")
public class HelpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("loggedInUser");

        // If user is logged in, fetch their complaints
        if (currentUser != null) {
            try {
                List<Map<String, Object>> myComplaints = getUserComplaints(currentUser.getUserId());
                request.setAttribute("myComplaints", myComplaints);
                
                System.out.println("✅ Fetched " + myComplaints.size() + " complaints for user: " + currentUser.getName());
                
            } catch (Exception e) {
                System.err.println("❌ Error fetching complaints");
                e.printStackTrace();
            }
        }

        // Forward to help.jsp
        request.getRequestDispatcher("help.jsp").forward(request, response);
    }

    /**
     * Fetch all complaints for a specific user
     */
    private List<Map<String, Object>> getUserComplaints(int userId) throws Exception {
        List<Map<String, Object>> complaints = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String dbUsername = "root";
        String dbPassword = "Atharvital@123"; // ⚠️ UPDATE THIS

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword)) {

            String sql = "SELECT * FROM complaints WHERE userId = ? ORDER BY createdAt DESC";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Map<String, Object> complaint = new HashMap<>();
                        complaint.put("complaintId", rs.getInt("complaintId"));
                        complaint.put("name", rs.getString("name"));
                        complaint.put("email", rs.getString("email"));
                        complaint.put("phone", rs.getString("phone"));
                        complaint.put("complaintType", rs.getString("complaintType"));
                        complaint.put("orderId", rs.getObject("orderId"));
                        complaint.put("subject", rs.getString("subject"));
                        complaint.put("description", rs.getString("description"));
                        complaint.put("status", rs.getString("status"));
                        complaint.put("createdAt", rs.getTimestamp("createdAt"));
                        complaint.put("resolvedAt", rs.getTimestamp("resolvedAt"));

                        complaints.add(complaint);
                    }
                }
            }
        }

        return complaints;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
