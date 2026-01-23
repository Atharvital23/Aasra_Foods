package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aasra.dao.MenuDao;
import com.aasra.model.Menu;

public class MenuDaoI implements MenuDao {

    private static Connection connection = null;

    final static String INSERT_QUERY = "INSERT INTO `menu` (`restaurantId`, `name`, `description`, " +
            "`price`, `ratings`, `imagePath`, `isAvailable`) VALUES (?, ?, ?, ?, ?, ?, ?)";

    final static String SELECT_QUERY = "SELECT * FROM `menu` WHERE `menuId` = ?";

    final static String SELECT_BY_RESTAURANT = "SELECT * FROM `menu` WHERE `restaurantId` = ? AND `isAvailable` = 1";

    final static String SELECT_ALL_QUERY = "SELECT * FROM `menu`";

    final static String UPDATE_QUERY = "UPDATE `menu` SET `restaurantId` = ?, `name` = ?, `description` = ?, " +
            "`price` = ?, `ratings` = ?, `imagePath` = ?, `isAvailable` = ? WHERE `menuId` = ?";

    final static String DELETE_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";

    final static String UPDATE_AVAILABILITY = "UPDATE `menu` SET `isAvailable` = ? WHERE `menuId` = ?";

    // Constructor - Database Connection
    public MenuDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ MenuDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. ADD MENU ITEM
    // ===================================
    @Override
    public void addMenu(Menu menu) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setInt(1, menu.getRestaurantId());
            statement.setString(2, menu.getName());
            statement.setString(3, menu.getDescription());
            statement.setDouble(4, menu.getPrice());
            statement.setDouble(5, menu.getRatings());
            statement.setString(6, menu.getImagePath());
            statement.setBoolean(7, menu.isAvailable());

            statement.executeUpdate();

            System.out.println("✅ Menu item added successfully: " + menu.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error adding menu item: " + menu.getName());
            e.printStackTrace();
        }
    }

    // ===================================
    // 2. GET MENU BY ID
    // ===================================
    @Override
    public Menu getMenu(int menuId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Menu menu = null;

        try {
            statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, menuId);

            rs = statement.executeQuery();

            if (rs.next()) {
                menu = extractMenuFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching menu by ID: " + menuId);
            e.printStackTrace();
        }

        return menu;
    }

    // ===================================
    // 3. GET ALL MENU ITEMS BY RESTAURANT
    // ===================================
    
    public List<Menu> getMenuByRestaurant(int restaurantId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        ArrayList<Menu> menuList = new ArrayList<Menu>();

        try {
            statement = connection.prepareStatement(SELECT_BY_RESTAURANT);
            statement.setInt(1, restaurantId);

            rs = statement.executeQuery();

            while (rs.next()) {
                Menu menu = extractMenuFromResultSet(rs);
                menuList.add(menu);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching menu for restaurant ID: " + restaurantId);
            e.printStackTrace();
        }

        return menuList;
    }

    // ===================================
    // 4. GET ALL MENU ITEMS
    // ===================================
    
    public List<Menu> getAllMenus() {
        Statement statement = null;
        ResultSet rs = null;
        ArrayList<Menu> menuList = new ArrayList<Menu>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_ALL_QUERY);

            while (rs.next()) {
                Menu menu = extractMenuFromResultSet(rs);
                menuList.add(menu);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching all menu items");
            e.printStackTrace();
        }

        return menuList;
    }

    // ===================================
    // 5. UPDATE MENU ITEM
    // ===================================
    @Override
    public void updateMenu(Menu menu) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setInt(1, menu.getRestaurantId());
            statement.setString(2, menu.getName());
            statement.setString(3, menu.getDescription());
            statement.setDouble(4, menu.getPrice());
            statement.setDouble(5, menu.getRatings());
            statement.setString(6, menu.getImagePath());
            statement.setBoolean(7, menu.isAvailable());
            statement.setInt(8, menu.getMenuId());

            statement.executeUpdate();

            System.out.println("✅ Menu item updated successfully: " + menu.getName());

        } catch (SQLException e) {
            System.err.println("❌ Error updating menu item: " + menu.getName());
            e.printStackTrace();
        }
    }

    // ===================================
    // 6. DELETE MENU ITEM
    // ===================================
    @Override
    public void deleteMenu(int menuId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, menuId);

            statement.executeUpdate();

            System.out.println("✅ Menu item deleted successfully: ID " + menuId);

        } catch (SQLException e) {
            System.err.println("❌ Error deleting menu item: ID " + menuId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 7. UPDATE MENU AVAILABILITY
    // ===================================
    public void updateAvailability(int menuId, boolean isAvailable) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_AVAILABILITY);

            statement.setBoolean(1, isAvailable);
            statement.setInt(2, menuId);

            statement.executeUpdate();

            System.out.println("✅ Menu availability updated: ID " + menuId);

        } catch (SQLException e) {
            System.err.println("❌ Error updating menu availability: ID " + menuId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 8. EXTRACT MENU FROM RESULTSET
    // ===================================
    private Menu extractMenuFromResultSet(ResultSet rs) throws SQLException {
        Menu menu = new Menu();

        menu.setMenuId(rs.getInt("menuId"));
        menu.setRestaurantId(rs.getInt("restaurantId"));
        menu.setName(rs.getString("name"));
        menu.setDescription(rs.getString("description"));
        menu.setPrice(rs.getDouble("price"));
        menu.setRatings(rs.getDouble("ratings"));
        menu.setImagePath(rs.getString("imagePath"));
        menu.setAvailable(rs.getBoolean("isAvailable"));

        return menu;
    }

	@Override
	public List<Menu> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}
}
