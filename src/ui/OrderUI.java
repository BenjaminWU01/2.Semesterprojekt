package ui;

import controller.OrderCtrl;
import model.Size;

public class OrderUI {
	private OrderCtrl orderCtrl;

	public OrderUI() {
		orderCtrl = new OrderCtrl();
	}

	// Creates a new, empty Order object
	public void registerOrder() {
		orderCtrl.createOrder();
	}

	// Associates a Product to a new OrderLine object, and the OrderLine to the
	// Order
	public void addProduct(String prodNo, int quantity, Size size) {
		orderCtrl.addProduct(prodNo, quantity, size);
	}

	// Associates a Contact object to the order
	public void addCustomer() {
		orderCtrl.addCustomer();
	}

	// Creates and saves the Order in the DB
	public void completeOrder() {
		orderCtrl.completeOrder();
	}
}
