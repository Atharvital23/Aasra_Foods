package com.aasra.dao;

import java.util.List;

import com.aasra.model.Orders;

public interface OrdersDao {
	int addOrders(Orders orders);

	Orders getOrders(int ordersId);

	List<Orders> getAll();

	void updateOrders(Orders orders);

	void deleteOrders(int ordersId);
}
