package controller;

import java.sql.SQLException;

import db.ProductDB;
import db.ProductDBIF;
import model.Product;
import model.Size;

public class ProductCtrl {
	private ProductDBIF productDB;

	public ProductCtrl() {
		productDB = new ProductDB();
	}

	public Product getProduct(String prodNo, String size) throws SQLException {
		Product product = productDB.getProduct(prodNo, size);
		return product;

	}

	public void updateStockLine(Product p) {

	}
}
