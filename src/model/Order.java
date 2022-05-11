package model;

import java.util.ArrayList;

public class Order {
	private ArrayList orderLines;
	private String status;

	public Order() {
		orderLines = new ArrayList<>();
	}

	public Order addOrderLine(OrderLine ol) {
		orderLines.add(ol);
		return this;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	
}
