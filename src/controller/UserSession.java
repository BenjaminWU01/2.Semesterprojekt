package controller;

import model.Contact;

public class UserSession {
	private static UserSession userSession;
	private Contact customer;

	private UserSession() {

	}

	public static UserSession getInstance() {
		if (userSession == null) {
			userSession = new UserSession();
		}
		return userSession;
	}

	public void logIn(Contact customer) {
		this.customer = customer;
	}

	public Contact getCustomer() {
		return customer;

	}
}
