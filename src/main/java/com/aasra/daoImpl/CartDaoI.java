package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aasra.dao.CartDao;
import com.aasra.model.Cart;
import com.aasra.model.CartItem;

/*
 * this is `implementation` class for the DAO implementation in java,
 * and implements the implements CartDao.
 */
public class CartDaoI implements CartDao {
	
	/*
	 * this is Connection variable created for the Private 
	 * that which no other class use this same same 
	 * of an connection class which is created in this class 
	 * that is CartDaoI
	 */
    private static Connection connection = null;

    /*
     * this INSERT_QUERY variable use for the insert the cart_items 
     * in to the `cart_items` table that i take it as a final when 
     * ever the class is extend to another class the variable should
     * not be used in the child class to avoid the hacking procedure
     */
    final static String INSERT_QUERY = "INSERT INTO `cart_items` (`userId`, `menuId`, `restaurantId`, `quantity`) " +
            "VALUES (?, ?, ?, ?)";

    /*
     * this SELECT_BY_USER variable use for the getting the cart_items,
     * menu, and restaurant from the joining the tables for getting the
     * `cart_items.cartItemId`,`cart_items.userId`, `cart_items.menuId`, 
     * `cart_items.restaurantId`,`cart_items.quantity`, `menu.name`, 
     * `menu.price`, `menu.imagePath`, and `restaurant.name` 
     * to get the same output i take it as a final when 
     * ever the class is extend to another class the variable should
     * not be used in the child class to avoid the hacking procedure
     */
    final static String SELECT_BY_USER = "SELECT ci.*, m.name AS itemName, m.price, m.imagePath, r.name AS restaurantName " +
            "FROM `cart_items` ci " +
            "JOIN `menu` m ON ci.menuId = m.menuId " +
            "JOIN `restaurant` r ON ci.restaurantId = r.restaurantId " +
            "WHERE ci.userId = ?";

    /*
     * this UPDATE_QUANTITY variable use for the updating 
     */
    final static String UPDATE_QUANTITY = "UPDATE `cart_items` SET `quantity` = ? WHERE `cartItemId` = ?";

    final static String DELETE_ITEM = "DELETE FROM `cart_items` WHERE `cartItemId` = ?";

    final static String DELETE_ALL_BY_USER = "DELETE FROM `cart_items` WHERE `userId` = ?";

    final static String CHECK_EXISTING = "SELECT * FROM `cart_items` WHERE `userId` = ? AND `menuId` = ?";

    // Constructor - Database Connection
    public CartDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ CartDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. ADD ITEM TO CART
    // ===================================
    @Override
    public void addToCart(CartItem item) {
        PreparedStatement statement = null;

        try {
            // Check if item already exists in cart
            statement = connection.prepareStatement(CHECK_EXISTING);
            statement.setInt(1, item.getUserId());
            statement.setInt(2, item.getMenuId());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Item exists, update quantity
                int existingQty = rs.getInt("quantity");
                int newQty = existingQty + item.getQuantity();
                int cartItemId = rs.getInt("cartItemId");

                updateQuantity(cartItemId, newQty);

            } else {
                // New item, insert
                statement = connection.prepareStatement(INSERT_QUERY);

                statement.setInt(1, item.getUserId());
                statement.setInt(2, item.getMenuId());
                statement.setInt(3, item.getRestaurantId());
                statement.setInt(4, item.getQuantity());

                statement.executeUpdate();

                System.out.println("✅ Item added to cart: " + item.getItemName());
            }

        } catch (SQLException e) {
            System.err.println("❌ Error adding item to cart");
            e.printStackTrace();
        }
    }

    // ===================================
    // 2. GET CART ITEMS BY USER
    // ===================================
    @Override
    public List<CartItem> getCartByUser(int userId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<CartItem> cartItems = new ArrayList<CartItem>();

        try {
            statement = connection.prepareStatement(SELECT_BY_USER);
            statement.setInt(1, userId);

            rs = statement.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem();

                item.setCartItemId(rs.getInt("cartItemId"));
                item.setUserId(rs.getInt("userId"));
                item.setMenuId(rs.getInt("menuId"));
                item.setRestaurantId(rs.getInt("restaurantId"));
                item.setQuantity(rs.getInt("quantity"));
                item.setItemName(rs.getString("itemName"));
                item.setPrice(rs.getDouble("price"));
                item.setImagePath(rs.getString("imagePath"));
                item.setRestaurantName(rs.getString("restaurantName"));

                cartItems.add(item);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching cart for user ID: " + userId);
            e.printStackTrace();
        }

        return cartItems;
    }

    // ===================================
    // 3. UPDATE CART ITEM QUANTITY
    // ===================================
    @Override
    public void updateQuantity(int cartItemId, int quantity) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_QUANTITY);

            statement.setInt(1, quantity);
            statement.setInt(2, cartItemId);

            statement.executeUpdate();

            System.out.println("✅ Cart item quantity updated: ID " + cartItemId);

        } catch (SQLException e) {
            System.err.println("❌ Error updating cart item quantity: ID " + cartItemId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 4. REMOVE ITEM FROM CART
    // ===================================
    @Override
    public void removeFromCart(int cartItemId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_ITEM);
            statement.setInt(1, cartItemId);

            statement.executeUpdate();

            System.out.println("✅ Item removed from cart: ID " + cartItemId);

        } catch (SQLException e) {
            System.err.println("❌ Error removing item from cart: ID " + cartItemId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 5. CLEAR ENTIRE CART
    // ===================================
    @Override
    public void clearCart(int userId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_ALL_BY_USER);
            statement.setInt(1, userId);

            statement.executeUpdate();

            System.out.println("✅ Cart cleared for user ID: " + userId);

        } catch (SQLException e) {
            System.err.println("❌ Error clearing cart for user ID: " + userId);
            e.printStackTrace();
        }
    }


}
