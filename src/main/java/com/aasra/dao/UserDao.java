package com.aasra.dao;

import java.util.List;

import com.aasra.model.User;

public interface UserDao {
	void addUser(User user);

	User getUser(int userId);

	List<User> getAll();

	void updateUser(User user);

	void deleteUser(int userId);

	boolean isEmailExists(String email);

	boolean isUsernameExists(String userName);

	boolean updatePassword(int userId, String newPassword);

	boolean updateUserProfile(User user);

	User getUserByUsername(String userName);

	User getUserById(int userId);

	User loginUser(String userName, String password);

	boolean registerUser(User user);
	
}
