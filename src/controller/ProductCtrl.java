package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ProductDB;
import db.ProductDBIF;
import model.Product;
import model.Size;

public class ProductCtrl {
	private ProductDBIF productDB;
	private ArrayList<Product> searchedProduct;

	public ProductCtrl() {
		productDB = new ProductDB();
	}

	public Product getProduct(String prodNo, Size size) throws SQLException {
		Product product = productDB.getProduct(prodNo, size);
		return product;

	}

	public void updateStockLine(Product p) {

	}

	public List<Product> findProduct(String prodNo) {
		
		
		List<Product> products = new ArrayList<>();
		searchedProduct = (ArrayList<Product>) productDB.buildAllProduct();
		for (int i = 0; i < searchedProduct.size(); i++) {

			if (prodNo.equals(searchedProduct.get(i).getProdNo())) {
				products.add(searchedProduct.get(i));

			}

		}

		return products;
	}

}
