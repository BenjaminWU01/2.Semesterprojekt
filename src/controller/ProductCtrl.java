package controller;

import java.util.ArrayList;
import java.util.List;

import db.DataAccessException;
import db.ProductDB;
import db.ProductDBIF;
import model.Product;
import model.Size;

public class ProductCtrl {
	private ProductDBIF productDB;
	private ArrayList<Product> searchedProduct;

	public ProductCtrl() {
		try {
			productDB = new ProductDB();
		} catch (DataAccessException e) {
			System.out.println("Error in ProductCtrl, in constructor");
			e.printStackTrace();
		}
	}

	public Product getProduct(String prodNo, Size size) {
		Product product = null;
		try {
			product = productDB.getProduct(prodNo, size);
		} catch (DataAccessException e) {
			System.out.println("Error in ProductCtrl, in getProduct()");
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> findProduct(String prodNo) {
		List<Product> products = new ArrayList<>();
		try {
			searchedProduct = (ArrayList<Product>) productDB.buildAllProduct();
		} catch (DataAccessException e) {
			System.out.println("Error in ProductCtrl, in findProduct()");
			e.printStackTrace();
		}
		for (int i = 0; i < searchedProduct.size(); i++) {
			if (prodNo.equals(searchedProduct.get(i).getProdNo())) {
				products.add(searchedProduct.get(i));
			}
		}
		return products;
	}
}
