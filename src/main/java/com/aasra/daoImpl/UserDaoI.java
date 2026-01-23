package com.aasra.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.aasra.dao.UserDao;
import com.aasra.model.User;
import com.aasra.model.User.Role;

public class UserDaoI implements UserDao {

    private static Connection connection = null;

    final static String INSERT_QUERY = "INSERT INTO `user` (`name`, `email`, `phoneNo`, `address`, " +
            "`userName`, `password`, `role`, `verificationStatus`) VALUES (?, ?, ?, ?, ?, ?, ?, 'PENDING')";

    final static String SELECT_BY_USERNAME_PASSWORD = "SELECT * FROM `user` WHERE `userName` = ? AND `password` = ?";

    final static String SELECT_BY_ID = "SELECT * FROM `user` WHERE `userId` = ?";

    final static String SELECT_BY_USERNAME = "SELECT * FROM `user` WHERE `userName` = ?";

    final static String SELECT_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";

    final static String UPDATE_PROFILE = "UPDATE `user` SET `name` = ?, `email` = ?, `phoneNo` = ?, " +
            "`address` = ? WHERE `userId` = ?";

    final static String UPDATE_PASSWORD = "UPDATE `user` SET `password` = ? WHERE `userId` = ?";

    final static String UPDATE_LAST_LOGIN = "UPDATE `user` SET `lastLogin` = NOW() WHERE `userId` = ?";

    final static String CHECK_USERNAME = "SELECT COUNT(*) FROM `user` WHERE `userName` = ?";

    final static String CHECK_EMAIL = "SELECT COUNT(*) FROM `user` WHERE `email` = ?";

    // Constructor - Database Connection
    public UserDaoI() {
        String url = "jdbc:mysql://localhost:3306/aasra_foods";
        String username = "root";
        String password = "Atharvital@123"; // Change to your MySQL password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ UserDao - Database connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }

    // ===================================
    // 1. USER REGISTRATION
    // ===================================
    @Override
    public boolean registerUser(User user) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getPhoneNo());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getUserName());
            statement.setString(6, user.getPassword()); // Hash in production
            statement.setString(7, user.getRole().toString());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ User registered successfully: " + user.getUserName());
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error registering user: " + user.getUserName());
            e.printStackTrace();
        }

        return false;
    }

    // ===================================
    // 2. USER LOGIN
    // ===================================
    @Override
    public User loginUser(String userName, String password) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;

        try {
            statement = connection.prepareStatement(SELECT_BY_USERNAME_PASSWORD);

            statement.setString(1, userName);
            statement.setString(2, password);

            rs = statement.executeQuery();

            if (rs.next()) {
                user = extractUserFromResultSet(rs);

                // Update last login
                updateLastLogin(user.getUserId());

                System.out.println("✅ User logged in successfully: " + userName);
                return user;
            } else {
                System.out.println("❌ Invalid username or password: " + userName);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error during login for user: " + userName);
            e.printStackTrace();
        }

        return null;
    }

    // ===================================
    // 3. GET USER BY ID
    // ===================================
    @Override
    public User getUserById(int userId) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;

        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, userId);

            rs = statement.executeQuery();

            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching user by ID: " + userId);
            e.printStackTrace();
        }

        return user;
    }

    // ===================================
    // 4. GET USER BY USERNAME
    // ===================================
    @Override
    public User getUserByUsername(String userName) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;

        try {
            statement = connection.prepareStatement(SELECT_BY_USERNAME);
            statement.setString(1, userName);

            rs = statement.executeQuery();

            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching user by username: " + userName);
            e.printStackTrace();
        }

        return user;
    }

    // ===================================
    // 5. UPDATE USER PROFILE
    // ===================================
    @Override
    public boolean updateUserProfile(User user) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_PROFILE);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getPhoneNo());
            statement.setString(4, user.getAddress());
            statement.setInt(5, user.getUserId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Profile updated successfully for user ID: " + user.getUserId());
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error updating profile for user ID: " + user.getUserId());
            e.printStackTrace();
        }

        return false;
    }

    // ===================================
    // 6. UPDATE PASSWORD
    // ===================================
    @Override
    public boolean updatePassword(int userId, String newPassword) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_PASSWORD);

            statement.setString(1, newPassword);
            statement.setInt(2, userId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Password updated successfully for user ID: " + userId);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error updating password for user ID: " + userId);
            e.printStackTrace();
        }

        return false;
    }

    // ===================================
    // 7. CHECK IF USERNAME EXISTS
    // ===================================
    @Override
    public boolean isUsernameExists(String userName) {
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(CHECK_USERNAME);
            statement.setString(1, userName);

            rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error checking username existence: " + userName);
            e.printStackTrace();
        }

        return false;
    }

    // ===================================
    // 8. CHECK IF EMAIL EXISTS
    // ===================================
    @Override
    public boolean isEmailExists(String email) {
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.prepareStatement(CHECK_EMAIL);
            statement.setString(1, email);

            rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error checking email existence: " + email);
            e.printStackTrace();
        }

        return false;
    }

    // ===================================
    // 9. UPDATE LAST LOGIN
    // ===================================
    private void updateLastLogin(int userId) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_LAST_LOGIN);
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Error updating last login for user ID: " + userId);
            e.printStackTrace();
        }
    }

    // ===================================
    // 10. EXTRACT USER FROM RESULTSET
    // ===================================
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUserId(rs.getInt("userId"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNo(rs.getLong("phoneNo"));
        user.setAddress(rs.getString("address"));
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setCreateDate(rs.getTimestamp("createDate"));
        user.setLastLogin(rs.getTimestamp("lastLogin"));
        user.setVerificationStatus(rs.getString("verificationStatus"));

        return user;
    }

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}
}
