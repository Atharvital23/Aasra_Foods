package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aasra.dao.OrderItemsDao;
import com.aasra.model.OrderItems;

public class OrderItemsDaoI implements OrderItemsDao {

    private static Connection connection = null;

    final static String INSERT_QUERY = "INSERT INTO `order_items` (`orderId`, `menuId`, `quantity`, " +
            "`price`, `ratings`, `comments`) VALUES (?, ?, ?, ?, ?, ?)";

    final static String SELECT_BY_ORDER = "SELECT oi.*, m.name AS itemName, m.imagePath FROM `order_items` oi " +
            "JOIN `menu` m ON oi.menuId = m.menuId WHERE oi.orderId = ?";

    final static String UPDATE_RATING = "UPDATE `order_items` SET `ratings` = ?, `comments` = ? " +
            "WHERE `orderItemsId` = ?";

    final static String DELETE_QUERY = "DELETE FROM `order_items` WHERE `orderItemsId` = ?";

    final static String DELETE_BY_ORDER = "DELETE FROM `order_items` WHERE `orderId` = ?";

    // Constructor - Database Connection
    public OrderItemsDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ OrderItemsDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. ADD ORDER ITEM
    // ===================================
    
    public void addOrderItem(OrderItems item) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setInt(1, item.getOrderId());
            statement.setInt(2, item.getMenuId());
            statement.setInt(3, item.getQuantity());
            statement.setDouble(4, item.getPrice());
            statement.setDouble(5, item.getRatings());
            statement.setString(6, item.getComments());

            statement.executeUpdate();

            System.out.println("✅ Order item added: " + item.getItemName());

        } catch (SQLException e) {
            System.err.println("❌ Error adding order item");
            e.printStackTrace();
        }
    }

    // ===================================
    // 2. GET ORDER ITEMS BY ORDER ID
    // ===================================
    
    public List<OrderItems> getOrderItemsByOrder(int orderId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<OrderItems> itemList = new ArrayList<OrderItems>();

        try {
            statement = connection.prepareStatement(SELECT_BY_ORDER);
            statement.setInt(1, orderId);

            rs = statement.executeQuery();

            while (rs.next()) {
                OrderItems item = extractOrderItemFromResultSet(rs);
                itemList.add(item);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching order items for order ID: " + orderId);
            e.printStackTrace();
        }

        return itemList;
    }

    // ===================================
    // 3. UPDATE ORDER ITEM RATING
    // ===================================
    
    public void updateRating(int orderItemsId, double rating, String comments) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_RATING);

            statement.setDouble(1, rating);
            statement.setString(2, comments);
            statement.setInt(3, orderItemsId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Order item rated successfully: ID " + orderItemsId);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error updating rating for order item ID: " + orderItemsId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 4. DELETE ORDER ITEM
    // ===================================
    
    public void deleteOrderItem(int orderItemsId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, orderItemsId);

            statement.executeUpdate();

            System.out.println("✅ Order item deleted: ID " + orderItemsId);

        } catch (SQLException e) {
            System.err.println("❌ Error deleting order item ID: " + orderItemsId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 5. DELETE ALL ORDER ITEMS BY ORDER
    // ===================================
    
    public void deleteOrderItemsByOrder(int orderId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_BY_ORDER);
            statement.setInt(1, orderId);

            statement.executeUpdate();

            System.out.println("✅ All order items deleted for order ID: " + orderId);

        } catch (SQLException e) {
            System.err.println("❌ Error deleting order items for order ID: " + orderId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 6. EXTRACT ORDER ITEM FROM RESULTSET
    // ===================================
    private OrderItems extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
        OrderItems item = new OrderItems();

        item.setOrderItemsId(rs.getInt("orderItemsId"));
        item.setOrderId(rs.getInt("orderId"));
        item.setMenuId(rs.getInt("menuId"));
        item.setQuantity(rs.getInt("quantity"));
        item.setPrice(rs.getDouble("price"));
        item.setRatings(rs.getDouble("ratings"));
        item.setComments(rs.getString("comments"));

        // Additional display fields
        try {
            item.setItemName(rs.getString("itemName"));
        } catch (SQLException e) { /* Field not in query */ }

        try {
            item.setImagePath(rs.getString("imagePath"));
        } catch (SQLException e) { /* Field not in query */ }

        return item;
    }

	@Override
	public void addOrderItems(OrderItems orderItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderItems getOrderItems(int orderItemsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItems> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOrderItems(OrderItems orderItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrderItems(int orderItemsId) {
		// TODO Auto-generated method stub
		
	}
}
