package model;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String productNo, productDescription;
	private Size size;
	private ArrayList stockLines;
	private int idProduct;

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Product(String productNo, String productDescription, Size size, int idProduct) {
		this.productNo = productNo;
		this.size = size;
		this.idProduct = idProduct;
		this.productDescription = productDescription;
		this.stockLines = new ArrayList<>();
	}

	public void addStockLines(StockLine stockLine) {
		stockLines.add(stockLine);
	}

	public List<StockLine> getStockLines() {

		return stockLines;

	}

	@Override
	public String toString() {
		return "Order [prodNo=" + productNo + ", product description=" + productDescription + ", size="
				+ size.getSizeDesc();
	}

	public int getIdProduct() {
		return idProduct;
	}

	public Size getSize() {
		return this.size;
	}

	public String getProdNo() {
		return this.productNo;
	}

}
