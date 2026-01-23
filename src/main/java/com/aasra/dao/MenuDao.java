package com.aasra.dao;

import java.util.List;

import com.aasra.model.Menu;

public interface MenuDao {
	void addMenu(Menu menu);

	Menu getMenu(int menuId);

	List<Menu> getAll();

	void updateMenu(Menu menu);

	void deleteMenu(int menuId);

	public List<Menu> getMenuByRestaurantId(int restaurantId);

	List<Menu> getAllMenus();

	List<Menu> getMenuByRestaurant(int restaurantId);
}
