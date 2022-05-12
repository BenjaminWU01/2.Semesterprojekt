package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.*;
import model.Contact;
import model.Order;
import model.OrderLine;
import model.Product;
import model.Size;

public class OrderCtrl {
	private ProductCtrl productCtrl;
	private Order order;
	private UserSession userSession;

	public OrderCtrl() {
		userSession = userSession.getInstance();
		productCtrl = new ProductCtrl();
	}

	public List<Order> getOrders() throws DataAccessException {
		OrderDB odb = new OrderDB();
		return odb.getOrders();
	}

	public Order createOrder() {
		Order order = new Order();
		this.order = order;
		return order;
	}

	public Order addProduct(String prodNo, int quantity, Size size) throws SQLException {
		Product p = productCtrl.getProduct(prodNo, size);
		OrderLine ol = new OrderLine(p, quantity);
		order.addOrderLine(ol);

		return order;

	}

	public Order addCustomer() {
		Contact customer = userSession.getCustomer();
//		order.addCustomer(customer);
		return order;
	}

	public Order completeOrder() {
		return null;

	}

	public static void processOldestOrder(String orderNo) {
		// OrderDB.processOldestOrder(orderNo);
	}
}
