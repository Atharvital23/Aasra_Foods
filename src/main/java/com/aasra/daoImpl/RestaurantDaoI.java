package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aasra.dao.RestaurantDao;
import com.aasra.model.Restaurant;

public class RestaurantDaoI implements RestaurantDao {

    private static Connection connection = null;
    
    final static String INSERT_QUERY = "INSERT INTO `restaurant`( `name`, " +
            "`imagePath`, `ratings`, `eta`, `cuisineType`,`Address`,`isActive`,`restaurantOwnerId`) VALUES(?,?,?,?,?,?,?,?)";

    final static String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId` = ?";

    final static String SELECT_ALL_QUERY = "SELECT * FROM `restaurant` WHERE `isActive` = 1";

    final static String SELECT_BY_OWNER = "SELECT * FROM `restaurant` WHERE `restaurantOwnerId` = ?";

    final static String UPDATE_QUERY = "UPDATE `restaurant` SET `name` = ?, `imagePath` = ?, `ratings` = ?, " +
            "`eta` = ?, `cuisineType` = ? , `Address` = ?, `isActive` = ?, `restaurantOwnerId` = ? WHERE `restaurantId` = ?";

    final static String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";

    final static String UPDATE_RATING = "UPDATE `restaurant` SET `ratings` = ? WHERE `restaurantId` = ?";

    final static String UPDATE_STATUS = "UPDATE `restaurant` SET `isActive` = ? WHERE `restaurantId` = ?";

    final static String SEARCH_QUERY = "SELECT * FROM `restaurant` WHERE `isActive` = 1 AND " +
            "(`name` LIKE ? OR `cuisineType` LIKE ?)";

    final static String GET_RESTAURANT_ID_BY_OWNER = "SELECT restaurantId FROM restaurant WHERE restaurantOwnerId = ? LIMIT 1";

    // Constructor - Database Connection
    public RestaurantDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ RestaurantDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. ADD RESTAURANT
    // ===================================
    @Override
    public void addRestaurant(Restaurant restaurant) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getImagePath());
            statement.setDouble(3, restaurant.getRatings());
            statement.setInt(4, restaurant.getEta());
            statement.setString(5, restaurant.getCuisineType());
            statement.setString(6, restaurant.getAddress());
            statement.setBoolean(7, restaurant.isActive());
            statement.setInt(8, restaurant.getRestaurantOwnerId());

            statement.executeUpdate();

            System.out.println("✅ Restaurant added successfully: " + restaurant.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error adding restaurant: " + restaurant.getName());
            e.printStackTrace();
        }
    }

    // ===================================
    // 2. GET RESTAURANT BY ID
    // ===================================
    @Override
    public Restaurant getRestaurant(int restaurantId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Restaurant restaurant = null;

        try {
            statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, restaurantId);

            rs = statement.executeQuery();

            if (rs.next()) {
                restaurant = extractRestaurantFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching restaurant by ID: " + restaurantId);
            e.printStackTrace();
        }

        return restaurant;
    }

    // ===================================
    // 3. GET ALL ACTIVE RESTAURANTS
    // ===================================
    @Override
    public List<Restaurant> getAll() {
        Statement statement = null;
        ResultSet res = null;
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

        try {
            statement = connection.createStatement();
            res = statement.executeQuery(SELECT_ALL_QUERY);

            while (res.next()) {
                Restaurant restaurant = extractRestaurantFromResultSet(res);
                restaurantList.add(restaurant);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching all restaurants");
            e.printStackTrace();
        }

        return restaurantList;
    }

    // ===================================
    // 4. GET RESTAURANTS BY OWNER ID
    // ===================================
    public List<Restaurant> getRestaurantsByOwnerId(int ownerId) {
        PreparedStatement statement = null;
        ResultSet res = null;
        List<Restaurant> list = new ArrayList<>();

        try {
            statement = connection.prepareStatement(SELECT_BY_OWNER);
            statement.setInt(1, ownerId);

            res = statement.executeQuery();

            while (res.next()) {
                Restaurant r = extractRestaurantFromResultSet(res);
                list.add(r);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching restaurants for owner ID: " + ownerId);
            e.printStackTrace();
        }

        return list;
    }

    // ===================================
    // 5. GET RESTAURANT ID BY OWNER ID
    // ===================================
    public int getRestaurantIdByOwnerId(int ownerId) {
        PreparedStatement statement = null;
        ResultSet res = null;

        try {
            statement = connection.prepareStatement(GET_RESTAURANT_ID_BY_OWNER);
            statement.setInt(1, ownerId);

            res = statement.executeQuery();

            if (res.next()) {
                int restaurantId = res.getInt("restaurantId");
                System.out.println("✅ Restaurant found for owner ID: " + ownerId + " → Restaurant ID: " + restaurantId);
                return restaurantId;
            } else {
                System.out.println("⚠️ No restaurant found for owner ID: " + ownerId);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching restaurant ID for owner: " + ownerId);
            e.printStackTrace();
        }

        return 0; // Return 0 if not found
    }

    // ===================================
    // 6. UPDATE RESTAURANT
    // ===================================
    @Override
    public void updateRestaurant(Restaurant restaurant) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getImagePath());
            statement.setDouble(3, restaurant.getRatings());
            statement.setInt(4, restaurant.getEta());
            statement.setString(5, restaurant.getCuisineType());
            statement.setString(6, restaurant.getAddress());
            statement.setBoolean(7, restaurant.isActive());
            statement.setInt(8, restaurant.getRestaurantOwnerId());
            statement.setInt(9, restaurant.getRestaurantId());

            statement.executeUpdate();

            System.out.println("✅ Restaurant updated successfully: " + restaurant.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error updating restaurant: " + restaurant.getName());
            e.printStackTrace();
        }
    }

    // ===================================
    // 7. DELETE RESTAURANT
    // ===================================
    @Override
    public void deleteRestaurant(int restaurantId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, restaurantId);

            statement.executeUpdate();

            System.out.println("✅ Restaurant deleted successfully: ID " + restaurantId);

        } catch (SQLException e) {
            System.err.println("❌ Error deleting restaurant ID: " + restaurantId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 8. UPDATE RESTAURANT RATING
    // ===================================
    public void updateRestaurantRating(int restaurantId, double newRating, String comments) {
        PreparedStatement selectStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            // Get current rating
            selectStmt = connection.prepareStatement("SELECT ratings FROM restaurant WHERE restaurantId = ?");
            selectStmt.setInt(1, restaurantId);
            rs = selectStmt.executeQuery();

            double currentRating = 0.0;
            if (rs.next()) {
                currentRating = rs.getDouble("ratings");
            }

            // Calculate average (simple approach)
            double averageRating = (currentRating + newRating) / 2;

            // Update restaurant rating
            updateStmt = connection.prepareStatement(UPDATE_RATING);
            updateStmt.setDouble(1, averageRating);
            updateStmt.setInt(2, restaurantId);
            updateStmt.executeUpdate();

            System.out.println("✅ Restaurant rated successfully! New rating: " + averageRating);

        } catch (SQLException e) {
            System.err.println("❌ Error updating restaurant rating for ID: " + restaurantId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 9. UPDATE RESTAURANT STATUS
    // ===================================
    public void updateRestaurantStatus(int restaurantId, boolean isActive) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_STATUS);

            statement.setBoolean(1, isActive);
            statement.setInt(2, restaurantId);

            statement.executeUpdate();

            System.out.println("✅ Restaurant status updated: ID " + restaurantId + 
                               " → " + (isActive ? "ACTIVE" : "INACTIVE"));

        } catch (SQLException e) {
            System.err.println("❌ Error updating restaurant status for ID: " + restaurantId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 10. SEARCH RESTAURANTS
    // ===================================
    public List<Restaurant> searchRestaurants(String keyword) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

        try {
            statement = connection.prepareStatement(SEARCH_QUERY);
            String searchPattern = "%" + keyword + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);

            rs = statement.executeQuery();

            while (rs.next()) {
                Restaurant restaurant = extractRestaurantFromResultSet(rs);
                restaurantList.add(restaurant);
            }

            System.out.println("✅ Search completed: Found " + restaurantList.size() + 
                               " restaurants for keyword: " + keyword);

        } catch (SQLException e) {
            System.err.println("❌ Error searching restaurants with keyword: " + keyword);
            e.printStackTrace();
        }

        return restaurantList;
    }

    // ===================================
    // 11. EXTRACT RESTAURANT FROM RESULTSET
    // ===================================
    private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId(rs.getInt("restaurantId"));
        restaurant.setName(rs.getString("name"));
        restaurant.setImagePath(rs.getString("imagePath"));
        restaurant.setRatings(rs.getDouble("ratings"));
        restaurant.setEta(rs.getInt("eta"));
        restaurant.setCuisineType(rs.getString("cuisineType"));
        restaurant.setAddress(rs.getString("Address"));
        restaurant.setActive(rs.getBoolean("isActive"));
        restaurant.setRestaurantOwnerId(rs.getInt("restaurantOwnerId"));

        return restaurant;
    }
}
