package ui;

import controller.OrderCtrl;
import model.Order;

public class OrderUI {
	private OrderCtrl orderCtrl;
	private Order order;

	public OrderUI() {
		orderCtrl = new OrderCtrl();
	}

	public void registerOrder() {
		order = orderCtrl.createOrder();
	}

	public void addProduct(int prodNo, int quantity, String size) {
		orderCtrl.addProduct(prodNo, quantity, size);
		order.toString();
	}

	public void addCustomer() {

	}

	public void completeOrder() {

	}

}
