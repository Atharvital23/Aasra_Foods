package com.aasra.dao;

import java.util.List;

import com.aasra.model.Cart;
import com.aasra.model.CartItem;

/*
 * this is interface for the DAO implementation in java.
 */
public interface CartDao {
	
	/*
	 * This method which use to add the items to the cart,
	 * with the specific which is added this, 
	 * `cartItemId`-> it is auto increment Primary key in the DB,
	 * `userId`-> Which Customer added the item to the cart,
	 * `menuId`-> Which item added into the cart,
	 * `restaurantId`-> Which restaurant choose for the item, 
	 * `quantity`-> how may items at the specific at the same item.
	 */
	void addToCart(CartItem item);

	/*
	 * In this we have perform an join operation on the `cart_items`,
	 * `menu`, and `restaurant` to getting the `cart_items.cartItemId`,
	 * `cart_items.userId`, `cart_items.menuId`, `cart_items.restaurantId`,
	 * `cart_items.quantity`, `menu.name`, `menu.price`, `menu.imagePath`,
	 * and `restaurant.name` tacking the multiple cart items from the table, 
	 * tacking the list used to tight bound to the CartItem class in java.   
	 */
	List<CartItem> getCartByUser(int userId);

	/*
	 * updating the number of quantities in the `cart_item` table associated,
	 * with the `cartItemId` and the `cartItemId`.
	 */
	void updateQuantity(int cartItemId, int quantity);

	/*
	 * we have to remove that is to performing an delete on the database,
	 * that which the specific cart item will be deleted or removed,
	 * from the DB to use Simply the `cartItemId` in the `cart_Item` table.
	 */
	void removeFromCart(int cartItemId);

	/*
	 * in this we have to perform an direct operation on the table while,
	 * removing all the items from the cart that results the customer cart,
	 * become an empty or clear, that to taken an `userId` that performs the,
	 * same operation.
	 * 
	 */
	void clearCart(int userId);
}
