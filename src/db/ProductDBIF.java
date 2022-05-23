package db;

import java.util.List;

import model.Product;
import model.Size;

public interface ProductDBIF {

	public Product getProduct(String productNo, Size size) throws DataAccessException;
	public List<Product> buildAllProduct() throws DataAccessException;

}
