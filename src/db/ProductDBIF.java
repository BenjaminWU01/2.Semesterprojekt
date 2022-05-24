package db;

import java.sql.ResultSet;
import java.util.List;

import model.Product;
import model.Size;

public interface ProductDBIF {

	public Product getProduct(String productNo, Size size) throws DataAccessException;
	public Product buildProduct(ResultSet rs) throws DataAccessException;
	public List<Product> buildAllProduct() throws DataAccessException;

}
