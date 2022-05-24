package controller;

import java.util.ArrayList;
import java.util.List;

import db.OrderDB;
import db.DataAccessException;
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

	// Constructs a new OrderCtrl object
	public OrderCtrl() {
		userSession = UserSession.getInstance();
		productCtrl = new ProductCtrl();
		try {
			orderDB = new OrderDB();
		} catch (DataAccessException e) {
			System.err.println("Error in OrderCtrl, in constructing OrderCtrl/orderDB.");
			e.printStackTrace();
		}
	}

	// Gets all orders from the DB
	public List<Order> getOrders() {
		List<Order> list = new ArrayList<>();
		try {
			list = orderDB.getOrders();
		} catch (DataAccessException e) {
			System.err.println("Error in OrderCtrl, in getting all orders.");
			e.printStackTrace();
		}
		return list;
	}

	// Creates a new Order object, that the class holds
	public Order createOrder() {
		Order order = new Order();
		this.order = order;
		return order;
	}

	// Adds a product to the order, from a given prodNo, quantity and size
	public Order addProduct(String prodNo, int quantity, Size size) {
		Product p = productCtrl.getProduct(prodNo, size);
		OrderLine ol = new OrderLine(p, quantity);
		order.addOrderLine(ol);
		return order;
	}

	// Adds a contact to the order, from the userSession, containing the customer
	// logged in
	public Order addCustomer() {
		Contact customer = userSession.getCustomer();
		order.addCustomer(customer);
		return order;
	}

	// Completes the order and commits it to the DB
	public Order completeOrder() {
		Order returnOrder = null;
		try {
			returnOrder = orderDB.commitOrder(order);
		} catch (DataAccessException e) {
			System.err.println("Error in OrderCtrl, in completing an order");
			e.printStackTrace();
		}
		return returnOrder;
	}
	
	
	// ---------------------------------- Future Use Cases/Iterations ---------------------------------- //

	
	// Updates the oldest orders status to running
	public void updateOrderRunning(String orderNo) {
		try {
			orderDB.updateOrderRunning(orderNo);
		} catch (DataAccessException e) {
			System.err.println("Error in OrderCtrl, in updating order to running");
			e.printStackTrace();
		}
	}

	// Updates the selected orders status to finished
	public void updateOrderFinished(String orderNo) {
		try {
			orderDB.updateOrderFinished(orderNo);
		} catch (DataAccessException e) {
			System.err.println("Error in OrderCtrl, in updating order to finished");
			e.printStackTrace();
		}
	}
}
