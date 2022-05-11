package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.*;
import model.Order;
import model.OrderLine;
import model.Product;

public class OrderCtrl {
	private ProductCtrl productCtrl;
	private Order order;

	public OrderCtrl() {

		productCtrl = new ProductCtrl();
	}

	public static ResultSet getAllOrders() {
		return OrderDB.getAllOrders();
	}

	public Order createOrder() {
		Order order = new Order();
		this.order = order;
		return order;
	}

	public Order addProduct(String prodNo, int quantity, String size) throws SQLException {
		Product p = productCtrl.getProduct(prodNo, size);
		OrderLine ol = new OrderLine(p, quantity);
		order.addOrderLine(ol);

		return order;

	}

	public Order addCustomer() {
		return null;

	}

	public Order completeOrder() {
		return null;

	}

	public static void processOldestOrder(String orderNo) {
		// OrderDB.processOldestOrder(orderNo);
	}
}
