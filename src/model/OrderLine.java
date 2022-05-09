package model;

public class OrderLine {
	private Product product;
	private int quantity;

	public OrderLine(Product p, int quantity) {
		this.product = p;
		this.quantity = quantity;
	}
}
