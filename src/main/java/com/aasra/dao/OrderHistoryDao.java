package com.aasra.dao;

import java.util.List;

import com.aasra.model.OrderHistory;

public interface OrderHistoryDao {
	void addOrderHistory(OrderHistory orderHistory);

	OrderHistory getOrderHistory(int orderHistoryId);

	List<OrderHistory> getAll();

	void updateOrderHistory(OrderHistory orderHistory);

	void deleteOrderHistory(int orderHistoryId);
}
