package ui;

import java.sql.SQLException;

import controller.OrderCtrl;
import model.Order;
import model.Size;

public class OrderUI {
	private OrderCtrl orderCtrl;
	private Order order;

	public OrderUI() {
		orderCtrl = new OrderCtrl();
	}

	public void registerOrder() {
		order = orderCtrl.createOrder();
	}

	public void addProduct(String prodNo, int quantity, Size size) throws SQLException {
		orderCtrl.addProduct(prodNo, quantity, size);
	}

	public void addCustomer() {
		orderCtrl.addCustomer();
	}

	public void completeOrder() {

	}

}
