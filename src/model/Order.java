package model;

import java.util.ArrayList;

public class Order {
	private ArrayList orderLines;

	public Order() {
		orderLines = new ArrayList<>();
	}

	public Order addOrderLine(OrderLine ol) {
		orderLines.add(ol);
		return this;
	}
}
