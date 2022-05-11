package controller;

import java.sql.ResultSet;

import db.*;

public class OrderCtrl {

	public static ResultSet getAllOrders() {
		return OrderDB.getAllOrders();
	}
}
