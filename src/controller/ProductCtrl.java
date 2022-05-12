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

	public Product getProduct(String prodNo, String size) {
		try {
			productDB.getProduct(prodNo, size);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void updateStockLine(Product p) {

	}
}
