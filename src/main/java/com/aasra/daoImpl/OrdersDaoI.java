package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aasra.dao.OrdersDao;
import com.aasra.model.Orders;
import com.aasra.model.Orders.OrderStatus;
import com.aasra.model.Orders.PaymentMode;

public class OrdersDaoI implements OrdersDao {

    private static Connection connection = null;

    final static String INSERT_QUERY = "INSERT INTO `orders` (`restaurantId`, `userId`, `totalAmount`, " +
            "`modeOfPayment`, `status`, `deliveryAgentId`) VALUES (?, ?, ?, ?, ?, ?)";

    final static String SELECT_QUERY = "SELECT o.*, r.name AS restaurantName, u.name AS customerName, " +
            "d.name AS deliveryAgentName FROM `orders` o " +
            "JOIN `restaurant` r ON o.restaurantId = r.restaurantId " +
            "JOIN `user` u ON o.userId = u.userId " +
            "LEFT JOIN `user` d ON o.deliveryAgentId = d.userId " +
            "WHERE o.orderId = ?";

    final static String SELECT_BY_USER = "SELECT o.*, r.name AS restaurantName, r.imagePath, " +
            "d.name AS deliveryAgentName, d.phoneNo AS agentPhone FROM `orders` o " +
            "JOIN `restaurant` r ON o.restaurantId = r.restaurantId " +
            "LEFT JOIN `user` d ON o.deliveryAgentId = d.userId " +
            "WHERE o.userId = ? ORDER BY o.orderDate DESC";

    final static String SELECT_BY_RESTAURANT = "SELECT o.*, u.name AS customerName, u.address, u.phoneNo, " +
            "d.name AS deliveryAgentName FROM `orders` o " +
            "JOIN `user` u ON o.userId = u.userId " +
            "LEFT JOIN `user` d ON o.deliveryAgentId = d.userId " +
            "WHERE o.restaurantId = ? ORDER BY o.orderDate DESC";

    final static String UPDATE_STATUS = "UPDATE `orders` SET `status` = ? WHERE `orderId` = ?";

    final static String UPDATE_DELIVERY_AGENT = "UPDATE `orders` SET `deliveryAgentId` = ? WHERE `orderId` = ?";

    final static String DELETE_QUERY = "DELETE FROM `orders` WHERE `orderId` = ?";

    final static String SELECT_ALL_QUERY = "SELECT o.*, r.name AS restaurantName, u.name AS customerName " +
            "FROM `orders` o " +
            "JOIN `restaurant` r ON o.restaurantId = r.restaurantId " +
            "JOIN `user` u ON o.userId = u.userId " +
            "ORDER BY o.orderDate DESC";

    // Constructor - Database Connection
    public OrdersDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ OrdersDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. CREATE ORDER
    // ===================================
    
    public int createOrder(Orders order) {
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        int orderId = 0;

        try {
            statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getRestaurantId());
            statement.setInt(2, order.getUserId());
            statement.setDouble(3, order.getTotalAmount());
            statement.setString(4, order.getModeOfPayment().toString());
            statement.setString(5, order.getStatus().toString());
            
            // deliveryAgentId can be null initially
            if (order.getDeliveryAgentId() != null) {
                statement.setInt(6, order.getDeliveryAgentId());
            } else {
                statement.setNull(6, java.sql.Types.INTEGER);
            }

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                    System.out.println("✅ Order created successfully! Order ID: " + orderId);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error creating order");
            e.printStackTrace();
        }

        return orderId;
    }

    // ===================================
    // 2. GET ORDER BY ID
    // ===================================
    
    public Orders getOrder(int orderId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Orders order = null;

        try {
            statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, orderId);

            rs = statement.executeQuery();

            if (rs.next()) {
                order = extractOrderFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching order by ID: " + orderId);
            e.printStackTrace();
        }

        return order;
    }

    // ===================================
    // 3. GET ORDERS BY USER ID
    // ===================================
    
    public List<Orders> getOrdersByUser(int userId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Orders> orderList = new ArrayList<Orders>();

        try {
            statement = connection.prepareStatement(SELECT_BY_USER);
            statement.setInt(1, userId);

            rs = statement.executeQuery();

            while (rs.next()) {
                Orders order = extractOrderFromResultSet(rs);
                orderList.add(order);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching orders for user ID: " + userId);
            e.printStackTrace();
        }

        return orderList;
    }

    // ===================================
    // 4. GET ORDERS BY RESTAURANT ID
    // ===================================
    
    public List<Orders> getOrdersByRestaurant(int restaurantId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Orders> orderList = new ArrayList<Orders>();

        try {
            statement = connection.prepareStatement(SELECT_BY_RESTAURANT);
            statement.setInt(1, restaurantId);

            rs = statement.executeQuery();

            while (rs.next()) {
                Orders order = extractOrderFromResultSet(rs);
                orderList.add(order);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching orders for restaurant ID: " + restaurantId);
            e.printStackTrace();
        }

        return orderList;
    }

    // ===================================
    // 5. GET ALL ORDERS
    // ===================================
    
    public List<Orders> getAllOrders() {
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<Orders> orderList = new ArrayList<Orders>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL_QUERY);

            while (rs.next()) {
                Orders order = extractOrderFromResultSet(rs);
                orderList.add(order);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching all orders");
            e.printStackTrace();
        }

        return orderList;
    }

    // ===================================
    // 6. UPDATE ORDER STATUS
    // ===================================
    
    public void updateOrderStatus(int orderId, OrderStatus status) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_STATUS);

            statement.setString(1, status.toString());
            statement.setInt(2, orderId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Order status updated: Order ID " + orderId + " → " + status);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error updating order status for order ID: " + orderId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 7. ASSIGN DELIVERY AGENT
    // ===================================
    
    public void assignDeliveryAgent(int orderId, int deliveryAgentId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_DELIVERY_AGENT);

            statement.setInt(1, deliveryAgentId);
            statement.setInt(2, orderId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Delivery agent assigned: Order ID " + orderId + 
                                   " → Agent ID " + deliveryAgentId);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error assigning delivery agent for order ID: " + orderId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 8. CANCEL ORDER
    // ===================================
    
    public boolean cancelOrder(int orderId) {
        PreparedStatement statement = null;

        try {
            // First check if order can be cancelled (not dispatched/delivered)
            Orders order = getOrder(orderId);
            
            if (order != null && (order.getStatus() == OrderStatus.CONFIRMED)) {
                statement = connection.prepareStatement(UPDATE_STATUS);
                statement.setString(1, OrderStatus.CANCELLED.toString());
                statement.setInt(2, orderId);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("✅ Order cancelled successfully: Order ID " + orderId);
                    return true;
                }
            } else {
                System.out.println("⚠️ Cannot cancel order. Current status: " + 
                                   (order != null ? order.getStatus() : "Order not found"));
            }

        } catch (SQLException e) {
            System.err.println("❌ Error cancelling order ID: " + orderId);
            e.printStackTrace();
        }

        return false;
    }

    // ===================================
    // 9. DELETE ORDER
    // ===================================
    
    public void deleteOrder(int orderId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, orderId);

            statement.executeUpdate();

            System.out.println("✅ Order deleted successfully: ID " + orderId);

        } catch (SQLException e) {
            System.err.println("❌ Error deleting order ID: " + orderId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 10. EXTRACT ORDER FROM RESULTSET
    // ===================================
    private Orders extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Orders order = new Orders();

        order.setOrderId(rs.getInt("orderId"));
        order.setRestaurantId(rs.getInt("restaurantId"));
        order.setUserId(rs.getInt("userId"));
        order.setTotalAmount(rs.getDouble("totalAmount"));
        order.setModeOfPayment(PaymentMode.valueOf(rs.getString("modeOfPayment")));
        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        order.setOrderDate(rs.getTimestamp("orderDate"));
        
        // deliveryAgentId can be null
        int agentId = rs.getInt("deliveryAgentId");
        if (!rs.wasNull()) {
            order.setDeliveryAgentId(agentId);
        }

        // Additional display fields (if available in query)
        try {
            order.setRestaurantName(rs.getString("restaurantName"));
        } catch (SQLException e) { /* Field not in query */ }

        try {
            order.setCustomerName(rs.getString("customerName"));
        } catch (SQLException e) { /* Field not in query */ }

        try {
            order.setDeliveryAgentName(rs.getString("deliveryAgentName"));
        } catch (SQLException e) { /* Field not in query */ }

        return order;
    }

	@Override
	public int addOrders(Orders orders) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Orders getOrders(int ordersId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orders> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrders(Orders orders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrders(int ordersId) {
		// TODO Auto-generated method stub
		
	}
}
