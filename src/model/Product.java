package model;

public class Product {

	private String productNo, productDescription;
	private Size size;
	
	public Product(String productNo, String productDescription, Size size) {
		this.productNo = productNo;
		this.productDescription = productDescription;
		this.size = size;
	}
	
}
