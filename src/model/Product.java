package model;

public class Product {

	private String prodNo, productDescription;
	private Size size;
	private int idProduct;

	public Product(String prodNo, String productDescription, Size size, int idProduct) {
		this.prodNo = prodNo;
		this.size = size;
		this.idProduct = idProduct;
		this.productDescription = productDescription;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public Size getSize() {
		return this.size;
	}

	public String getProdNo() {
		return this.prodNo;
	}
	
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "Order [prodNo=" + prodNo + ", product description=" + productDescription + ", size="
				+ size.getSizeDesc();
	}
}
