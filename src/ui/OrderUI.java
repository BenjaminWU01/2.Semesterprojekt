package ui;

import java.sql.SQLException;

import controller.OrderCtrl;
import db.DataAccessException;
import model.Order;
import model.Size;

public class OrderUI {
	private OrderCtrl orderCtrl;

	public OrderUI() throws DataAccessException {
		orderCtrl = new OrderCtrl();
	}

	public void registerOrder() {
		orderCtrl.createOrder();
	}

	public Order addProduct(String prodNo, int quantity, Size size) throws SQLException {
		return orderCtrl.addProduct(prodNo, quantity, size);

	}

	public void addCustomer() {
		orderCtrl.addCustomer();
	}

	public Order completeOrder() {
		Order o = orderCtrl.completeOrder();
		return o;
	}

}
