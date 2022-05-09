package model;

public class Product {

	private String productNo, productDescription;
	private int reservedAQ, reservedOOQ;
	private Size size;
	
	public Product(String productNo, String productDescription, Size size) {
		this.productNo = productNo;
		this.productDescription = productDescription;
		this.size = size;
	}
	
}
