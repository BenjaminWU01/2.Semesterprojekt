package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private OrderDB orderDB;
	

	public OrderCtrl() {
		userSession = UserSession.getInstance();
		productCtrl = new ProductCtrl();
		try {
			orderDB = new OrderDB();
		} catch (DataAccessException e) {
			System.out.println("Error in OrderCtrl, in constructing OrderCtrl/orderDB.");
			e.printStackTrace();
		}
	}

	public List<Order> getOrders() {
		List<Order> list = new ArrayList<>();
		try {
			list = orderDB.getOrders();
		} catch (DataAccessException e) {
			System.out.println("Error in OrderCtrl, in getting all orders.");
			e.printStackTrace();
		}
		return list;
	}

	public Order createOrder() {
		Order order = new Order();
		this.order = order;
		return order;
	}

	public Order addProduct(String prodNo, int quantity, Size size) throws SQLException {
		System.out.println("adding product " + prodNo);
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
		Order returnOrder = null;
		System.out.println(order);
		try{
			returnOrder = orderDB.commitOrder(order);
		} catch (DataAccessException e) {
			System.out.println("Error in OrderCtrl, in completing an order");
			e.printStackTrace();
		}
		return returnOrder;
	}

	public void updateOrderRunning(String orderNo) {
		try {
			orderDB.updateOrderRunning(orderNo);
		} catch (DataAccessException e) {
			System.out.println("Error in OrderCtrl, in updating order to running");
			e.printStackTrace();
		}
	}

	public void updateOrderFinished(String orderNo) {
		try {
			orderDB.updateOrderFinished(orderNo);
		} catch (DataAccessException e) {
			System.out.println("Error in OrderCtrl, in updating order to finished");
			e.printStackTrace();
		}
	}
}
