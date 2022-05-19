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
	private OrderDBIF orderDB;

	public OrderCtrl() throws DataAccessException {
		userSession = userSession.getInstance();
		productCtrl = new ProductCtrl();
		orderDB = new OrderDB();
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

	public void processOldestOrder(String orderNo) throws DataAccessException, SQLException {
		OrderDB odb = new OrderDB();
		odb.processOldestOrder(orderNo);
	}

	public void finishOrder(String orderNo) throws DataAccessException, SQLException {
		OrderDB odb = new OrderDB();
		odb.finishOrder(orderNo);
	}
}
