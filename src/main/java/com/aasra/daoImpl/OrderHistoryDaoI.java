package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aasra.dao.OrderHistoryDao;
import com.aasra.model.OrderHistory;

public class OrderHistoryDaoI implements OrderHistoryDao {

    private static Connection connection = null;

    final static String INSERT_QUERY = "INSERT INTO `order_history` (`orderId`, `userId`, `assigned_agent_id`) " +
            "VALUES (?, ?, ?)";

    final static String SELECT_BY_USER = "SELECT * FROM `order_history` WHERE `userId` = ?";

    final static String SELECT_BY_ORDER = "SELECT * FROM `order_history` WHERE `orderId` = ?";

    final static String UPDATE_AGENT = "UPDATE `order_history` SET `assigned_agent_id` = ? " +
            "WHERE `orderHistoryId` = ?";

    final static String DELETE_QUERY = "DELETE FROM `order_history` WHERE `orderHistoryId` = ?";

    // Constructor - Database Connection
    public OrderHistoryDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ OrderHistoryDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. ADD ORDER HISTORY
    // ===================================
    @Override
    public void addOrderHistory(OrderHistory history) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setInt(1, history.getOrderId());
            statement.setInt(2, history.getUserId());
            
            if (history.getAssignedAgentId() != null) {
                statement.setInt(3, history.getAssignedAgentId());
            } else {
                statement.setNull(3, java.sql.Types.INTEGER);
            }

            statement.executeUpdate();

            System.out.println("✅ Order history added: Order ID " + history.getOrderId());

        } catch (SQLException e) {
            System.err.println("❌ Error adding order history");
            e.printStackTrace();
        }
    }

    // ===================================
    // 2. GET ORDER HISTORY BY USER
    // ===================================
    
    public List<OrderHistory> getHistoryByUser(int userId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<OrderHistory> historyList = new ArrayList<OrderHistory>();

        try {
            statement = connection.prepareStatement(SELECT_BY_USER);
            statement.setInt(1, userId);

            rs = statement.executeQuery();

            while (rs.next()) {
                OrderHistory history = extractHistoryFromResultSet(rs);
                historyList.add(history);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching order history for user ID: " + userId);
            e.printStackTrace();
        }

        return historyList;
    }

    // ===================================
    // 3. GET ORDER HISTORY BY ORDER
    // ===================================
    
    public OrderHistory getHistoryByOrder(int orderId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        OrderHistory history = null;

        try {
            statement = connection.prepareStatement(SELECT_BY_ORDER);
            statement.setInt(1, orderId);

            rs = statement.executeQuery();

            if (rs.next()) {
                history = extractHistoryFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching order history for order ID: " + orderId);
            e.printStackTrace();
        }

        return history;
    }

    // ===================================
    // 4. UPDATE ASSIGNED AGENT
    // ===================================
    
    public void updateAssignedAgent(int orderHistoryId, int agentId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_AGENT);

            statement.setInt(1, agentId);
            statement.setInt(2, orderHistoryId);

            statement.executeUpdate();

            System.out.println("✅ Order history updated: Agent ID " + agentId);

        } catch (SQLException e) {
            System.err.println("❌ Error updating order history ID: " + orderHistoryId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 5. DELETE ORDER HISTORY
    // ===================================
    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, orderHistoryId);

            statement.executeUpdate();

            System.out.println("✅ Order history deleted: ID " + orderHistoryId);

        } catch (SQLException e) {
            System.err.println("❌ Error deleting order history ID: " + orderHistoryId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 6. EXTRACT HISTORY FROM RESULTSET
    // ===================================
    private OrderHistory extractHistoryFromResultSet(ResultSet rs) throws SQLException {
        OrderHistory history = new OrderHistory();

        history.setOrderHistoryId(rs.getInt("orderHistoryId"));
        history.setOrderId(rs.getInt("orderId"));
        history.setUserId(rs.getInt("userId"));
        
        int agentId = rs.getInt("assigned_agent_id");
        if (!rs.wasNull()) {
            history.setAssignedAgentId(agentId);
        }

        return history;
    }

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderHistory> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		// TODO Auto-generated method stub
		
	}
}
