package model;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String productNo, productDescription;
	private Size size;
	private ArrayList stockLines;

	public Product(String productNo, String productDescription, Size size) {
		this.productNo = productNo;
		this.productDescription = productDescription;
		this.size = size;
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

}
