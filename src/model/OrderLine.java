package model;

public class OrderLine {
	private Product product;
	private int quantity;

	public OrderLine(Product p, int quantity) {
		this.setProduct(p);
		this.setQuantity(quantity);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
