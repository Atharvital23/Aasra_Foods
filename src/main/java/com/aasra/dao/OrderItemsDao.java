package com.aasra.dao;

import java.util.List;

import com.aasra.model.OrderItems;

public interface OrderItemsDao {
	void addOrderItems(OrderItems orderItems);

	OrderItems getOrderItems(int orderItemsId);

	List<OrderItems> getAll();

	void updateOrderItems(OrderItems orderItems);

	void deleteOrderItems(int orderItemsId);
}
