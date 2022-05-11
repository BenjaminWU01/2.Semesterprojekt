package controller;

import model.Order;
import model.OrderLine;
import model.Product;

public class OrderCtrl {
	private ProductCtrl productCtrl;
	private Order order;

	public OrderCtrl() {

		productCtrl = new ProductCtrl();
	}

	public Order createOrder() {
		Order order = new Order();
		this.order = order;
		return order;
	}

	public Order addProduct(int prodNo, int quantity, String size) {
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

}
