package controller;

import db.ProductDB;
import db.ProductDBIF;
import model.Product;
import model.Size;

public class ProductCtrl {
	private ProductDBIF productDB;

	public ProductCtrl() {
		productDB = new ProductDB();
	}

	public Product getProduct(int prodNo, Size s) {
		return null;

	}

	public void updateStockLine(Product p) {

	}
}
