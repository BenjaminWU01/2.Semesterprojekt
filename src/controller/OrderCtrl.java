package controller;

import java.sql.ResultSet;

import db.*;
import model.Order;

public class OrderCtrl {

	public OrderCtrl() {

	}

	public static ResultSet getAllOrders() {
		return OrderDB.getAllOrders();
	}

	public Order createOrder() {
		return null;
	}

	public Order addProduct(int prodNo, int quantity, String size) {
		return null;

	}

	public Order addCustomer() {
		return null;

	}

	public Order completeOrder() {
		return null;

	}
}
